package controller;

import service.MatriculaService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named(value = "matriculaMB")
public class MatriculaMB  implements Serializable {
    private static final long serialVersionUID = -7785394172005232068L;

    @Inject
    private MatriculaService matriculaService;

}
