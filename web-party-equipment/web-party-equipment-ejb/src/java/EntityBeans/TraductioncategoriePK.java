/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Pierre
 */
@Embeddable
public class TraductioncategoriePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LANGUE")
    private int idLangue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CATEGORIE")
    private int idCategorie;

    public TraductioncategoriePK() {
    }

    public TraductioncategoriePK(int idLangue, int idCategorie) {
        this.idLangue = idLangue;
        this.idCategorie = idCategorie;
    }

    public int getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(int idLangue) {
        this.idLangue = idLangue;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLangue;
        hash += (int) idCategorie;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraductioncategoriePK)) {
            return false;
        }
        TraductioncategoriePK other = (TraductioncategoriePK) object;
        if (this.idLangue != other.idLangue) {
            return false;
        }
        if (this.idCategorie != other.idCategorie) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TraductioncategoriePK[ idLangue=" + idLangue + ", idCategorie=" + idCategorie + " ]";
    }
    
}
