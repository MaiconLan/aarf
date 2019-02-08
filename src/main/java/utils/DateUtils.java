package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String FORMATO_DIA_MES_ANO = "dd/MM/yyyy";

    public static String FORMATO_DIA_MES_ANO_HORA_MINUTO = "dd/MM/yyyy HH:mm";

    public static String formatoData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DIA_MES_ANO);
        return data.format(formatter);
    }

    public static String formatoDataHora(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DIA_MES_ANO_HORA_MINUTO);
        return data.format(formatter);
    }
}
