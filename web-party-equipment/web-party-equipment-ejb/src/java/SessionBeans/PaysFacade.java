/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Pays;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Pierre
 */
@Stateless
public class PaysFacade extends AbstractFacade<Pays> implements PaysFacadeLocal {
    @PersistenceContext(unitName = "web-party-equipment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaysFacade() {
        super(Pays.class);
    }
    
//    private Collection<Traductionpays> getAllPays(HashMap<Langue, String> hashLibelle, Integer id) {
//        Collection<Traductionpays> labels = new ArrayList();
//        for (HashMap.Entry<Langue, String> libelle : hashLibelle.entrySet()) {
//            labels.add(new Traductionpays(new TraductionpaysPK(
//                    id, libelle.getKey().getIdLangue()),libelle.getValue()));
//        }
//        return labels;
//    }  
    
}
