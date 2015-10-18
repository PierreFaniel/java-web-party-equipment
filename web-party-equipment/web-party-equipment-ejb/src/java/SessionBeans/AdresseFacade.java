/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Adresse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pierre
 */
@Stateless
public class AdresseFacade extends AbstractFacade<Adresse> implements AdresseFacadeLocal {
    @PersistenceContext(unitName = "web-party-equipment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public Integer getNewId(){
        Query q = em.createQuery("SELECT Max(a.idAdresse) FROM Adresse a");
        Integer id = (Integer)q.getSingleResult();
        if (id == null)
            return 1;
        else
            return (id + 1) ;
    }

    public AdresseFacade() {
        super(Adresse.class);
    }
    
}
