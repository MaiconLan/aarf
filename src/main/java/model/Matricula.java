package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "matricula")
public class Matricula  implements Serializable {

    private static final long serialVersionUID = -4839789861123080961L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;

    @Temporal(TemporalType.DATE)
    private LocalDate inscricao;

    private Boolean confirmada;

    @ManyToOne
    @JoinColumn(name = "id_estudante")
    private Estudante estudante;

    @ManyToOne
    @JoinColumn(name = "id_edital")
    private Edital edital;



}
