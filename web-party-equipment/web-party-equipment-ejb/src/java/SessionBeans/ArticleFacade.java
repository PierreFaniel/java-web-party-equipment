/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import EntityBeans.Article;
import EntityBeans.Categorie;
import EntityBeans.Langue;
import EntityBeans.Traductionarticle;
import EntityBeans.TraductionarticlePK;
import Modele.TexteInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pierre
 */
@Stateless
public class ArticleFacade extends AbstractFacade<Article> implements ArticleFacadeLocal {
    @EJB
    private CategorieFacadeLocal categorieFacade;
    
    
    @PersistenceContext(unitName = "web-party-equipment-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
        categorieFacade = new CategorieFacade();
    }
    
    private Collection<Traductionarticle> detailsArticle(HashMap<Langue,
        TexteInfo> texteHashe, Integer idArticle){
            Collection<Traductionarticle> texteInfo = new ArrayList<>();
            for(Map.Entry<Langue, TexteInfo> info : texteHashe.entrySet()){
                texteInfo.add(new Traductionarticle(
                        new TraductionarticlePK(info.getKey().getIdLangue(), idArticle),
                        info.getValue().getLibelle(), info.getValue().getDescriptif())
                );
            }
        return texteInfo;
    }
    
    @Override
    public ArrayList<Article> findArticlesParCategorie(Categorie categorie){
        ArrayList<Article> listeArticles = new ArrayList<>();
        getArticlesParCategorie(categorie).stream().forEach((article) -> {
            listeArticles.add(article);
        });
        return listeArticles;
    }
    @Override
    public Article findArticleParId(Integer id){
        return find(id);
    }
    
    @Override
    public ArrayList<Article> getArticlesParCategorie(Categorie categorie){
        Query q;
        q = em.createNamedQuery("Article.findParCategorie");
        q.setParameter("idCategorie", categorie);
        return new ArrayList(q.getResultList());        
    }
}
