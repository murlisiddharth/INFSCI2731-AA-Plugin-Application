<%-- 
    Document   : RBACtest
    Created on : Apr 17, 2016, 9:53:13 PM
    Author     : Zhirun Tian, Hanwei Cheng
--%>

<%@ page import="controller.RBAC" %> 


<%
    if (session.getAttribute("AccessControll") != null && (Integer)session.getAttribute("AccessControll") > 0)
    {
        
    }
//    response.sendRedirect("RBAC?action=Auth");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
