/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.domain.Itemvenda;
import model.domain.Produto;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Danie
 */
public class ItemvendaJpaController implements Serializable {
    
    private List<Itemvenda> itemVendas;
    private VendasJpaController vendasJpaController;
    private ProdutoJpaController produtoJpaController;
    
    public ItemvendaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProjetoFinalPU");
        this.itemVendas = ObservableCollections.observableList(new ArrayList<Itemvenda>());
        this.vendasJpaController = new VendasJpaController();
        produtoJpaController =  new ProdutoJpaController();
    }

    public ItemvendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Itemvenda itemvenda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(itemvenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Itemvenda itemvenda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            itemvenda = em.merge(itemvenda);
           Produto prod = this.produtoJpaController.findProduto(itemvenda.getIdProduto());
           prod.setQtdEstoque(prod.getQtdEstoque() - itemvenda.getQuantidadeProduto());
           this.produtoJpaController.edit(prod);
           
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itemvenda.getIdItemVenda();
                if (findItemvenda(id) == null) {
                    throw new NonexistentEntityException("The itemvenda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Itemvenda itemvenda;
            try {
                itemvenda = em.getReference(Itemvenda.class, id);
                itemvenda.getIdItemVenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itemvenda with id " + id + " no longer exists.", enfe);
            }
            em.remove(itemvenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Itemvenda> findItemvendaEntities() {
        return findItemvendaEntities(true, -1, -1);
    }

    public List<Itemvenda> findItemvendaEntities(int maxResults, int firstResult) {
        return findItemvendaEntities(false, maxResults, firstResult);
    }

    private List<Itemvenda> findItemvendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Itemvenda.class));
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

    public List<Itemvenda> findItemvenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Itemvenda p WHERE p.idVenda = "+id).getResultList();
        } finally {
            em.close();
        }
    }

    public int getItemvendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Itemvenda> rt = cq.from(Itemvenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Itemvenda> getItemVendas() {
        return itemVendas;
    }
    
    public void limparLista() {
        this.itemVendas.clear();
        this.itemVendas.addAll(this.findItemvenda(this.vendasJpaController.getVendasLastId()));
    }
    
    public double getValorItems(){
        double valorTotal = 0;
        for (Iterator<Itemvenda> iterator = itemVendas.iterator(); iterator.hasNext();) {
            Itemvenda next = iterator.next();
            valorTotal += next.getValorTotal();
        }
        return valorTotal;
    }
    
}
