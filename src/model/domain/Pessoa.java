/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Danie
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
    , @NamedQuery(name = "Pessoa.findByIdPessoa", query = "SELECT p FROM Pessoa p WHERE p.idPessoa = :idPessoa")
    , @NamedQuery(name = "Pessoa.findByNomePessoa", query = "SELECT p FROM Pessoa p WHERE p.nomePessoa = :nomePessoa")
    , @NamedQuery(name = "Pessoa.findByEmailPessoa", query = "SELECT p FROM Pessoa p WHERE p.emailPessoa = :emailPessoa")
    , @NamedQuery(name = "Pessoa.findByTelefonePessoa", query = "SELECT p FROM Pessoa p WHERE p.telefonePessoa = :telefonePessoa")
    , @NamedQuery(name = "Pessoa.findByCargoPessoa", query = "SELECT p FROM Pessoa p WHERE p.cargoPessoa = :cargoPessoa")
    , @NamedQuery(name = "Pessoa.findBySenhaPessoa", query = "SELECT p FROM Pessoa p WHERE p.senhaPessoa = :senhaPessoa")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPessoa")
    private Integer idPessoa;
    @Column(name = "nomePessoa")
    private String nomePessoa;
    @Column(name = "emailPessoa")
    private String emailPessoa;
    @Column(name = "telefonePessoa")
    private String telefonePessoa;
    @Column(name = "cargoPessoa")
    private String cargoPessoa;
    @Basic(optional = false)
    @Column(name = "senhaPessoa")
    private String senhaPessoa;

    public Pessoa() {
    }

   
    public Pessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Pessoa(Integer idPessoa, String senhaPessoa) {
        this.idPessoa = idPessoa;
        this.senhaPessoa = senhaPessoa;
    }
    
    public Pessoa(String nomePessoa, String emailPessoa, String telefonePessoa, String cargoPessoa, String senhaPessoa) {
        this.nomePessoa = nomePessoa;
        this.emailPessoa = emailPessoa;
        this.telefonePessoa = telefonePessoa;
        this.cargoPessoa = cargoPessoa;
        this.senhaPessoa = senhaPessoa;
    }


    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getEmailPessoa() {
        return emailPessoa;
    }

    public void setEmailPessoa(String emailPessoa) {
        this.emailPessoa = emailPessoa;
    }

    public String getTelefonePessoa() {
        return telefonePessoa;
    }

    public void setTelefonePessoa(String telefonePessoa) {
        this.telefonePessoa = telefonePessoa;
    }

    public String getCargoPessoa() {
        return cargoPessoa;
    }

    public void setCargoPessoa(String cargoPessoa) {
        this.cargoPessoa = cargoPessoa;
    }

    public String getSenhaPessoa() {
        return senhaPessoa;
    }

    public void setSenhaPessoa(String senhaPessoa) {
        this.senhaPessoa = senhaPessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoa != null ? idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.domain.Pessoa[ idPessoa=" + idPessoa + " ]";
    }
    
}
