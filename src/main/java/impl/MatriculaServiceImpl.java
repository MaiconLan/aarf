package impl;

import business.MatriculaBusiness;
import service.MatriculaService;

import javax.inject.Inject;
import java.io.Serializable;

public class MatriculaServiceImpl implements MatriculaService, Serializable {
    private static final long serialVersionUID = -5431634182682857354L;

    @Inject
    private MatriculaBusiness matriculaBusiness;
}
