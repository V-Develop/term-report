<%-- 
    Document   : index
    Created on : Oct 23, 2023, 8:34:32 PM
    Author     : alongkornvanzoh
--%>
<%@page import="model.ShoppingcartTable"%>
<%@page import="model.Shoppingcart"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.ProductsTable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Products"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DVD Catalog</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <jsp:useBean id="products" class="model.Products" scope="request"/>
    <%

        List<Products> productsList = ProductsTable.findAllProduct();
        Iterator<Products> itr = productsList.iterator();

        List<Shoppingcart> shoppingcarts = ShoppingcartTable.findAllShoppingCart();
        int cartId = 0;
        if (shoppingcarts.size() == 0) {
            cartId = 1;
        } else {
            cartId = shoppingcarts.get(shoppingcarts.size() - 1).getShoppingcartPK().getCartId() + 1;
        }
        session.setAttribute("cartId", cartId);
    %>
    <body>
        <h1 class="text-center mt-3">DVD Catalog</h1>
        <form name="addToShoppingCart" action="AddToShoppingCartController" method="POST">
            <center>
                <div class="containner-fluid">
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-6 mt-3">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">DVD Names</th>
                                        <th scope="col">Rate</th>
                                        <th scope="col">Year</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        while (itr.hasNext()) {
                                            products = itr.next();
                                            out.println("<tr>");
                                            out.println("<td> " + "<input type=\"checkbox\" name=" + products.getId() + "> " + products.getMovie() + "</td>");
                                            out.println("<td> " + products.getRating() + "</td>");
                                            out.println("<td> " + products.getYearcreate() + "</td>");
                                            out.println("<td> " + products.getPrice() + "</td>");
                                            out.println("<td>" + "<input type=\"number\" name=\"quantity\" class=\"form-control\">");
                                            out.println("</tr>");
                                        }
                                    %>    
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Add To Cart</button>
            </center>
        </form>
    </body>
</html>
