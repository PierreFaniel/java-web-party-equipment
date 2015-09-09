/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Pays;
import EntityBeans.Traductionpays;
import EntityBeans.TraductionpaysPK;
import EntityBeans.Langue;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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
    
//    @Override
//    public Pays converterToEntity(Modele.Pays pays){
//        Pays entity = new Pays(pays.getId());
//        entity.setTraductionpaysCollection(getAllPays(pays.getHashLibelle(),pays.getId()));
//        return entity;
//    }
//
//    @Override
//    public Modele.Pays converterToModel(Pays entity) {
//        Modele.Pays pays = new Modele.Pays(entity.getIdPays());
//        for (Traductionpays tradPays: entity.getTraductionpaysCollection() ){
//            pays.addLibelle(langueFacade.converterToModel(tradPays.getLangue()), tradPays.getLibelle());
//            
//        }
//        return pays;
//    }
    
    @Override
    public ArrayList<Pays> findAllPays(){
        ArrayList<Pays> listePays = new ArrayList();
        findAll().stream().forEach((pays)-> {
            listePays.add(pays);
        });
        return listePays;
    }

    private Collection<Traductionpays> getAllPays(HashMap<Langue, String> hashLibelle, Integer id) {
        Collection<Traductionpays> labels = new ArrayList();
        for (HashMap.Entry<Langue, String> libelle : hashLibelle.entrySet()) {
            labels.add(new Traductionpays(new TraductionpaysPK(
                    id, libelle.getKey().getIdLangue()),libelle.getValue()));
        }
        return labels;
    }  
    
}
