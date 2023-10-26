/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Products;
import model.ProductsTable;
import utilities.ShoppingcartManager;

/**
 *
 * @author alongkornvanzoh
 */
public class AddToShoppingCartController extends HttpServlet {

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
        int cartId = (int) session.getAttribute("cartId");
        synchronized (this.getServletContext()) {
            if (ShoppingcartManager.isShopping(this.getServletContext(), cartId)) {
                String errMsg = "Sorry this record is updating by another user. Try again later";
                request.setAttribute("errMsg", errMsg);
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }

        List<Products> productList = ProductsTable.findAllProduct();
        List<Integer> quantities = new ArrayList<>();
        int allPrice = 0;
        for (int i = 0; i < productList.size(); i++) {
            String parameter = String.valueOf(i + 1);
            String select = request.getParameter(parameter);
            String[] quantity = request.getParameterValues("quantity");
            if (select != null) {
                quantities.add(Integer.parseInt(quantity[i]));
                allPrice += productList.get(i).getPrice() * Integer.parseInt(quantity[i]);
            } else {
                quantities.add(0);
            }
        }
        request.setAttribute("allPrice", allPrice);
        request.setAttribute("quantities", quantities);
        request.getRequestDispatcher("AddToShoppingCart.jsp").forward(request, response);
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
