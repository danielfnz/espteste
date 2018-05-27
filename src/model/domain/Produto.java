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
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
    , @NamedQuery(name = "Produto.findByIdProduto", query = "SELECT p FROM Produto p WHERE p.idProduto = :idProduto")
    , @NamedQuery(name = "Produto.findByNomeProduto", query = "SELECT p FROM Produto p WHERE p.nomeProduto = :nomeProduto")
    , @NamedQuery(name = "Produto.findByValorPoduto", query = "SELECT p FROM Produto p WHERE p.valorPoduto = :valorPoduto")
    , @NamedQuery(name = "Produto.findByUnidadeProduto", query = "SELECT p FROM Produto p WHERE p.unidadeProduto = :unidadeProduto")
    , @NamedQuery(name = "Produto.findByQtdEstoque", query = "SELECT p FROM Produto p WHERE p.qtdEstoque = :qtdEstoque")
    , @NamedQuery(name = "Produto.findByCodigoBarra", query = "SELECT p FROM Produto p WHERE p.codigoBarra = :codigoBarra")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProduto")
    private Integer idProduto;
    @Column(name = "nomeProduto")
    private String nomeProduto;
    @Basic(optional = false)
    @Column(name = "valorPoduto")
    private double valorPoduto;
    @Column(name = "unidadeProduto")
    private String unidadeProduto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qtdEstoque")
    private Double qtdEstoque;
    @Column(name = "codigoBarra")
    private String codigoBarra;

    public Produto() {
    }

    public Produto(String nomeProduto, double valorPoduto, String unidadeProduto, Double qtdEstoque, String codigoBarra) {
        this.nomeProduto = nomeProduto;
        this.valorPoduto = valorPoduto;
        this.unidadeProduto = unidadeProduto;
        this.qtdEstoque = qtdEstoque;
        this.codigoBarra = codigoBarra;
    }



    public Produto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Produto(Integer idProduto, double valorPoduto) {
        this.idProduto = idProduto;
        this.valorPoduto = valorPoduto;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValorPoduto() {
        return valorPoduto;
    }

    public void setValorPoduto(double valorPoduto) {
        this.valorPoduto = valorPoduto;
    }

    public String getUnidadeProduto() {
        return unidadeProduto;
    }

    public void setUnidadeProduto(String unidadeProduto) {
        this.unidadeProduto = unidadeProduto;
    }

    public Double getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Double qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduto != null ? idProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProduto == null && other.idProduto != null) || (this.idProduto != null && !this.idProduto.equals(other.idProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.domain.Produto[ idProduto=" + idProduto + " ]";
    }

}
