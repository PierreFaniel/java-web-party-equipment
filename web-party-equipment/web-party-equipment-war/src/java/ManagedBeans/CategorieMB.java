/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBeans;

import EntityBeans.Categorie;
import SessionBeans.CategorieFacadeLocal;
//import SessionBeans.CategorieFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author etu23708
 */

@Named(value = "categorieMB")
@SessionScoped
public class CategorieMB implements Serializable{
    @EJB
    private CategorieFacadeLocal categorieFacade;
    
    
    private ArrayList<Categorie> categories;
    
    public CategorieMB () {
    }
    
    @PostConstruct
    public void initCategories() {
        categories = new ArrayList(categorieFacade.findAll());
    }
    
    public ArrayList<Categorie> getCategories() {
        return categories;
    }

    public CategorieFacadeLocal getCategorieFacade() {
        return categorieFacade;
    }

    public void setCategorieFacade(CategorieFacadeLocal categorieFacade) {
        this.categorieFacade = categorieFacade;
    }   
    
    public Categorie getCategorie(Integer idCat){
        for (Categorie cat : categories){
            if(cat.getIdCategorie().equals(idCat))
                return cat;
        }
        return null;
    }
    
}