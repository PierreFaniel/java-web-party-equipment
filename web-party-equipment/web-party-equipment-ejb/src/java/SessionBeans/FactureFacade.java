/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Facture;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pierre
 */
@Stateless
public class FactureFacade extends AbstractFacade<Facture> implements FactureFacadeLocal {
    @EJB
    private LignecommandeFacadeLocal lignecommandeFacade;
    
    @EJB
    private ClientFacadeLocal clientFacade;
    
    @EJB
    private AdresseFacadeLocal adresseFacade;
    
    @PersistenceContext(unitName = "web-party-equipment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactureFacade() {
        super(Facture.class);
    }
    
    @Override
    public ArrayList<Facture> findParClient(Integer idClient) throws Exception{
        try{
            Query query = em.createNamedQuery("Facture.findByIdClient");
            query.setParameter("idClient", idClient);
            ArrayList<Facture> factures = new ArrayList();
            query.getResultList().stream().forEach((facture) ->{
                factures.add((Facture)facture);
            });
            return factures;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
}
