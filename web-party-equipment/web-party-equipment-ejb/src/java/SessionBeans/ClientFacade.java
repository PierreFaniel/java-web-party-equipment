/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Adresse;
import EntityBeans.Client;
import Modele.Encryption;
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
public class ClientFacade extends AbstractFacade<Client> implements ClientFacadeLocal {
    @EJB
    private AdresseFacadeLocal adresseFacade;
    
    @PersistenceContext(unitName = "web-party-equipment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    
    @Override
    public void create(Client client, ResourceBundle bundle) throws Exception{
        client.setMotdepasse(Encryption.encryption(client.getMotdepasse()));
        Adresse adresse = client.getIdAdresse();
        adresseFacade.create(adresse);
        client.setIdAdresse(adresse);
        create(client);
    }
    
    @Override
    public Client findByEmail(String email){
        try{
            Query q = em.createNamedQuery("Client.findByEmail");
            q.setParameter("email", email);
            return (Client)q.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    } 
    
}
