/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pierre
 */
@Entity
@Table(name = "LIGNECOMMANDE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lignecommande.findAll", query = "SELECT l FROM Lignecommande l"),
    @NamedQuery(name = "Lignecommande.findByIdLignecommande", query = "SELECT l FROM Lignecommande l WHERE l.idLignecommande = :idLignecommande"),
    @NamedQuery(name = "Lignecommande.findByPrixarticle", query = "SELECT l FROM Lignecommande l WHERE l.prixarticle = :prixarticle"),
    @NamedQuery(name = "Lignecommande.findByQuantite", query = "SELECT l FROM Lignecommande l WHERE l.quantite = :quantite")})
public class Lignecommande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LIGNECOMMANDE")
    private Integer idLignecommande;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIXARTICLE")
    private Double prixarticle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITE")
    private short quantite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIXLIGNE")
    private Double prixLigne;
    @JoinColumn(name = "ID_ARTICLE", referencedColumnName = "ID_ARTICLE")
    @ManyToOne(optional = false)
    private Article idArticle;
    @JoinColumn(name = "ID_FACTURE", referencedColumnName = "ID_FACTURE")
    @ManyToOne(optional = false)
    private Facture idFacture;

    public Lignecommande() {
    }

    public Lignecommande(Integer idLignecommande) {
        this.idLignecommande = idLignecommande;
    }

    public Lignecommande(Article article, Double prixarticle, short quantite, Double prixLigne) {
        idArticle = article;
        this.prixarticle = prixarticle;
        this.quantite = quantite;
        this.prixLigne = prixLigne;
    }

    public Integer getIdLignecommande() {
        return idLignecommande;
    }

    public void setIdLignecommande(Integer idLignecommande) {
        this.idLignecommande = idLignecommande;
    }

    public Double getPrixarticle() {
        return prixarticle;
    }

    public void setPrixarticle(Double prixarticle) {
        this.prixarticle = prixarticle;
    }

    public short getQuantite() {
        return quantite;
    }

    public void setQuantite(short quantite) {
        this.quantite = quantite;
    }

    public Article getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Article idArticle) {
        this.idArticle = idArticle;
    }

    public Facture getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Facture idFacture) {
        this.idFacture = idFacture;
    }

    public Double getPrixLigne() {
        return prixLigne;
    }

    public void setPrixLigne(Double prixLigne) {
        this.prixLigne = prixLigne;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLignecommande != null ? idLignecommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lignecommande)) {
            return false;
        }
        Lignecommande other = (Lignecommande) object;
        if ((this.idLignecommande == null && other.idLignecommande != null) || (this.idLignecommande != null && !this.idLignecommande.equals(other.idLignecommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Lignecommande[ idLignecommande=" + idLignecommande + " ]";
    }
    
}
