/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import EntityBeans.Article;
import EntityBeans.Categorie;
import SessionBeans.ArticleFacadeLocal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Pierre
 */
@Named(value = "articleMB")
@Dependent
public class ArticleMB {
    @EJB
    private ArticleFacadeLocal articleFacade;
    private ArrayList<Article> listeArticle;
    private String libCategorie;
    private Article article;
    private Integer quantiteArticle = 1;

    
    public ArticleMB() {
    }
    
    public String getLibCategorie() {
        return libCategorie;
    }

    public void setLibCategorie(String libCategorie) {
        this.libCategorie = libCategorie;
    }
    
    public ArrayList<Article> findArticleParCategorie(Categorie categorie) {
        return articleFacade.findArticlesParCategorie(categorie);
    }
    
    public Article findArticleParId(Integer idArticle){
        return articleFacade.findArticleParId(idArticle);
    }

    public ArrayList<Article> getListeArticle() {
        return listeArticle;
    }

    public void setListeArticle(ArrayList<Article> listeArticle) {
        this.listeArticle = listeArticle;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public ArticleFacadeLocal getArticleFacade() {
        return articleFacade;
    }

    public void setArticleFacade(ArticleFacadeLocal articleFacade) {
        this.articleFacade = articleFacade;
    }

    public Integer getQuantiteArticle() {
        return quantiteArticle;
    }

    public void setQuantiteArticle(Integer quantiteArticle) {
        this.quantiteArticle = quantiteArticle;
    }
}
