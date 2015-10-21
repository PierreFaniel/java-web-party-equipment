/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Facture;
import EntityBeans.Lignecommande;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
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
    public ArrayList<Facture> findParClient(Integer idClient){
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
            return new ArrayList<Facture>();
        }
    }
    
    @Override
    public void create(ResourceBundle bundle, Facture facture){
        facture.setIdFacture(getNewId());
        create(facture);
        em.flush();
        facture.setLignecommandeCollection(ajoutLignesCommande(facture));
        creerLignesCommande(facture);
    }

    private Collection<Lignecommande> ajoutLignesCommande(Facture facture) {
        ArrayList<Lignecommande> lignesCommande = new ArrayList<>();
        facture.getLignecommandeCollection().stream().forEach((ligneCmd) ->{
            lignesCommande.add(ligneCmd);
        });
        return lignesCommande;
    }

    private void creerLignesCommande(Facture facture) {
        facture.getLignecommandeCollection().stream().forEach((ligneCmd) ->{
            ligneCmd.setIdLignecommande(getNewId());
            lignecommandeFacade.create(ligneCmd);
        });
    }
    
    @Override
    public Integer getNewId(){
        Query q = em.createQuery("SELECT Max(f.idFacture) FROM Facture f");
        Integer id = (Integer)q.getSingleResult();
        if (id == null)
            return 1;
        else
            return (id + 1) ;
    }
    
    @Override
    public Facture getFactureById(Integer id){
        try{
            Query q = em.createNamedQuery("Facture.findByIdClient");
            q.setParameter("idFacture", id);
            return (Facture)q.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
}
