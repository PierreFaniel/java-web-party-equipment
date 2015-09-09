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
public class TraductionarticlePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_LANGUE")
    private int idLangue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ARTICLE")
    private int idArticle;

    public TraductionarticlePK() {
    }

    public TraductionarticlePK(int idLangue, int idArticle) {
        this.idLangue = idLangue;
        this.idArticle = idArticle;
    }

    public int getIdLangue() {
        return idLangue;
    }

    public void setIdLangue(int idLangue) {
        this.idLangue = idLangue;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idLangue;
        hash += (int) idArticle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TraductionarticlePK)) {
            return false;
        }
        TraductionarticlePK other = (TraductionarticlePK) object;
        if (this.idLangue != other.idLangue) {
            return false;
        }
        if (this.idArticle != other.idArticle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.TraductionarticlePK[ idLangue=" + idLangue + ", idArticle=" + idArticle + " ]";
    }
    
}
