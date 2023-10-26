/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author alongkornvanzoh
 */
public class ShoppingcartManager {
    public static boolean isShopping(ServletContext sc, int id) {
        ArrayList<Integer> idUsed = (ArrayList<Integer>) sc.getAttribute("cartId");
        if (idUsed == null) {
            idUsed = new ArrayList<>();
            idUsed.add(id);
            sc.setAttribute("cartId", idUsed);
        }
        else {
            if (idUsed.indexOf(id) == -1) {
                idUsed.add(id);
            }
            else {
                return true;
            }
        }
        return false;
    }
    
    public static void finishShopping(ServletContext sc, Integer id) {
         ArrayList<Integer> idUsed = (ArrayList<Integer>) sc.getAttribute("cartId");
         if (idUsed == null) return;
         idUsed.remove(id);
        
    }
}
