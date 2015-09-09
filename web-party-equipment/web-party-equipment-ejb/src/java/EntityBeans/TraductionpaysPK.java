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
public class TraductionpaysPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PAYS")
    private int idPays;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LANGUE")
    private int idLangue;

    public TraductionpaysPK() {
    }

    public TraductionpaysPK(int idPays, int idLangue) {
        this.idPays = idPays;
        this.idLangue = idLangue;
    }

    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public int getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(int idLangue) {
        this.idLangue = idLangue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPays;
        hash += (int) idLangue;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraductionpaysPK)) {
            return false;
        }
        TraductionpaysPK other = (TraductionpaysPK) object;
        if (this.idPays != other.idPays) {
            return false;
        }
        if (this.idLangue != other.idLangue) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TraductionpaysPK[ idPays=" + idPays + ", idLangue=" + idLangue + " ]";
    }
    
}
