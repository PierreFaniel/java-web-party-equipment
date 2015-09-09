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
@Table(name = "TRADUCTIONCATEGORIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traductioncategorie.findAll", query = "SELECT t FROM Traductioncategorie t"),
    @NamedQuery(name = "Traductioncategorie.findByIdLangue", query = "SELECT t FROM Traductioncategorie t WHERE t.traductioncategoriePK.idLangue = :idLangue"),
    @NamedQuery(name = "Traductioncategorie.findByIdCategorie", query = "SELECT t FROM Traductioncategorie t WHERE t.traductioncategoriePK.idCategorie = :idCategorie"),
    @NamedQuery(name = "Traductioncategorie.findByLibelle", query = "SELECT t FROM Traductioncategorie t WHERE t.libelle = :libelle")})
public class Traductioncategorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TraductioncategoriePK traductioncategoriePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LIBELLE")
    private String libelle;
    @JoinColumn(name = "ID_CATEGORIE", referencedColumnName = "ID_CATEGORIE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Categorie categorie;
    @JoinColumn(name = "ID_LANGUE", referencedColumnName = "ID_LANGUE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Langue langue;

    public Traductioncategorie() {
    }

    public Traductioncategorie(TraductioncategoriePK traductioncategoriePK) {
        this.traductioncategoriePK = traductioncategoriePK;
    }

    public Traductioncategorie(TraductioncategoriePK traductioncategoriePK, String libelle) {
        this.traductioncategoriePK = traductioncategoriePK;
        this.libelle = libelle;
    }

    public Traductioncategorie(int idLangue, int idCategorie) {
        this.traductioncategoriePK = new TraductioncategoriePK(idLangue, idCategorie);
    }

    public TraductioncategoriePK getTraductioncategoriePK() {
        return traductioncategoriePK;
    }

    public void setTraductioncategoriePK(TraductioncategoriePK traductioncategoriePK) {
        this.traductioncategoriePK = traductioncategoriePK;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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
        hash += (traductioncategoriePK != null ? traductioncategoriePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traductioncategorie)) {
            return false;
        }
        Traductioncategorie other = (Traductioncategorie) object;
        if ((this.traductioncategoriePK == null && other.traductioncategoriePK != null) || (this.traductioncategoriePK != null && !this.traductioncategoriePK.equals(other.traductioncategoriePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Traductioncategorie[ traductioncategoriePK=" + traductioncategoriePK + " ]";
    }
    
}
