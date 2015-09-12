/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Promotion;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pierre
 */
@Stateless
public class PromotionFacade extends AbstractFacade<Promotion> implements PromotionFacadeLocal {
    @PersistenceContext(unitName = "web-party-equipment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PromotionFacade() {
        super(Promotion.class);
    }
    
    @Override
    public Promotion findByCodePromo(String codePromo){
        Query q = em.createNamedQuery("Promotion.findByCodepromo");
        q.setParameter("codepromo", codePromo);
        ArrayList<Promotion> promos = new ArrayList<>(q.getResultList());
        if(promos.size() > 0)
            return promos.get(0);
        else
            return null;
    }
}
