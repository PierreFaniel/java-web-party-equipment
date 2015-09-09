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
@Table(name = "LANGUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Langue.findAll", query = "SELECT l FROM Langue l"),
    @NamedQuery(name = "Langue.findByIdLangue", query = "SELECT l FROM Langue l WHERE l.idLangue = :idLangue"),
    @NamedQuery(name = "Langue.findByLibelle", query = "SELECT l FROM Langue l WHERE l.libelle = :libelle"),
    @NamedQuery(name = "Langue.findByAbreviation", query = "SELECT l FROM Langue l WHERE l.abreviation = :abreviation")})
public class Langue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LANGUE")
    private Integer idLangue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "LIBELLE")
    private String libelle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ABREVIATION")
    private String abreviation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "langue")
    private Collection<Tradpromoarticle> tradpromoarticleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "langue")
    private Collection<Traductionpays> traductionpaysCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "langue")
    private Collection<Traductioncategorie> traductioncategorieCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "langue")
    private Collection<Traductionarticle> traductionarticleCollection;

    public Langue() {
    }

    public Langue(Integer idLangue) {
        this.idLangue = idLangue;
    }

    public Langue(Integer idLangue, String libelle, String abreviation) {
        this.idLangue = idLangue;
        this.libelle = libelle;
        this.abreviation = abreviation;
    }

    public Integer getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(Integer idLangue) {
        this.idLangue = idLangue;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    @XmlTransient
    public Collection<Tradpromoarticle> getTradpromoarticleCollection() {
        return tradpromoarticleCollection;
    }

    public void setTradpromoarticleCollection(Collection<Tradpromoarticle> tradpromoarticleCollection) {
        this.tradpromoarticleCollection = tradpromoarticleCollection;
    }

    @XmlTransient
    public Collection<Traductionpays> getTraductionpaysCollection() {
        return traductionpaysCollection;
    }

    public void setTraductionpaysCollection(Collection<Traductionpays> traductionpaysCollection) {
        this.traductionpaysCollection = traductionpaysCollection;
    }

    @XmlTransient
    public Collection<Traductioncategorie> getTraductioncategorieCollection() {
        return traductioncategorieCollection;
    }

    public void setTraductioncategorieCollection(Collection<Traductioncategorie> traductioncategorieCollection) {
        this.traductioncategorieCollection = traductioncategorieCollection;
    }

    @XmlTransient
    public Collection<Traductionarticle> getTraductionarticleCollection() {
        return traductionarticleCollection;
    }

    public void setTraductionarticleCollection(Collection<Traductionarticle> traductionarticleCollection) {
        this.traductionarticleCollection = traductionarticleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLangue != null ? idLangue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Langue)) {
            return false;
        }
        Langue other = (Langue) object;
        if ((this.idLangue == null && other.idLangue != null) || (this.idLangue != null && !this.idLangue.equals(other.idLangue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Langue[ idLangue=" + idLangue + " ]";
    }
    
}
