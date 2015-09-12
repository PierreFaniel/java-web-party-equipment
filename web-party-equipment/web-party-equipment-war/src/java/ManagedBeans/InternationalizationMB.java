/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EntityBeans.Langue;
import SessionBeans.LangueFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Pierre
 */
@Named(value = "internationalizationMB")
@SessionScoped
public class InternationalizationMB implements Serializable {
    @EJB
    private LangueFacadeLocal langueFacade;
    
    private Locale locale;
    private Langue langueCourante;
    private ArrayList<Langue> langues;
    
    public InternationalizationMB() {
    }
    
    @PostConstruct
    public void init(){
        setLangues(new ArrayList(langueFacade.findAll()));
        setLangueCourante(langues.get(0));
        locale = new Locale("en");
    }
    
    public Locale getLocale() {
        return locale;
    }
    public void setLocale(Langue langue){
        setLangueCourante(langue);
    }    
    
    public void setLangues(ArrayList<Langue> langues) {
        this.langues = langues;
    }

    public Langue getLangueCourante() {
        return langueCourante;
    }

    public void setLangueCourante(Langue langueCourante) {
        this.langueCourante = langueCourante;
        locale = new Locale(this.langueCourante.getAbreviation());
    }
}
