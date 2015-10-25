/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Client;
import EntityBeans.Facture;
import EntityBeans.Lignecommande;
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
    public ArrayList<Facture> findParClient(Client client){
        try{
            Query query = em.createNamedQuery("Facture.findByIdClient");
            query.setParameter("idClient", client);
            ArrayList<Facture> factures = new ArrayList();
            query.getResultList().stream().forEach((facture) ->{
                factures.add((Facture)facture);
            });
            return factures;
        }
        catch(NoResultException e){
            return new ArrayList<Facture>();
        }
    }
    
    @Override
    public void create(ArrayList<Lignecommande> lignesCommande, Facture facture){
        create(facture);
        em.flush();
        attachFactureToLignes(facture, lignesCommande);
        facture.setLignecommandeCollection(lignesCommande);
        creerLignesCommande(facture);
    }

    private void creerLignesCommande(Facture facture) {
        for(Lignecommande ligne: facture.getLignecommandeCollection()){
            lignecommandeFacade.create(ligne);
        }
    }
    
    @Override
    public Facture getFactureById(Integer id){
        try{
            Query q = em.createNamedQuery("Facture.findByIdFacture");
            q.setParameter("idFacture", id);
            return (Facture)q.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }

    private void attachFactureToLignes(Facture facture, ArrayList<Lignecommande> lignesCommande) {
        for(Lignecommande ligne: lignesCommande){
            ligne.setIdFacture(facture);
        }
    }
}
