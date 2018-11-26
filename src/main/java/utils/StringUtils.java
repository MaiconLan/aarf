package utils;

import model.Pessoa;

public class StringUtils {

    public static String removerCaracteres(String valor){
        return valor.replace(".", "")
                .replace("-", "")
                .replace("/", "")
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "");
    }

    public static void removerCaracteres(Pessoa pessoa){
        String cpf = removerCaracteres(pessoa.getCpf());
        String rg = removerCaracteres(pessoa.getRg());
        String celular = removerCaracteres(pessoa.getCelular());
        String telefone = removerCaracteres(pessoa.getTelefone());
        String cep = removerCaracteres(pessoa.getEndereco().getCep());

        pessoa.getEndereco().setCep(cep);
        pessoa.setCelular(celular);
        pessoa.setTelefone(telefone);
        pessoa.setCpf(cpf);
        pessoa.setRg(rg);
    }
}
