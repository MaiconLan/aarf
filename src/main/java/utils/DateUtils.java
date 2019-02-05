package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String FORMATO_DIA_MES_ANO = "dd/MM/yyyy";

    public static String FORMATO_DIA_MES_ANO_HORA_MINUTO = "dd/MM/yyyy HH:mm";

    public static String formatoDataHora(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DIA_MES_ANO);
        return data.format(formatter);
    }
}
