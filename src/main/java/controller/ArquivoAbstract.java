package controller;

import model.Anexo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.omnifaces.util.Faces.getServletContext;

public abstract class ArquivoAbstract {

    protected static final String DIRETORIO_GERAL = getServletContext().getInitParameter("upload.location");

    protected List<Anexo> arquivosTemporarios = new ArrayList<>();
    protected List<Anexo> arquivosRemovidos = new ArrayList<>();
    protected List<Anexo> arquivosAdicionados = new ArrayList<>();

    protected Path diretorioUploads = Paths.get("/aarf/uploads");

    public void enviarArquivoTemporario(FileUploadEvent event) {
        String hash = UUID.randomUUID().toString().substring(0, 20);
        UploadedFile arquivoEnviado = event.getFile();
        String nome = FilenameUtils.getBaseName(arquivoEnviado.getFileName());
        String extensao = FilenameUtils.getExtension(arquivoEnviado.getFileName());

        try {
            if(!diretorioUploads.toFile().exists())
                diretorioUploads.toFile().mkdirs();

            Path arquivo = Files.createTempFile(diretorioUploads, nome + "-", "." + extensao);
            OutputStream out = new FileOutputStream(arquivo.toFile());
            out.write(arquivoEnviado.getContents());
            out.close();

            Anexo anexo = new Anexo();
            anexo.setCaminho(obterDiretorioCompleto());
            anexo.setNome(nome);
            anexo.setExtensao(extensao);
            anexo.setHash(hash);
            anexo.setFile(arquivo.toFile());
            arquivosTemporarios.add(anexo);

            Messages.addInfo(null, "O arquivo " + anexo.getNome() + " foi adicionado!");
        } catch (IOException e) {
            e.printStackTrace();
            Messages.addError(null, "Erro ao enviar arquivo");
        } finally {

        }

    }

    public void salvarArquivosTemporarios(){
        for (Anexo anexo : arquivosTemporarios) {
            try {
                File arquivoFisico = new File(obterDiretorioCompleto(), anexo.getHash() + "." + anexo.getExtensao());
                FileUtils.copyFile(anexo.getFile(), arquivoFisico);

                if(!arquivoFisico.exists())
                    arquivoFisico.getParentFile().mkdirs();
                arquivoFisico.createNewFile();

                salvarAnexos(anexo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        arquivosTemporarios.clear();
    }

    public StreamedContent download(Anexo anexo){
        String path = anexo.getPath();

        try {
            InputStream stream = new FileInputStream(path);
            return new DefaultStreamedContent(stream, "application/pdf", anexo.getNome() + "." + anexo.getExtensao());

        } catch (FileNotFoundException e) {
            Messages.addError(null, "Arquivo não encontrado");
            e.printStackTrace();
            return null;
        }
    }

    public void remover(Anexo anexo) {
        if(!arquivosRemovidos.contains(anexo))
            arquivosRemovidos.add(anexo);
        if(arquivosAdicionados.contains(anexo))
            arquivosAdicionados.remove(anexo);
    }

    public void removerArquivosTemporarios(){
        for (Anexo anexo : arquivosRemovidos) {
            File file = new File(anexo.getPath());
            removerAnexo(anexo);
            if(file.exists())
                file.delete();
        }

        arquivosRemovidos.clear();
    }

    protected abstract void salvarAnexos(Anexo anexo);

    protected abstract void removerAnexo(Anexo anexo);

    protected abstract String obterDiretorioModulo();

    private String obterDiretorioCompleto(){
        return DIRETORIO_GERAL + obterDiretorioModulo();
    }
}
