/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Langue;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pierre
 */
@Stateless
public class LangueFacade extends AbstractFacade<Langue> implements LangueFacadeLocal {
    @PersistenceContext(unitName = "web-party-equipment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LangueFacade() {
        super(Langue.class);
    }
    
}
