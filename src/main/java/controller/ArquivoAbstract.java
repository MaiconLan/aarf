package controller;

import org.apache.commons.io.FileUtils;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static org.omnifaces.util.Faces.getServletContext;

public abstract class ArquivoAbstract {

    protected static final String DIRETORIO_GERAL = getServletContext().getInitParameter("upload.location");
    protected static final File TEMP = FileUtils.getTempDirectory();
    private List<File> arquivosTemporarios = new ArrayList<>();

    public void enviarArquivoTemporario(FileUploadEvent event) {
            try {
            UploadedFile arquivoEnviado = event.getFile();

            File file = new File(obterDiretorioCompleto(), arquivoEnviado.getFileName());
            file.createTempFile(file.getName(), ".tmp", TEMP);
            arquivosTemporarios.add(file);
            Messages.addInfo(null, "O arquivo " + arquivoEnviado.getFileName() + " foi adicionado!");

        } catch(IOException e) {
            Messages.addError(null, "Erro ao enviar o arquivo!");
            e.printStackTrace();
        }

    }

    public void salvarArquivosTemporarios(){
        for (File file : arquivosTemporarios) {
            FileUtils.getFile(TEMP, file.getName());

            try {
                Files.copy(TEMP.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                e.printStackTrace();
            }

            if(!file.exists())
                file.mkdirs();

            salvarAnexos(obterDiretorioCompleto(), file.getName());
        }
    }

    protected abstract void salvarAnexos(String caminho, String arquivo);

    protected abstract String obterDiretorioModulo();

    private String obterDiretorioCompleto(){
        return DIRETORIO_GERAL + obterDiretorioModulo();
    }
}
