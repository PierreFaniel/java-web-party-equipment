package ManagedBeans;

import EntityBeans.Facture;
import SessionBeans.FactureFacadeLocal;
import SessionBeans.LignecommandeFacadeLocal;
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
    
    public ArrayList<Facture> getListeFacture(Integer idClient){
        return factureFacade.findParClient(idClient);
    }
    
    public Integer getIdFac() {
        return idFac;
    }

    public void setIdFac(Integer idFac) {
        this.idFac = idFac;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
    
    public void initFactureById(Integer id){
        setFacture(factureFacade.getFactureById(id));
    }
           
}
