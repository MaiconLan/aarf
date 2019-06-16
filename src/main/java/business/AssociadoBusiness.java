package business;

import dao.AssociadoDAO;
import dto.AssociadoDTO;
import exception.AssociadoBusinessException;
import exception.LoginException;
import model.Associado;
import model.Endereco;
import model.Usuario;
import utils.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Stateless
public class AssociadoBusiness {

	@Inject
	private AssociadoDAO associadoDAO;

	@Inject
	private UsuarioBusiness usuarioBusiness;

    public void salvarAssociado(Associado a) throws AssociadoBusinessException, LoginException {
        validarSalvarAssociado(a);

        StringUtils.removerCaracteres(a.getPessoa());
        usuarioBusiness.salvar(a.getUsuario());

        Endereco endereco = a.getPessoa().getEndereco();
        endereco.setPessoa(a.getPessoa());

        if(a.getIdAssociado() == null)
        	associadoDAO.save(a);
        else
        	associadoDAO.update(a);
    }

    private void validarSalvarAssociado(Associado a) throws AssociadoBusinessException {
        Collection<String> detalhes = new ArrayList<>();

        validarCpf(a, detalhes);
        validarRg(a, detalhes);
        validarAssociado(a, detalhes);
        validarEndereco(a.getPessoa().getEndereco(), detalhes);

        if(!detalhes.isEmpty())
            throw new AssociadoBusinessException(detalhes);
    }

    private void validarCpf(Associado associado, Collection<String> detalhes){
        if(associadoDAO.isCpfCadastrado(associado))
            detalhes.add("Este CPF já está cadastrado para um Associado ");
    }

    private void validarRg(Associado associado, Collection<String> detalhes){
        if(associadoDAO.isRgCadastrado(associado))
            detalhes.add("Este RG já está cadastrado para um Associado ");
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
        StringUtils.removerCaracteres(a.getPessoa());
        associadoDAO.update(a);
    }

    public Associado obterAssociado(Long idAssociado) {
        return associadoDAO.findById(idAssociado);
    }
}
