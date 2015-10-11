/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EntityBeans.Promotion;
import SessionBeans.PromotionFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "promotionMB")
@SessionScoped
public class PromotionMB implements Serializable{
    @EJB
    private PromotionFacadeLocal promotionFacade;
    
    private ArrayList<Promotion> promotions;
    
    public PromotionMB() {
    }
    
    @PostConstruct
    public void init(){
        promotions = (ArrayList<Promotion>)promotionFacade.findAll();
    }

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }
}
