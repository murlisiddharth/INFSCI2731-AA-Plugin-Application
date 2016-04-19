<%-- 
    Document   : RBACtest2
    Created on : April 19, 2016, 7:38:49 PM
    Author     : Hanwei Cheng
--%>


<%@page import="java.util.List"%>
<%@ page import="controller.RBAC" %> 
<%@ page import="dataAccessObject.RBACDao" %> 

<%--
    //only for test, I set an attribute UserID here
    session.setAttribute("RoleID", 3);//!!roleID
    
    if (session.getAttribute("RoleID") != null )  //roleID
    {
        RBACDao accessControl = new RBACDao();
        List<Integer> UserPool = accessControl.getRolebyPath("RBACtest.jsp"); //it should be roleID pool
        if(!UserPool.contains(session.getAttribute("RoleID")))
        {
            response.sendRedirect("index.jsp");
        }
    }
    else
    {
        response.sendRedirect("login.jsp");
    }
//    response.sendRedirect("RBAC?action=Auth");
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test4</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    </head>
    <body>
        
    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">IS2731</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="hostile.jsp">Hostile</a></li>
           <li><a href="RBACtest.jsp">RBACtest</a></li>
            <li class="active"><a href="RBACtest2.jsp">RBACtest2</a></li>
            <li><a href="RBACtest3.jsp">RBACtest3</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
                <li id="user"><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
            </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

                  

    
    <!-- Begin page content -->
    <div class="container" style="margin-top:100px; margin-bottom:250px; ">
      <div class="page-header">
        <h1>Authentication Project</h1>
      </div>
        <p> You have privilege to access the page!</p>
    </div>

    <footer class="footer">
      <div class="container">
        <p class="text-muted">&copy; 2016 E-Commerce Security &middot; <a href="#">Privacy</a>
                            &middot; <a href="#">Terms</a></p>
      </div>
    </footer>
    

     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous">
        </script>

    </body>
</html>