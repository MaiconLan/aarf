package controller;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class ArquivoAbstract {

    protected static final String DIRETORIO_GERAL = "c:\\aarf\\arquivos\\";

    public void enviarArquivo(FileUploadEvent event) {
            try {
            UploadedFile arquivoEnviado = event.getFile();

            File file = new File(obterDiretorioCompleto());
            if(!file.exists())
                file.mkdirs();

            file = new File(obterDiretorioCompleto(), arquivoEnviado.getFileName());

            OutputStream out = new FileOutputStream(file);
            out.write(arquivoEnviado.getContents());
            out.close();

            Messages.addInfo(null, "O arquivo " + arquivoEnviado.getFileName() + " foi salvo!");
        } catch(IOException e) {
            Messages.addError(null, "Erro ao enviar o arquivo!");
            e.printStackTrace();
        }

    }

    protected abstract void salvarAnexos(String caminho, String arquivo);

    protected abstract String obterDiretorioModulo();

    private String obterDiretorioCompleto(){
        return DIRETORIO_GERAL + obterDiretorioModulo();
    }
}
