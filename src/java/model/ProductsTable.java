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
public class ProductsTable {

    public static List<Products> findAllProduct() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingPU");
        EntityManager em = emf.createEntityManager();
        List<Products> productsList = null;
        try {
            productsList = (List<Products>) em.createNamedQuery("Products.findAll").getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            em.close();
            emf.close();
        }
        return productsList;
    }
}
