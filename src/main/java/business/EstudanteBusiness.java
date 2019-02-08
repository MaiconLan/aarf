package business;

import dao.EstudanteDAO;
import dto.EstudanteDTO;
import exception.EstudanteBusinessException;
import exception.LoginException;
import model.*;
import service.UsuarioService;
import utils.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Stateless
public class EstudanteBusiness {

    @Inject
    private EstudanteDAO estudanteDAO;

    @Inject
    private UsuarioService usuarioService;

    public void salvarEstudante(Estudante estudante) throws EstudanteBusinessException, LoginException {
        validarSalvarEstudante(estudante);

        removerCaracteres(estudante.getPessoa());
        usuarioService.salvarUsuario(estudante.getUsuario());

        Endereco endereco = estudante.getPessoa().getEndereco();
        endereco.setPessoa(estudante.getPessoa());

        if(estudante.getIdEstudante() == null)
            estudanteDAO.save(estudante);
        else
            estudanteDAO.update(estudante);
    }

    private void validarSalvarEstudante(Estudante estudante) throws EstudanteBusinessException {
        Collection<String> detalhes = new ArrayList<>();

        validarCpf(estudante, detalhes);
        validarRg(estudante, detalhes);
        validarEstudante(estudante, detalhes);
        validarEndereco(estudante.getPessoa().getEndereco(), detalhes);

        if(!detalhes.isEmpty())
            throw new EstudanteBusinessException(detalhes);
    }

    private void validarCpf(Estudante estudante, Collection<String> detalhes){
        if(estudanteDAO.isCpfCadastrado(estudante))
            detalhes.add("Este CPF já está cadastrado para um Estudante!");
    }

    private void validarRg(Estudante estudante, Collection<String> detalhes){
        if(estudanteDAO.isRgCadastrado(estudante))
            detalhes.add("Este RG já está cadastrado para um Estudante!");
    }

    private void validarEstudante(Estudante estudante, Collection<String> detalhes) {
        if(estudante.getPessoa().getNome() == null)
            detalhes.add("Nome é de preenchimento obrigatório");
    }

    private void validarEndereco(Endereco endereco, Collection<String> detalhes){
        if(endereco.getCep() == null)
            detalhes.add("CEP é de preenchimento obrigatório");

        if(endereco.getBairro() == null)
            detalhes.add("Bairro é de preenchimento obrigatório");

        if(endereco.getLogradouro() == null)
            detalhes.add("Logradouro é de preenchimento obrigatório");

    }

    private void removerCaracteres(Pessoa pessoa){
        String cpf = StringUtils.removerCaracteres(pessoa.getCpf());
        String rg = StringUtils.removerCaracteres(pessoa.getRg());
        String celular = StringUtils.removerCaracteres(pessoa.getCelular());
        String telefone = StringUtils.removerCaracteres(pessoa.getTelefone());
        String cep = StringUtils.removerCaracteres(pessoa.getEndereco().getCep());

        pessoa.getEndereco().setCep(cep);
        pessoa.setCelular(celular);
        pessoa.setTelefone(telefone);
        pessoa.setCpf(cpf);
        pessoa.setRg(rg);
    }

    public List<Estudante> consultarEstudantes(EstudanteDTO estudanteDTO) {
        String telefone = StringUtils.removerCaracteres(estudanteDTO.getTelefone());
        String celular = StringUtils.removerCaracteres(estudanteDTO.getCelular());
        estudanteDTO.setTelefone(telefone);
        estudanteDTO.setCelular(celular);

        return estudanteDAO.consultarEstudantes(estudanteDTO);
    }

    public boolean isLoginPreenchido(Usuario usuario){
        if(usuario != null && usuario.getLogin() != null && usuario.getSenha() != null)
            return !usuario.getLogin().isEmpty() && !usuario.getSenha().isEmpty();
        return false;
    }

    public void removerEstudante(Estudante estudante) {
        estudante.setInativo(Boolean.TRUE);
        removerCaracteres(estudante.getPessoa());
        estudanteDAO.update(estudante);
    }

    public Estudante obterEstudante(Long idEstudante) {
        return estudanteDAO.findById(idEstudante);
    }
}
