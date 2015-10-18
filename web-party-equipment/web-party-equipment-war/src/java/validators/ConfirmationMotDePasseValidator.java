/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validators;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Manotas
 */
@FacesValidator("confirmationMotDePasseValidator")
public class ConfirmationMotDePasseValidator implements Validator {
    

    private static final String BUNDLE_LOCALE = "language.lang",
                                CHAMP_MOTDEPASSE = "composantMotDePasse",
                                MESSAGE_ERREUR = "motsDePasseDifferents"; 
    private String motDePasse,
                   confirmationMotDePasse;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        confirmationMotDePasse = (String)value;
        motDePasse = getField(component, CHAMP_MOTDEPASSE);
        if(!motDePasse.equals(confirmationMotDePasse)){
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(MESSAGE_ERREUR)));
        }
    }
    
    protected String getField(UIComponent component, String nameComponent){
            UIInput componentField  =(UIInput) component.getAttributes().get(nameComponent);
            return (String) (componentField.getSubmittedValue() == null ? 
                    componentField.getValue() : componentField.getSubmittedValue());
    }
    
    protected ResourceBundle linkToBundle(FacesContext context){
        Locale locale = context.getViewRoot().getLocale();
        return ResourceBundle.getBundle(BUNDLE_LOCALE, locale);
    }
}
