<%-- 
    Document   : RBACtest
    Created on : Apr 17, 2016, 9:53:13 PM
    Author     : Zhirun Tian, Hanwei Cheng
--%>

<%@page import="java.util.List"%>
<%@ page import="controller.RBAC" %> 
<%@ page import="dataAccessObject.RBACDao" %> 


<%
    //only for test, I set an attribute UserID here
    session.setAttribute("UserID", 3);
    
    if (session.getAttribute("UserID") != null )
    {
        RBACDao accessControl = new RBACDao();
        List<Integer> UserPool = accessControl.getRolebyPath("RBACtest.jsp");
        if(!UserPool.contains(session.getAttribute("UserID")))
        {
            response.sendRedirect("login.jsp");
        }
    }
    else
    {
        response.sendRedirect("login.jsp");
    }
//    response.sendRedirect("RBAC?action=Auth");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RBAC test</title>
    </head>
    <body>
        <h1>You have privilege to access the page!</h1>
    </body>
</html>
