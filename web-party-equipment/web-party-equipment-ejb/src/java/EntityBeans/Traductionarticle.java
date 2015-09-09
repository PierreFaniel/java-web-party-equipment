/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pierre
 */
@Entity
@Table(name = "TRADUCTIONARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traductionarticle.findAll", query = "SELECT t FROM Traductionarticle t"),
    @NamedQuery(name = "Traductionarticle.findByIdLangue", query = "SELECT t FROM Traductionarticle t WHERE t.traductionarticlePK.idLangue = :idLangue"),
    @NamedQuery(name = "Traductionarticle.findByIdArticle", query = "SELECT t FROM Traductionarticle t WHERE t.traductionarticlePK.idArticle = :idArticle"),
    @NamedQuery(name = "Traductionarticle.findByLibelle", query = "SELECT t FROM Traductionarticle t WHERE t.libelle = :libelle"),
    @NamedQuery(name = "Traductionarticle.findByDescriptif", query = "SELECT t FROM Traductionarticle t WHERE t.descriptif = :descriptif")})
public class Traductionarticle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TraductionarticlePK traductionarticlePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LIBELLE")
    private String libelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPTIF")
    private String descriptif;
    @JoinColumn(name = "ID_ARTICLE", referencedColumnName = "ID_ARTICLE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "ID_LANGUE", referencedColumnName = "ID_LANGUE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Langue langue;

    public Traductionarticle() {
    }

    public Traductionarticle(TraductionarticlePK traductionarticlePK) {
        this.traductionarticlePK = traductionarticlePK;
    }

    public Traductionarticle(TraductionarticlePK traductionarticlePK, String libelle, String descriptif) {
        this.traductionarticlePK = traductionarticlePK;
        this.libelle = libelle;
        this.descriptif = descriptif;
    }

    public Traductionarticle(int idLangue, int idArticle) {
        this.traductionarticlePK = new TraductionarticlePK(idLangue, idArticle);
    }

    public TraductionarticlePK getTraductionarticlePK() {
        return traductionarticlePK;
    }

    public void setTraductionarticlePK(TraductionarticlePK traductionarticlePK) {
        this.traductionarticlePK = traductionarticlePK;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traductionarticlePK != null ? traductionarticlePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traductionarticle)) {
            return false;
        }
        Traductionarticle other = (Traductionarticle) object;
        if ((this.traductionarticlePK == null && other.traductionarticlePK != null) || (this.traductionarticlePK != null && !this.traductionarticlePK.equals(other.traductionarticlePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Traductionarticle[ traductionarticlePK=" + traductionarticlePK + " ]";
    }
    
}
