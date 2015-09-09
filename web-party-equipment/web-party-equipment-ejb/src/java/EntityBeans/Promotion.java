/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityBeans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pierre
 */
@Entity
@Table(name = "PROMOTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p"),
    @NamedQuery(name = "Promotion.findByIdPromo", query = "SELECT p FROM Promotion p WHERE p.idPromo = :idPromo"),
    @NamedQuery(name = "Promotion.findByCodepromo", query = "SELECT p FROM Promotion p WHERE p.codepromo = :codepromo"),
    @NamedQuery(name = "Promotion.findByPourcentage", query = "SELECT p FROM Promotion p WHERE p.pourcentage = :pourcentage")})
public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROMO")
    private Integer idPromo;
    @Size(max = 15)
    @Column(name = "CODEPROMO")
    private String codepromo;
    @Column(name = "POURCENTAGE")
    private Long pourcentage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotion")
    private Collection<Tradpromoarticle> tradpromoarticleCollection;
    @JoinColumn(name = "ID_ARTICLE", referencedColumnName = "ID_ARTICLE")
    @ManyToOne(optional = false)
    private Article idArticle;

    public Promotion() {
    }

    public Promotion(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public String getCodepromo() {
        return codepromo;
    }

    public void setCodepromo(String codepromo) {
        this.codepromo = codepromo;
    }

    public Long getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Long pourcentage) {
        this.pourcentage = pourcentage;
    }

    @XmlTransient
    public Collection<Tradpromoarticle> getTradpromoarticleCollection() {
        return tradpromoarticleCollection;
    }

    public void setTradpromoarticleCollection(Collection<Tradpromoarticle> tradpromoarticleCollection) {
        this.tradpromoarticleCollection = tradpromoarticleCollection;
    }

    public Article getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Article idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPromo != null ? idPromo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.idPromo == null && other.idPromo != null) || (this.idPromo != null && !this.idPromo.equals(other.idPromo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Promotion[ idPromo=" + idPromo + " ]";
    }
    
}
