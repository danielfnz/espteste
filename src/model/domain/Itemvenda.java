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
@Table(name = "itemvenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itemvenda.findAll", query = "SELECT i FROM Itemvenda i")
    , @NamedQuery(name = "Itemvenda.findByIdItemVenda", query = "SELECT i FROM Itemvenda i WHERE i.idItemVenda = :idItemVenda")
    , @NamedQuery(name = "Itemvenda.findByIdProduto", query = "SELECT i FROM Itemvenda i WHERE i.idProduto = :idProduto")
    , @NamedQuery(name = "Itemvenda.findByIdVenda", query = "SELECT i FROM Itemvenda i WHERE i.idVenda = :idVenda")
    , @NamedQuery(name = "Itemvenda.findByQuantidadeProduto", query = "SELECT i FROM Itemvenda i WHERE i.quantidadeProduto = :quantidadeProduto")
    , @NamedQuery(name = "Itemvenda.findByValorTotal", query = "SELECT i FROM Itemvenda i WHERE i.valorTotal = :valorTotal")
    , @NamedQuery(name = "Itemvenda.findByNomeProduto", query = "SELECT i FROM Itemvenda i WHERE i.nomeProduto = :nomeProduto")
    , @NamedQuery(name = "Itemvenda.findByValorUnitario", query = "SELECT i FROM Itemvenda i WHERE i.valorUnitario = :valorUnitario")
    , @NamedQuery(name = "Itemvenda.findByUnidadeProduto", query = "SELECT i FROM Itemvenda i WHERE i.unidadeProduto = :unidadeProduto")})
public class Itemvenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idItemVenda")
    private Integer idItemVenda;
    @Basic(optional = false)
    @Column(name = "idProduto")
    private int idProduto;
    @Basic(optional = false)
    @Column(name = "idVenda")
    private int idVenda;
    @Column(name = "quantidadeProduto")
    private Double quantidadeProduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorTotal")
    private Double valorTotal;
    @Column(name = "nomeProduto")
    private String nomeProduto;
    @Basic(optional = false)
    @Column(name = "valorUnitario")
    private double valorUnitario;
    @Column(name = "unidadeProduto")
    private String unidadeProduto;

    public Itemvenda() {
    }

    public Itemvenda(Integer idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public Itemvenda(int idProduto, int idVenda, Double quantidadeProduto, Double valorTotal, String nomeProduto, double valorUnitario, String unidadeProduto) {
        this.idProduto = idProduto;
        this.idVenda = idVenda;
        this.quantidadeProduto = quantidadeProduto;
        this.valorTotal = valorTotal;
        this.nomeProduto = nomeProduto;
        this.valorUnitario = valorUnitario;
        this.unidadeProduto = unidadeProduto;
    }
   
    public Integer getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(Integer idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Double getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Double quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getUnidadeProduto() {
        return unidadeProduto;
    }

    public void setUnidadeProduto(String unidadeProduto) {
        this.unidadeProduto = unidadeProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemVenda != null ? idItemVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itemvenda)) {
            return false;
        }
        Itemvenda other = (Itemvenda) object;
        if ((this.idItemVenda == null && other.idItemVenda != null) || (this.idItemVenda != null && !this.idItemVenda.equals(other.idItemVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.domain.Itemvenda[ idItemVenda=" + idItemVenda + " ]";
    }
    
}
