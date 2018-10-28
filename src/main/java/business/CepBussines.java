package business;

import dto.EnderecoDTO;
import exception.CepBussinesException;
import model.Endereco;
import utils.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class CepBussines {

    public String URL_API_POSTMON = "https://api.postmon.com.br/v1/cep/";
    public String FORMATO_XML = "?format=xml";

    public Endereco getEnderecoCompleto(String cep) throws CepBussinesException {
        EnderecoDTO enderecoDTO = null;
        String cepFormatado = StringUtils.removerCaracteres(cep);

        try {
            URL url = new URL(URL_API_POSTMON + cepFormatado + FORMATO_XML);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/xml");

            JAXBContext jc = JAXBContext.newInstance(EnderecoDTO.class);
            InputStream xml = conn.getInputStream();
            enderecoDTO = (EnderecoDTO) jc.createUnmarshaller().unmarshal(xml);

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnknownHostException e ) {
            throw new CepBussinesException("Consulta de CEP indisponível no momento, tente novamente mais tarde!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return converterEndereco(enderecoDTO);
    }

    public Endereco converterEndereco(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();
        try {
            endereco.setCep(enderecoDTO.getCep());
            endereco.setBairro(enderecoDTO.getBairro());
            endereco.setLogradouro(enderecoDTO.getLogradouro());

        } catch (NullPointerException e) {
            return new Endereco();
        }

        return endereco;
    }

}
