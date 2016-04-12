<%-- 
    Document   : index
    Created on : Mar 30, 2016, 7:38:49 PM
    Author     : Siwei Jiao
--%>
<%
    response.sendRedirect("login.jsp");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="Test?action=getNonce">Get Nonce</a>
    </body>
</html>
