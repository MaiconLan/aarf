package dto;

import model.Edital;

import java.io.Serializable;
import java.util.List;

public class EditalDTO implements Serializable {

    private static final long serialVersionUID = -7896227064313305812L;
    private  String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
