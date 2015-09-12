/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBeans;

import EntityBeans.Pays;
import SessionBeans.PaysFacadeLocal;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Manotas
 */
@Named(value="paysMB")
@ApplicationScoped
public class PaysMB {
    @EJB
    private PaysFacadeLocal paysFacade;
    
    private ArrayList<Pays> pays;
    
    public PaysMB() {
    }
    
    @PostConstruct
    public void init(){
        setPays(paysFacade.findAllPays());
    }

    public ArrayList<Pays> getPays() {
        return pays;
    }
    
    public void setPays(ArrayList<Pays> pays){
        this.pays = pays;
    }
    
}