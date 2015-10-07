/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;

import EntityBeans.Article;
import EntityBeans.Promotion;
import SessionBeans.PromotionFacadeLocal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ManagedBeans.PanierMB;

/**
 *
 * @author Manotas
 */
@FacesValidator("promotionValidator")
public class PromotionValidator implements Validator {
    

    private static final String BUNDLE_LOCALE = "language.lang",
                                PROMO_DEJA_UTILISEE = "promoDejaUtilisee",
                                PROMO_INCORRECTE = "promoIncorrecte"; 
   
    PromotionFacadeLocal promotionFacade = lookupPromotionFacadeLocal();
    private Promotion promotion;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if((!String.valueOf(value).equals(null)) && (!String.valueOf(value).equals(""))){
            promotion = promotionFacade.findByCodePromo(String.valueOf(value));
            if(promotion == null){
                throw new ValidatorException(
                        new FacesMessage(linkToBundle(context).getString(PROMO_INCORRECTE)));
            }else{
                if(promoDejaUtilisee(promotion)){
                    throw new ValidatorException(
                            new FacesMessage(linkToBundle(context).getString(PROMO_DEJA_UTILISEE)));
                }else{
//                    if(articleNonPresent(promotion.getArticleEnPromo())){
                        throw new ValidatorException(
                            new FacesMessage(linkToBundle(context).getString(PROMO_INCORRECTE)));
//                    }
                }
            }
        }
    }
    
    private boolean promoDejaUtilisee(Promotion promo){
        ArrayList<Promotion> promos = getPanier().getPromotions();
        if((promos == null )||(promos.size()<=0))
            return false;
        else
        {
            for(Promotion promotion : promos){
                if(promo.equals(promotion)){                    
                    return true;
                }
            }
            return false;
        }
    }
     
    private PanierMB getPanier(){
        FacesContext context = FacesContext.getCurrentInstance();
        return  (PanierMB) context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(), 
                                "#{panierMB}", 
                                PanierMB.class)
                        .getValue(context.getELContext());
    }
        
    private PromotionFacadeLocal lookupPromotionFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PromotionFacadeLocal) c.lookup("java:global/web-party-equipment/web-party-equipment-ejb/PromotionFacade!SessionBeans.PromotionFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private boolean articleNonPresent(Article article) {
        return getPanier().getPanier().get(article) == null;
    }
    
    protected ResourceBundle linkToBundle(FacesContext context){
        Locale locale = context.getViewRoot().getLocale();
        return ResourceBundle.getBundle(BUNDLE_LOCALE, locale);
    }
}
