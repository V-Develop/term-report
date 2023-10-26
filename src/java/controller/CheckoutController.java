/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Products;
import model.Shoppingcart;
import model.ShoppingcartPK;
import model.ShoppingcartTable;
import static model.ShoppingcartTable.insertShoppingCart;
import utilities.ShoppingcartManager;

/**
 *
 * @author alongkornvanzoh
 */
public class CheckoutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int allPrice = (Integer) session.getAttribute("allPrice");
        List<Products> products = (List<Products>) session.getAttribute("products");
        List<Integer> quantities = (List<Integer>) session.getAttribute("quantities");

        List<Shoppingcart> shoppingcarts = ShoppingcartTable.findAllShoppingCart();
        int newCartId = 0;
        if (shoppingcarts.isEmpty()) {
            newCartId = 1;
        } else {
            newCartId = shoppingcarts.get(shoppingcarts.size() - 1).getShoppingcartPK().getCartId() + 1;
        }

        for (int i = 0; i < products.size(); i++) {
            ShoppingcartPK shoppingcartPK = new ShoppingcartPK();
            shoppingcartPK.setCartId(newCartId);
            shoppingcartPK.setMovieId(products.get(i).getId());

            Shoppingcart cart = new Shoppingcart();
            cart.setShoppingcartPK(shoppingcartPK);
            cart.setQuantity(quantities.get(i));
            insertShoppingCart(cart);
        }

        int cartId = (int) session.getAttribute("cartId");
        synchronized (this.getServletContext()) {
            ShoppingcartManager.finishShopping(this.getServletContext(), cartId);
        }
        request.setAttribute("allPrice", allPrice);
        request.getRequestDispatcher("ShowConfirm.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
