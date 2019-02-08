package dto;

import model.Edital;

import java.io.Serializable;
import java.util.List;

public class EditalDTO implements Serializable {

    private static final long serialVersionUID = -7896227064313305812L;
    private String titulo;

    private boolean order = true;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }
}
