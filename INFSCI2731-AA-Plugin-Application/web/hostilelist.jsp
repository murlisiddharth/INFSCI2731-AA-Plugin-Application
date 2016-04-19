<%-- 
    Document   : hostilelist
    Created on : Apr 10, 2016, 3:21:53 AM
    Author     : Zhirun Tian
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
        <title>Hostile actions</title>
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
                   <li class="active"><a href="hostile.jsp">Hostile</a></li>
                   <li><a href="RBACtest.jsp">RBACtest</a></li>
                   <li><a href="RBACtest2.jsp">RBACtest2</a></li>
                   <li><a href="RBACtest3.jsp">RBACtest3</a></li>
                 </ul>
                 <ul class="nav navbar-nav navbar-right">
                       <li id="user"><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                   </ul>
               </div><!--/.nav-collapse -->
             </div>
           </nav>
        
        <!--content part--->
         <div class="container" style="margin-top:100px; margin-bottom:250px;">
             <div class="page-header">
                <h1 href="Hostile?action=getHostile">Get Hostile entry</h1>
            </div>

            <form name="resetform-password" method="POST" action="Hostile">
        <!--        <input type="hidden" name="token" value="${param.token}">-->
                Enter CountAttempts <input name="countAttempt" type="text" value="5" />
                <br/><br/>
                Enter IP address: <input name="IPAddress" type="text" value="192.168.32.11"/>
                <br/><br/>
                Enter SYSTEM_SOURCE: <input name="SYSTEM_SOURCE" type="text" value="from_security_question" />
                <br/><br/>

                <input type="submit">
            </form>
        </div><!--container-->
    
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous">
        </script>
     </body>
     
</html>
