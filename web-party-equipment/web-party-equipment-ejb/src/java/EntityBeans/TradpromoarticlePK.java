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
public class TradpromoarticlePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LANGUE")
    private int idLangue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROMO")
    private int idPromo;

    public TradpromoarticlePK() {
    }

    public TradpromoarticlePK(int idLangue, int idPromo) {
        this.idLangue = idLangue;
        this.idPromo = idPromo;
    }

    public int getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(int idLangue) {
        this.idLangue = idLangue;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLangue;
        hash += (int) idPromo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradpromoarticlePK)) {
            return false;
        }
        TradpromoarticlePK other = (TradpromoarticlePK) object;
        if (this.idLangue != other.idLangue) {
            return false;
        }
        if (this.idPromo != other.idPromo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TradpromoarticlePK[ idLangue=" + idLangue + ", idPromo=" + idPromo + " ]";
    }
    
}
