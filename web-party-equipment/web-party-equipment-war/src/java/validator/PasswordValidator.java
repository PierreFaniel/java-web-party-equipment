package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Locale;
import java.util.ResourceBundle;

@FacesValidator("PasswordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String pw = (String) value;
        
        if (pw.length() < 6) {
            Locale locale = context.getViewRoot().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("language.lang",locale);
            FacesMessage mess = new FacesMessage(bundle.getString("lengthPasswordValidation"));
            throw new ValidatorException(mess);
        }
    }
    
}