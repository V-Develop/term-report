/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alongkornvanzoh
 */
public class ShoppingcartTable {

    public static void insertShoppingCart(Shoppingcart cart) {
        EntityManager em = Persistence.createEntityManagerFactory("OnlineShoppingPU").createEntityManager();
        em.getTransaction().begin();
        em.persist(cart);
        em.getTransaction().commit();
        em.close();
    }

    public static List<Shoppingcart> findAllShoppingCart() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingPU");
        EntityManager em = emf.createEntityManager();
        List<Shoppingcart> shoppingcartList = null;
        try {
            shoppingcartList = (List<Shoppingcart>) em.createNamedQuery("Shoppingcart.findAll").getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            em.close();
            emf.close();
        }
        return shoppingcartList;
    }
}
