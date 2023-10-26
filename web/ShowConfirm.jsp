<%-- 
    Document   : ShowConfirm
    Created on : Oct 25, 2023, 1:48:33 PM
    Author     : alongkornvanzoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <%
        int totalPrice = (Integer) request.getAttribute("allPrice");
    %>
    <body>
        <h1>Your Order is confirmed!</h1>
        <br>

        <h1>The total amount is $<%= totalPrice%></h1>
    </body>
</html>