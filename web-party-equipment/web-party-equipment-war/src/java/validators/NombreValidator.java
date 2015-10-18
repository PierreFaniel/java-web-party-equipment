/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validators;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Manotas
 */
@FacesValidator("nombreValidator")
public class NombreValidator implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try{
            BigDecimal nb = (BigDecimal)value;
            if( nb.intValue() <= 0)
                throw new ValidatorException(
                    new FacesMessage("erreur"));
        }
        catch(Exception e)
        {
            throw new ValidatorException(
                    new FacesMessage("erreur"));
        }
    }
    
}
