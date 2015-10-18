/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import EntityBeans.Pays;
import SessionBeans.PaysFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Pierre
 */
@FacesConverter(forClass = Pays.class)
public class PaysConverter implements Converter {
    PaysFacadeLocal paysFacade = getPaysFacadeLocal();
    
    public Object getAsObject(FacesContext context, UIComponent component, String value)
    {
        return paysFacade.find(Integer.valueOf(value));
    }

    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return String.valueOf(((Pays)value).getIdPays());
    }

    private PaysFacadeLocal getPaysFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PaysFacadeLocal) c.lookup("java:global/web-party-equipment/web-party-equipment-ejb/PaysFacade!SessionBeans.PaysFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
