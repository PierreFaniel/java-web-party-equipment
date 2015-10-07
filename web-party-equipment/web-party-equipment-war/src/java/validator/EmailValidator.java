/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;

import SessionBeans.ClientFacadeLocal;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Manotas
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
    private static final String BUNDLE_LOCALE = "language.lang",
                                EMAIL_EXISTANT = "emailExistant",
                                ERREUR_EMAIL = "emailIncorrect";
    
    ClientFacadeLocal clientFacade = lookupClientFacadeLocal();
    private String email;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        email = (String) value;
        if(clientFacade.findByEmail(email) != null)
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(EMAIL_EXISTANT)));
        if(!Pattern.matches("^[a-zA-Z0-9._-]{2,}@[a-z0-9._-]{2,}\\.[a-z]{2,4}$",email))
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(ERREUR_EMAIL)));
    
    }
    
    private ClientFacadeLocal lookupClientFacadeLocal(){
        try {
            Context c = new InitialContext();
            return (ClientFacadeLocal) c.lookup("java:global/web-party-equipment/web-party-equipment-ejb/ClientFacade!SessionBeans.ClientFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    
    }
    
    private ResourceBundle linkToBundle(FacesContext context){
        Locale locale = context.getViewRoot().getLocale();
        return ResourceBundle.getBundle(BUNDLE_LOCALE, locale);
    }
}
