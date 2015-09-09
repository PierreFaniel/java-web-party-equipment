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
@Table(name = "TRADUCTIONPAYS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traductionpays.findAll", query = "SELECT t FROM Traductionpays t"),
    @NamedQuery(name = "Traductionpays.findByIdPays", query = "SELECT t FROM Traductionpays t WHERE t.traductionpaysPK.idPays = :idPays"),
    @NamedQuery(name = "Traductionpays.findByIdLangue", query = "SELECT t FROM Traductionpays t WHERE t.traductionpaysPK.idLangue = :idLangue"),
    @NamedQuery(name = "Traductionpays.findByLibelle", query = "SELECT t FROM Traductionpays t WHERE t.libelle = :libelle")})
public class Traductionpays implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TraductionpaysPK traductionpaysPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "LIBELLE")
    private String libelle;
    @JoinColumn(name = "ID_LANGUE", referencedColumnName = "ID_LANGUE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Langue langue;
    @JoinColumn(name = "ID_PAYS", referencedColumnName = "ID_PAYS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pays pays;

    public Traductionpays() {
    }

    public Traductionpays(TraductionpaysPK traductionpaysPK) {
        this.traductionpaysPK = traductionpaysPK;
    }

    public Traductionpays(TraductionpaysPK traductionpaysPK, String libelle) {
        this.traductionpaysPK = traductionpaysPK;
        this.libelle = libelle;
    }

    public Traductionpays(int idPays, int idLangue) {
        this.traductionpaysPK = new TraductionpaysPK(idPays, idLangue);
    }

    public TraductionpaysPK getTraductionpaysPK() {
        return traductionpaysPK;
    }

    public void setTraductionpaysPK(TraductionpaysPK traductionpaysPK) {
        this.traductionpaysPK = traductionpaysPK;
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

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (traductionpaysPK != null ? traductionpaysPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traductionpays)) {
            return false;
        }
        Traductionpays other = (Traductionpays) object;
        if ((this.traductionpaysPK == null && other.traductionpaysPK != null) || (this.traductionpaysPK != null && !this.traductionpaysPK.equals(other.traductionpaysPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Traductionpays[ traductionpaysPK=" + traductionpaysPK + " ]";
    }
    
}
