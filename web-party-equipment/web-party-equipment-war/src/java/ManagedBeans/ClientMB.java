package ManagedBeans;

import EntityBeans.Adresse;
import EntityBeans.Client;
import Modele.Encryption;
import SessionBeans.AdresseFacadeLocal;
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
    
    @EJB
    private AdresseFacadeLocal adresseFacade;
    
    private Client client;
    private String motDePasse = "";
    private String email = "";
    private boolean connecte = false;
    private Adresse nouvelleAdresse;
    private Client copieClient;
    
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
        
    public String deconnexion(){
        setConnecte(false);
        client = new Client();
        client.setIdAdresse(new Adresse());
        return "index";
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
    
    private ResourceBundle getBundle() {
            return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    
    }    

    public String supprimerCompte(){
        return "index";
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

    public Adresse getNouvelleAdresse() {
        return nouvelleAdresse;
    }

    public void setNouvelleAdresse(Adresse nouvelleAdresse) {
        this.nouvelleAdresse = nouvelleAdresse;
    }

    public Client getCopieClient() {
        return copieClient;
    }

    public void setCopieClient(Client copieClient) {
        this.copieClient = copieClient;
    }
    
    public String editerInformationsClient(){
        copieClient = copierClient(client);
        return "editerInfosClient";
    }
    
    public String changerInformationsClient(){
        clientFacade.edit(copieClient);
        client = copierClient(copieClient);
        return "clientActions";
    }
    
    private Client copierClient (Client oldClient){
        Client clientCopy = new Client();
        clientCopy.setEmail(oldClient.getEmail());
        clientCopy.setMotdepasse(oldClient.getMotdepasse());
        clientCopy.setNom(oldClient.getNom());
        clientCopy.setPrenom(oldClient.getPrenom());
        clientCopy.setIdAdresse(oldClient.getIdAdresse());
        clientCopy.setIdClient(oldClient.getIdClient());
        clientCopy.setNumtelephone(oldClient.getNumtelephone());
        return clientCopy;
    }
    
    
    public String changerAdresse(){
        adresseFacade.edit(nouvelleAdresse);
        client.setIdAdresse(nouvelleAdresse);
        return "clientActions";
    }
    
    public String editerAdresse(){
        nouvelleAdresse = new Adresse();
        nouvelleAdresse.setIdAdresse(client.getIdAdresse().getIdAdresse());
        nouvelleAdresse.setIdPays(client.getIdAdresse().getIdPays());
        nouvelleAdresse.setCodepostal(client.getIdAdresse().getCodepostal());
        nouvelleAdresse.setLocalite(client.getIdAdresse().getLocalite());
        nouvelleAdresse.setRue(client.getIdAdresse().getRue());
        nouvelleAdresse.setNumero(client.getIdAdresse().getNumero());
        return "editerAdresse";
    }
}
