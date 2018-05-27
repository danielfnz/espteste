/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.domain.Caixapessoa;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Danie
 */
public class CaixapessoaJpaController implements Serializable {

    private List<Caixapessoa> caixaLista;

    public List<Caixapessoa> getCaixaLista() {
        return caixaLista;
    }

    public void setCaixaLista(List<Caixapessoa> caixaLista) {
        this.caixaLista = caixaLista;
    }

    public CaixapessoaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProjetoFinalPU");
        this.caixaLista = ObservableCollections.observableList(new ArrayList<Caixapessoa>());
        this.limparLista();
    }

    public void limparLista() {
        this.caixaLista.clear();
        this.caixaLista.addAll(this.findCaixapessoaEntities());
    }

    public CaixapessoaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Caixapessoa caixapessoa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(caixapessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        this.limparLista();
    }

    public void edit(Caixapessoa caixapessoa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            caixapessoa = em.merge(caixapessoa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = caixapessoa.getIdCaixaPessoa();
                if (findCaixapessoa(id) == null) {
                    throw new NonexistentEntityException("The caixapessoa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        this.limparLista();
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caixapessoa caixapessoa;
            try {
                caixapessoa = em.getReference(Caixapessoa.class, id);
                caixapessoa.getIdCaixaPessoa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caixapessoa with id " + id + " no longer exists.", enfe);
            }
            em.remove(caixapessoa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        this.limparLista();
    }

    public List<Caixapessoa> findCaixapessoaEntities() {
        return findCaixapessoaEntities(true, -1, -1);
    }

    public List<Caixapessoa> findCaixapessoaEntities(int maxResults, int firstResult) {
        return findCaixapessoaEntities(false, maxResults, firstResult);
    }

    private List<Caixapessoa> findCaixapessoaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caixapessoa.class));
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

    public Caixapessoa findCaixapessoa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caixapessoa.class, id);
        } finally {
            em.close();
        }
    }

    public int getCaixapessoaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caixapessoa> rt = cq.from(Caixapessoa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public int getLastId(){
             EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();

            Query q = em.createNativeQuery("SELECT LAST_INSERT_ID FROM caixapessoa");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
