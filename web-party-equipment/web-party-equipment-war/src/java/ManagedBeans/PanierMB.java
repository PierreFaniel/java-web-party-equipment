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
//        Integer pourcentagePromo = 0;
//        for(Promotion promo : promotions){
//            if(promo.getArticleEnPromo().getId().equals(article.getId())){
//                pourcentagePromo =  promo.getPourcentage();
//                break;
//            }
//        }
//        return (article.getPrixcatalogue() * (1-(pourcentagePromo/100))
//                * panier.get(article).shortValue());
        return (double)(article.getPrixcatalogue().shortValue() * panier.get(article).shortValue());

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
        for(Promotion promo : promotions){
//            if(article.getId().equals(promo.getArticleEnPromo().getId()))
//                promotions.remove(promo);
        }
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
    
    
    public String validerCommande(){
        try{
//            Facture facture = creationFacture();
//            factureFacade.create(getBundle(), facture);
            clearPanier();
            return "commandeEffectuee";
        }
        catch(Exception e){
            return "erreur";
        }        
    }

//    private Facture creationFacture() {
//        FacesContext ctxt = FacesContext.getCurrentInstance();
//        ClientMB clientManaged = (ClientMB) ctxt.getApplication().getExpressionFactory()
//                .createValueExpression(ctxt.getELContext(),"#{clientMB}",ClientMB.class)
//                .getValue(ctxt.getELContext());
//        Client client = clientManaged.getClient();
//        Facture facture = new Facture(null, new Date(),
//                client.getAdresse(), client);
////        facture.setLignesCmd(conversionLigneCommande());
////        facture.setPromotions(promotions);
//        return facture;
//    }
    
    private ArrayList<Lignecommande> conversionLigneCommande() {
        ArrayList<Lignecommande> lignes = new ArrayList<>();
        panier.forEach((article,quantite)->{
//            lignes.add(new Lignecommande(article.getIdArticle(),article.getPrixcatalogue(), quantite.intValue(), article));
        });
        return lignes;
    }
    
    private ResourceBundle getBundle(){
        return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
    
}
