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
    protected static final File TEMP;
    private List<File> arquivosTemporarios = new ArrayList<>();

    static {
        TEMP = FileUtils.getTempDirectory();
        if(!TEMP.exists())
            TEMP.mkdir();
    }

    public void enviarArquivoTemporario(FileUploadEvent event) {
        UploadedFile arquivoEnviado = event.getFile();

        File file = new File(obterDiretorioCompleto(), arquivoEnviado.getFileName());
        arquivosTemporarios.add(file);
        Messages.addInfo(null, "O arquivo " + arquivoEnviado.getFileName() + " foi adicionado!");
    }

    public void salvarArquivosTemporarios(){
        for (File file : arquivosTemporarios) {
            try {
                if(!file.exists())
                    file.getParentFile().mkdirs();
                file.createNewFile();

                salvarAnexos(obterDiretorioCompleto(), file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void salvarAnexos(String caminho, String arquivo);

    protected abstract String obterDiretorioModulo();

    private String obterDiretorioCompleto(){
        return DIRETORIO_GERAL + obterDiretorioModulo();
    }
}
