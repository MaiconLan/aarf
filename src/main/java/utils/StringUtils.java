package utils;

public class StringUtils {

    public static String removerCaracteres(String valor){
        return valor.replace(".", "")
                .replace("-", "")
                .replace("/", "")
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "");
    }
}
