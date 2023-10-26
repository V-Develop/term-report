<%-- 
    Document   : error
    Created on : Oct 25, 2023, 3:30:23 PM
    Author     : alongkornvanzoh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Notification</title>
    </head>
    <body>
        <h1><%= request.getAttribute("errMsg")%></h1>
        <a href="index.jsp">Back to Menu</a>
    </body>
</html>
