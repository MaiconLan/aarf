package business;

import dao.AssociadoDAO;
import dto.AssociadoDTO;
import exception.AssociadoBusinessException;
import exception.LoginException;
import model.Associado;
import model.Endereco;
import model.Pessoa;
import model.Usuario;
import service.UsuarioService;
import utils.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
@Stateless
public class AssociadoBusiness {

	@Inject
	private AssociadoDAO associadoDAO;

	@Inject
	private UsuarioService usuarioService;

    public void salvarAssociado(Associado a) throws AssociadoBusinessException, LoginException {
        validarSalvarAssociado(a);

        removerCaracteres(a.getPessoa());
        usuarioService.salvarUsuario(a.getUsuario());

        Endereco endereco = a.getPessoa().getEndereco();
        endereco.setPessoa(a.getPessoa());

        if(a.getIdAssociado() == null)
        	associadoDAO.save(a);
        else
        	associadoDAO.update(a);
    }

    private void validarSalvarAssociado(Associado a) throws AssociadoBusinessException {
        Collection<String> detalhes = Collections.EMPTY_LIST;

        validarAssociado(a, detalhes);
        validarEndereco(a.getPessoa().getEndereco(), detalhes);

        if(!detalhes.isEmpty())
            throw new AssociadoBusinessException(detalhes);
    }

    private void validarAssociado(Associado a, Collection<String> detalhes) {
        if(a.getPessoa().getNome() == null)
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

    public List<Associado> consultarAssociados(AssociadoDTO associadoDTO) {
        String telefone = StringUtils.removerCaracteres(associadoDTO.getTelefone());
        String celular = StringUtils.removerCaracteres(associadoDTO.getCelular());
        associadoDTO.setTelefone(telefone);
        associadoDTO.setCelular(celular);

        return associadoDAO.consultarAssociados(associadoDTO);
    }

    public boolean isLoginPreenchido(Usuario usuario){
        if(usuario != null && usuario.getLogin() != null && usuario.getSenha() != null)
            return !usuario.getLogin().isEmpty() && !usuario.getSenha().isEmpty();
        return false;
    }

    public void removerAssociado(Associado a) {
        a.setInativo(Boolean.TRUE);
        removerCaracteres(a.getPessoa());
        associadoDAO.update(a);
    }

}
