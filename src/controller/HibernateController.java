package controller;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Danie
 */
public class HibernateController {
    
    private static EntityManagerFactory entityManagerFactory;
    
    private static HibernateController conexao;

    public HibernateController() {
     this.entityManagerFactory = Persistence.createEntityManagerFactory("ProjetoFinalPU");
    }
     
     public synchronized static EntityManager getConexao(){
         if(conexao == null){
             conexao = new HibernateController();
         }
         return entityManagerFactory.createEntityManager();
     }
    
}
