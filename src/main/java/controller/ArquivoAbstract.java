package controller;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public abstract class ArquivoAbstract {

    protected static final String DIRETORIO_GERAL = "\\aarf\\arquivos\\";

    protected Part arquivoEnviado;

    public void salvarArquivo(){
        try (InputStream input = arquivoEnviado.getInputStream()) {
            String fileName = arquivoEnviado.getName();
            Files.copy(input, new File(obterDiretorioCompleto(), fileName).toPath());
            System.out.print("Arquivo enviad: " + fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String obterDiretorioModulo();

    public Part getArquivoEnviado() {
        return arquivoEnviado;
    }

    public void setArquivoEnviado(Part arquivoEnviado) {
        this.arquivoEnviado = arquivoEnviado;
    }

    private String obterDiretorioCompleto(){
        return DIRETORIO_GERAL + obterDiretorioModulo();
    }
}
