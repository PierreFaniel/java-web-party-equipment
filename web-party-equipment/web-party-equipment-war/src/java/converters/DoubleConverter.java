package converters;

import java.text.DecimalFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Double.class)
public class DoubleConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        return Double.parseDouble(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return new DecimalFormat("#.##").format(value);
    }
}
