/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.domain.Pessoa;
import model.domain.Vendas;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Danie
 */
public class VendasJpaController implements Serializable {
    
    public List listVendas;
    
    private EntityManagerFactory emf = null;
       
    public VendasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProjetoFinalPU");
         this.listVendas = ObservableCollections.observableList(new ArrayList<>());
        this.findVendasJoin();
    }  
     
    public VendasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vendas vendas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(vendas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVendas(vendas.getIdVenda()) != null) {
                throw new PreexistingEntityException("Vendas " + vendas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vendas vendas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            vendas = em.merge(vendas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = vendas.getIdVenda();
                if (findVendas(id) == null) {
                    throw new NonexistentEntityException("The vendas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendas vendas;
            try {
                vendas = em.getReference(Vendas.class, id);
                vendas.getIdVenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendas with id " + id + " no longer exists.", enfe);
            }
            em.remove(vendas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vendas> findVendasEntities() {
        return findVendasEntities(true, -1, -1);
    }

    public List<Vendas> findVendasEntities(int maxResults, int firstResult) {
        return findVendasEntities(false, maxResults, firstResult);
    }

    private List<Vendas> findVendasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vendas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vendas findVendas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vendas.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vendas> rt = cq.from(Vendas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public int getVendasLastId() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
           return (int) em.createNativeQuery("SELECT idVenda FROM `vendas` ORDER BY idVenda DESC LIMIT 1").getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public void findVendasJoin() {
         EntityManager em = getEntityManager();
        try {
            this.listVendas.clear();
               this.listVendas.addAll(em.createNativeQuery("SELECT\n" +
"vendas.dataVenda,\n" +
"itemvenda.nomeProduto ,\n" +
"itemvenda.valorUnitario,\n" +
"itemvenda.quantidadeProduto,\n" +
"itemvenda.unidadeProduto,\n" +
"vendas.valorTotal,\n" +
"vendas.valorPago,\n" +
"vendas.valorTroco,\n" +
"vendas.formaPgto,\n" +
"produto.qtdEstoque\n" +
"FROM vendas\n" +
"INNER JOIN itemvenda ON (itemvenda.idVenda = vendas.idVenda)\n" +
"INNER JOIN produto ON (itemvenda.idProduto = produto.idProduto)\n" +
"\n").getResultList());
               System.out.println(this.listVendas);
        } finally {
            em.close();
        }
    }
    
    
    public List<Vendas> getListVendas() {
        return listVendas;
    }

    public void setListVendas(List<Vendas> listVendas) {
        this.listVendas = listVendas;
    }

     public void limparLista() {
        this.listVendas.clear();
        this.listVendas.addAll(this.findVendasEntities());
    }
    
}
