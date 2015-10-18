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
@Table(name = "ADRESSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByIdAdresse", query = "SELECT a FROM Adresse a WHERE a.idAdresse = :idAdresse"),
    @NamedQuery(name = "Adresse.findByRue", query = "SELECT a FROM Adresse a WHERE a.rue = :rue"),
    @NamedQuery(name = "Adresse.findByNumero", query = "SELECT a FROM Adresse a WHERE a.numero = :numero"),
    @NamedQuery(name = "Adresse.findByLocalite", query = "SELECT a FROM Adresse a WHERE a.localite = :localite"),
    @NamedQuery(name = "Adresse.getMaxId", query = "SELECT Max(a.idAdresse) FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByCodepostal", query = "SELECT a FROM Adresse a WHERE a.codepostal = :codepostal")})
public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ADRESSE")
    private Integer idAdresse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "RUE")
    private String rue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private Short numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LOCALITE")
    private String localite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODEPOSTAL")
    private Integer codepostal;
    @JoinColumn(name = "ID_PAYS", referencedColumnName = "ID_PAYS")
    @ManyToOne(optional = false)
    private Pays idPays;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAdresse")
    private Collection<Client> clientCollection;

    public Adresse() {
    }

    public Adresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public Adresse(Integer idAdresse, String rue, Short numero, String localite, Integer codepostal) {
        this.idAdresse = idAdresse;
        this.rue = rue;
        this.numero = numero;
        this.localite = localite;
        this.codepostal = codepostal;
    }

    public Integer getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Short getNumero() {
        return numero;
    }

    public void setNumero(Short numero) {
        this.numero = numero;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public Integer getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(Integer codepostal) {
        this.codepostal = codepostal;
    }

    public Pays getIdPays() {
        return idPays;
    }

    public void setIdPays(Pays idPays) {
        this.idPays = idPays;
    }

    @XmlTransient
    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdresse != null ? idAdresse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.idAdresse == null && other.idAdresse != null) || (this.idAdresse != null && !this.idAdresse.equals(other.idAdresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Adresse[ idAdresse=" + idAdresse + " ]";
    }
    
}
