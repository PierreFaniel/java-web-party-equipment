package ManagedBeans;

import EntityBeans.Adresse;
import EntityBeans.Client;
import EntityBeans.InfosConnexion;
import Modele.Encryption;
import SessionBeans.ClientFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "clientMB")
@SessionScoped
public class ClientMB implements Serializable {
    private static final String BUNDLE_LOCALE = "language.lang";
 
    @EJB
    private ClientFacadeLocal clientFacade;
    
    private Client client;
    private InfosConnexion infosConnexion;
    private Adresse adresse;
    private String pagePrecedente;
    
    public ClientMB() {
        client = new Client();
        client.setIdAdresse(new Adresse());
        infosConnexion = new InfosConnexion();
    }
    
    public String inscription(){
        try{
            testNumTel();
            clientFacade.create(client, getBundle());
            return "inscriptionReussie";
        }
        catch (Exception e){
            System.out.println(e);
            return "erreur";
        }
    }
    public void testNumTel(){
        if(client.getNumtelephone().equals(""))
            client.setNumtelephone(null);
    }
        
    public void deconnexion() throws IOException{
        infosConnexion.setConnecte(false);
        client = new Client();
        retourIndex();
    } 

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public InfosConnexion getInfosConnexion() {
        return infosConnexion;
    }

    public void setInfosConnexion(InfosConnexion infosConnexion) {
        this.infosConnexion = infosConnexion;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getPagePrecedente() {
        return pagePrecedente;
    }

    public void setPagePrecedente(String pagePrecedente) {
        this.pagePrecedente = pagePrecedente;
    }
    
    
    public void retourIndex() throws IOException{
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("index.xhtml");
        }
        catch(IOException e){
            System.out.println("error redirect.");            
        }
    }
    
    public String connexion(){
        client = clientFacade.findByEmail(infosConnexion.getEmail());
    
        if(infosConnexonCorrectes()) {
            infosConnexion.setConnecte(true);
            infosConnexion.setEmail("");
            if(pageActuelle().equals("connect.xhtml"))
                return pagePrecedente;
            return "";
        }
        else
        {
            if(!pageActuelle().equals("connect.xhtml"))
                pagePrecedente = pageActuelle();
            return "connect";
        }
    }
    
    public void redirectionErreur() throws IOException{
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("erreur.xhtml");
        }
        catch(IOException e){
            System.out.println("error redirect.");
        }
    }
    
    public void redirectionConnexion()throws IOException{
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("connect.xhtml");
        }
        catch(IOException e){
            System.out.println("error redirect.");
        }
    }
    
    private boolean infosConnexonCorrectes() {
        String motDePasse = Encryption.encryption(
                infosConnexion.getMotDePasse());
        return client != null && motDePasse.equals(
                client.getMotdepasse());
    }
    
     public String pageActuelle(){
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }
     
    public String editerClient(){
        pagePrecedente = pageActuelle();
        return "editerClient";        
    }
    
    public String editionClient(){
        //clientFacade.edit(clientFacade.converterToEntity(client));
        return pagePrecedente;
    }
    
    private ResourceBundle getBundle() {
            return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    
    }    
}
