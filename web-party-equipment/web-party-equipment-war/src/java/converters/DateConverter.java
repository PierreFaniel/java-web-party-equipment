package converters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Date.class)
public class DateConverter implements Converter {
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        try{
            Date date = dateFormat.parse(value);
            return date;
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return dateFormat.format((Date)value);
    }
}
