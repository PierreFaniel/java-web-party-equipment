package ManagedBeans;

import EntityBeans.Adresse;
import EntityBeans.Client;
import Modele.Encryption;
import SessionBeans.ClientFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
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
    private String motDePasse = "";
    private String email = "";
    private boolean connecte = false;
    
    public ClientMB() {
    }
    
    @PostConstruct
    public void init(){        
        client = new Client();
        client.setIdAdresse(new Adresse());
    }    
    
    public String inscription(){
        try{
            testNumTel();
            clientFacade.create(client, getBundle());
            return "inscriptionReussie";
        }
        catch (Exception e){
            return "erreur";
        }
    }
    public void testNumTel(){
        if(client.getNumtelephone().equals(""))
            client.setNumtelephone(null);
    }
        
    public void deconnexion(){
        setConnecte(false);
        client = new Client();
        client.setIdAdresse(new Adresse());
    } 

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        client = clientFacade.findByEmail(email);
    
        if(infosConnexionCorrectes()) {
            setConnecte(true);
            return "index";
        }
        else
        {
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
    
    private boolean infosConnexionCorrectes() {
        String mdp = Encryption.encryption(
                motDePasse);
        return client != null && mdp.equals(
                client.getMotdepasse());
    }
    
     public String pageActuelle(){
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }
     
    public String editerClient(){
        return "editerClient";        
    }
    
    public String editionClient(){
        //clientFpagePrecedenteacade.edit(clientFacade.converterToEntity(client)); TO DO
        return "index";
    }
    
    private ResourceBundle getBundle() {
            return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    
    }    

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isConnecte() {
        return connecte;
    }

    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }
}
