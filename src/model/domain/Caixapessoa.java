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
@Table(name = "caixapessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caixapessoa.findAll", query = "SELECT c FROM Caixapessoa c")
    , @NamedQuery(name = "Caixapessoa.findByIdCaixaPessoa", query = "SELECT c FROM Caixapessoa c WHERE c.idCaixaPessoa = :idCaixaPessoa")
    , @NamedQuery(name = "Caixapessoa.findByIdPessoa", query = "SELECT c FROM Caixapessoa c WHERE c.idPessoa = :idPessoa")
    , @NamedQuery(name = "Caixapessoa.findByIdCaixa", query = "SELECT c FROM Caixapessoa c WHERE c.idCaixa = :idCaixa")})
public class Caixapessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCaixaPessoa")
    private Integer idCaixaPessoa;
    @Basic(optional = false)
    @Column(name = "idPessoa")
    private int idPessoa;
    @Basic(optional = false)
    @Column(name = "idCaixa")
    private int idCaixa;

    public Caixapessoa() {
    }

    public Caixapessoa(Integer idCaixaPessoa) {
        this.idCaixaPessoa = idCaixaPessoa;
    }

    public Caixapessoa(int idPessoa, int idCaixa) {
        this.idPessoa = idPessoa;
        this.idCaixa = idCaixa;
    }
    
    
    public Caixapessoa(Integer idCaixaPessoa, int idPessoa, int idCaixa) {
        this.idCaixaPessoa = idCaixaPessoa;
        this.idPessoa = idPessoa;
        this.idCaixa = idCaixa;
    }

    public Integer getIdCaixaPessoa() {
        return idCaixaPessoa;
    }

    public void setIdCaixaPessoa(Integer idCaixaPessoa) {
        this.idCaixaPessoa = idCaixaPessoa;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCaixaPessoa != null ? idCaixaPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caixapessoa)) {
            return false;
        }
        Caixapessoa other = (Caixapessoa) object;
        if ((this.idCaixaPessoa == null && other.idCaixaPessoa != null) || (this.idCaixaPessoa != null && !this.idCaixaPessoa.equals(other.idCaixaPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.domain.Caixapessoa[ idCaixaPessoa=" + idCaixaPessoa + " ]";
    }
    
}
