package utils;

import java.util.ArrayList;
import java.util.List;

public class EnderecoUtils {

    private static List<String> estados = new ArrayList<>();

    private static void popuplarUfs() {
        estados.add("AC");
        estados.add("AL");
        estados.add("AM");
        estados.add("AP");
        estados.add("BA");
        estados.add("CE");
        estados.add("DF");
        estados.add("ES");
        estados.add("GO");
        estados.add("MA");
        estados.add("MG");
        estados.add("MS");
        estados.add("MT");
        estados.add("PA");
        estados.add("PB");
        estados.add("PE");
        estados.add("PI");
        estados.add("PR");
        estados.add("RJ");
        estados.add("RN");
        estados.add("RO");
        estados.add("RR");
        estados.add("RS");
        estados.add("SC");
        estados.add("SE");
        estados.add("SP");
        estados.add("TO");
    }

    public static List<String> getEstados() {
        if(estados.isEmpty())
            popuplarUfs();
        return estados;
    }
}
