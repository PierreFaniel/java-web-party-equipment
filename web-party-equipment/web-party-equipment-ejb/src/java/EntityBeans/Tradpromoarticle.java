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
@Table(name = "TRADPROMOARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tradpromoarticle.findAll", query = "SELECT t FROM Tradpromoarticle t"),
    @NamedQuery(name = "Tradpromoarticle.findByIdLangue", query = "SELECT t FROM Tradpromoarticle t WHERE t.tradpromoarticlePK.idLangue = :idLangue"),
    @NamedQuery(name = "Tradpromoarticle.findByIdPromo", query = "SELECT t FROM Tradpromoarticle t WHERE t.tradpromoarticlePK.idPromo = :idPromo"),
    @NamedQuery(name = "Tradpromoarticle.findByLibelle", query = "SELECT t FROM Tradpromoarticle t WHERE t.libelle = :libelle")})
public class Tradpromoarticle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TradpromoarticlePK tradpromoarticlePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LIBELLE")
    private String libelle;
    @JoinColumn(name = "ID_LANGUE", referencedColumnName = "ID_LANGUE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Langue langue;
    @JoinColumn(name = "ID_PROMO", referencedColumnName = "ID_PROMO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Promotion promotion;

    public Tradpromoarticle() {
    }

    public Tradpromoarticle(TradpromoarticlePK tradpromoarticlePK) {
        this.tradpromoarticlePK = tradpromoarticlePK;
    }

    public Tradpromoarticle(TradpromoarticlePK tradpromoarticlePK, String libelle) {
        this.tradpromoarticlePK = tradpromoarticlePK;
        this.libelle = libelle;
    }

    public Tradpromoarticle(int idLangue, int idPromo) {
        this.tradpromoarticlePK = new TradpromoarticlePK(idLangue, idPromo);
    }

    public TradpromoarticlePK getTradpromoarticlePK() {
        return tradpromoarticlePK;
    }

    public void setTradpromoarticlePK(TradpromoarticlePK tradpromoarticlePK) {
        this.tradpromoarticlePK = tradpromoarticlePK;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradpromoarticlePK != null ? tradpromoarticlePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tradpromoarticle)) {
            return false;
        }
        Tradpromoarticle other = (Tradpromoarticle) object;
        if ((this.tradpromoarticlePK == null && other.tradpromoarticlePK != null) || (this.tradpromoarticlePK != null && !this.tradpromoarticlePK.equals(other.tradpromoarticlePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Tradpromoarticle[ tradpromoarticlePK=" + tradpromoarticlePK + " ]";
    }
    
}
