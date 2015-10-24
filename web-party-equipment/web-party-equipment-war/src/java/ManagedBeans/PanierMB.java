/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EntityBeans.Article;
import EntityBeans.Client;
import EntityBeans.Facture;
import EntityBeans.Lignecommande;
import EntityBeans.Promotion;
import SessionBeans.FactureFacadeLocal;
import SessionBeans.PromotionFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pierre
 */
@Named(value = "panierMB")
@SessionScoped
public class PanierMB implements Serializable{
    private static final String BUNDLE_LOCALE = "language.lang";
    @EJB
    private FactureFacadeLocal factureFacade;
    @EJB
    private PromotionFacadeLocal promotionFacade;
    
    private HashMap<Article,BigDecimal> panier;
    private String codePromo;
    private ArrayList<Promotion> promotions = new ArrayList<>();
    public PanierMB() {
        panier = new HashMap();
    }
    
    public void addArticle(Article article, Integer quantite){
        if(!panier.containsKey(article))
            panier.put(article, new BigDecimal(quantite));
        else
            panier.put(article, new BigDecimal(quantite + panier.get(article).intValue()));
    }
    
    public Double sommeLigne(Article article){
        Long pourcentagePromo = 0L;
        for(Promotion promo : promotions){
            if(promo.getIdArticle().getIdArticle().equals(article.getIdArticle())){
                pourcentagePromo =  promo.getPourcentage();
                break;
            }
        }
        return (1-(pourcentagePromo/100.0)) * (double) article.getPrixcatalogue().floatValue() * panier.get(article).shortValue();

    }
    
    public void removeArticle(Article article){
        panier.remove(article);
        removeArticlePromo(article);       
        codePromo = null;
    }
    
    public void clearPanier(){
        panier.clear();
        promotions.clear();
        codePromo = null;
    }

    public String getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }    

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }
    
    public void validerCodePromo(){
        Promotion promo = promotionFacade.findByCodePromo(codePromo);
        if(promo != null)
            promotions.add(promo);
        codePromo = null;
    } 
    
    public boolean panierVide(){
        return panier.isEmpty();
    }
    
    public HashMap<Article, BigDecimal> getPanier() {
        return panier;
    }

    public void setPanier(HashMap<Article, BigDecimal> panier) {
        this.panier = panier;
    }
    
    public List<Map.Entry<Article,Integer>> getListePanier(){
        return new ArrayList(panier.entrySet());
    }
  
    public Double sommePanier(){
        double somme = 0.;
        for (Map.Entry<Article, BigDecimal> article : panier.entrySet()) {
            somme += sommeLigne(article.getKey());
        }
        return somme;
    }
    
    public void removeArticlePromo(Article article){
        /*for(Promotion promo : promotions){
            if(article.getIdArticle().equals(promo.getIdArticle().getIdArticle()))
                promotions.remove(promo);
        } TO DO */ 
    }

    private void goToCommande() throws IOException{
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("commande.xhtml");
        }
        catch(IOException e){
            System.out.println("error redirect.");            
        }
    }
    
    
    public void validerCommande(){
        try{
            Facture facture = creationFacture();
            factureFacade.create(conversionLigneCommande(), facture);
            clearPanier();
            FacesContext.getCurrentInstance().getExternalContext().redirect(
            "facture.xhtml?factureID=" + facture.getIdFacture());
        }
        catch(Exception e){
            redirectToPage("erreur.xhtml");
        }        
    }
    
    public void redirectToPage(String page){
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        }
        catch(Exception e){
            System.out.println("Error redirect");
        }
    }

    private Facture creationFacture() {
        FacesContext ctxt = FacesContext.getCurrentInstance();
        ClientMB clientManaged = (ClientMB) ctxt.getApplication().getExpressionFactory()
                .createValueExpression(ctxt.getELContext(),"#{clientMB}",ClientMB.class)
                .getValue(ctxt.getELContext());
        Client client = clientManaged.getClient();
        Facture facture = new Facture();
        facture.setIdClient(client);
        facture.setDatefacturation(new Date());
        facture.setLignecommandeCollection(new ArrayList<>());
        return facture;
    }
    
    private ArrayList<Lignecommande> conversionLigneCommande() {
        ArrayList<Lignecommande> lignes = new ArrayList<>();
        panier.forEach((article,quantite)->{
            lignes.add(new Lignecommande(article,article.getPrixcatalogue(),
                    quantite.shortValue(), sommeLigne(article)));
        });
        return lignes;
    }
    
    private ResourceBundle getBundle(){
        return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
    
}
