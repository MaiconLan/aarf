package business;

import dto.EnderecoDTO;
import model.Endereco;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class CepBussines {

    public String URL_API_POSTMON = "https://api.postmon.com.br/v1/cep/";
    public String FORMATO_XML = "?format=xml";

    public Endereco getEnderecoCompleto(String cep) {
        EnderecoDTO enderecoDTO = null;

        try {
            URL url = new URL(URL_API_POSTMON + cep + FORMATO_XML);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
        //return converterEndereco(enderecoDTO);
    }

    public void teste(String cep){

    }
/*
    public Endereco converterEndereco(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();
        try {
            endereco.setCep(enderecoDTO.getCep());
            endereco.setBairro(enderecoDTO.getBairro());
            endereco.setLogradouro(enderecoDTO.getLogradouro());
            endereco.setCidade(enderecoDTO.getCidade());
            endereco.setEstado(enderecoDTO.getEstado());
        } catch (NullPointerException e) {
            return new Endereco();
        }

        return endereco;
    }

    public EnderecoDTO converterEndereco(Endereco endereco){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        try {
            enderecoDTO.setCep(endereco.getCep());
            enderecoDTO.setBairro(endereco.getBairro());
            enderecoDTO.setLogradouro(endereco.getLogradouro());
            enderecoDTO.setCidade(endereco.getCidade());
            enderecoDTO.setEstado(endereco.getEstado());
        } catch (NullPointerException e) {
            return new EnderecoDTO();
        }

        return enderecoDTO;
    }
    */

}
