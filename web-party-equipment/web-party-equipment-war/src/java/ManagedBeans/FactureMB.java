package ManagedBeans;

import EntityBeans.Client;
import EntityBeans.Facture;
import EntityBeans.Lignecommande;
import SessionBeans.FactureFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
@Named(value = "factureMB")
@SessionScoped
public class FactureMB implements Serializable {
    @EJB
    private FactureFacadeLocal factureFacade;
    
    private Integer idFac;
    private Facture facture;
    
    public FactureMB() {
    }
    
    public ArrayList<Facture> getListeFacture(Client client){
        return factureFacade.findParClient(client);
    }
    
    public Integer getIdFac() {
        return idFac;
    }

    public void setIdFac(Integer idFac) {
        this.idFac = idFac;
        setFacture(factureFacade.getFactureById(idFac));
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
    
    public Double getTotalFacture(){
        Double totalFacture = 0.0;
        for(Lignecommande ligneCommande: facture.getLignecommandeCollection()){
            totalFacture += ligneCommande.getPrixLigne();
        }
        return totalFacture;
    }
           
}
