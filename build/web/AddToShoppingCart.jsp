<%-- 
    Document   : AddToShoppingCart
    Created on : Oct 24, 2023, 8:00:27 PM
    Author     : alongkornvanzoh
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.ProductsTable"%>
<%@page import="java.util.List"%>
<%@page import="model.Products"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add To Shopping Cart</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <jsp:useBean id="products" class="model.Products" scope="request"/>
    <%
        List<Products> productsList = ProductsTable.findAllProduct();
        int allPrice = (Integer) request.getAttribute("allPrice");
        List<Integer> quantities = (List<Integer>) request.getAttribute("quantities");
        for (int i = 0; i < quantities.size(); i++) {
            if (quantities.get(i) == 0) {
                productsList.remove(i);
                quantities.remove(i);
            }
        }
        Iterator<Products> itr = productsList.iterator();
        session.setAttribute("allPrice", allPrice);
        session.setAttribute("products", productsList);
        session.setAttribute("quantities", quantities);
    %>
    <body>
        <h1 class="text-center mt-3">Shopping Cart</h1>
        <form name="checkout" action="CheckoutController" method="POST">
            <div class="containner-fluid">
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6 mt-3">
                        <table class="table">
                            <thead>
                                <tr class="text-center">
                                    <th scope="col">DVD Names</th>
                                    <th scope="col">Rate</th>
                                    <th scope="col">Year</th>
                                    <th scope="col">Price/Unit</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int index = 0;
                                    while (itr.hasNext()) {
                                        products = itr.next();
                                        out.println("<tr class=\"text-center\">");
                                        out.println("<td> " + products.getMovie() + "</td>");
                                        out.println("<td> " + products.getRating() + "</td>");
                                        out.println("<td> " + products.getYearcreate() + "</td>");
                                        out.println("<td> " + products.getPrice() + "</td>");
                                        out.println("<td>" + quantities.get(index) + "</td>");
                                        out.println("<td>" + products.getPrice() * quantities.get(index) + "</td>");
                                        out.println("</tr>");
                                        index += 1;
                                    }
                                    out.println("<tr class=\"text-center\">" + "<td colspan=\"5\"><b>Total</b></td>");
                                    out.println("<td>" + allPrice + "</td>" + "</tr>");
                                %>    
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-3"></div>
                </div>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Check out</button>
            </div>
        </form>
    </body>
</html>
