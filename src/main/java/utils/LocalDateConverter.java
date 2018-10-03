package utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FacesConverter(value="localDateConverter")
public class LocalDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.FORMATO_DIA_MES_ANO);
        return LocalDate.parse(value, formatter);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        LocalDate dateValue = (LocalDate) value;

        return dateValue.format(DateTimeFormatter.ofPattern(DateUtils.FORMATO_DIA_MES_ANO));
    }

}