/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Danie
 */
@Entity
@Table(name = "vendas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendas.findAll", query = "SELECT v FROM Vendas v")
    , @NamedQuery(name = "Vendas.findByIdVenda", query = "SELECT v FROM Vendas v WHERE v.idVenda = :idVenda")
    , @NamedQuery(name = "Vendas.findByIdCaixaPessoa", query = "SELECT v FROM Vendas v WHERE v.idCaixaPessoa = :idCaixaPessoa")
    , @NamedQuery(name = "Vendas.findByValorTotal", query = "SELECT v FROM Vendas v WHERE v.valorTotal = :valorTotal")
    , @NamedQuery(name = "Vendas.findByFormaPgto", query = "SELECT v FROM Vendas v WHERE v.formaPgto = :formaPgto")
    , @NamedQuery(name = "Vendas.findByDataVenda", query = "SELECT v FROM Vendas v WHERE v.dataVenda = :dataVenda")})
public class Vendas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVenda")
    private Integer idVenda;
    @Basic(optional = false)
    @Column(name = "idCaixaPessoa")
    private int idCaixaPessoa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorTotal")
    private Double valorTotal;
    @Column(name = "valorPago")
    private Double valorPago;
    @Column(name = "valorTroco")
    private Double valorTroco;
    @Column(name = "formaPgto")
    private String formaPgto;
    @Column(name = "dataVenda")
    @Temporal(TemporalType.DATE)
    private Date dataVenda;

    public Vendas() {
    }

    public Vendas(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Vendas(int idCaixaPessoa, Double valorTotal, Double valorPago, Double valorTroco, String formaPgto, Date dataVenda) {
        this.idCaixaPessoa = idCaixaPessoa;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.valorTroco = valorTroco;
        this.formaPgto = formaPgto;
        this.dataVenda = dataVenda;
    }
    
    


    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdCaixaPessoa() {
        return idCaixaPessoa;
    }

    public void setIdCaixaPessoa(int idCaixaPessoa) {
        this.idCaixaPessoa = idCaixaPessoa;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenda != null ? idVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendas)) {
            return false;
        }
        Vendas other = (Vendas) object;
        if ((this.idVenda == null && other.idVenda != null) || (this.idVenda != null && !this.idVenda.equals(other.idVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.domain.Vendas[ idVenda=" + idVenda + " ]";
    }
    
}
