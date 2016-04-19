<%-- 
    Document   : index
    Created on : Mar 30, 2016, 7:38:49 PM
    Author     : Siwei Jiao
    Add more content: Hanwei Cheng
--%>

<%
    if(session.getAttribute("account_id") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
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
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Page 1</a></li>
            <li><a href="#">Page 2</a></li>
            <li><a href="#">Page 3</a></li>
            <li><a href="#">Page 4</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
                <li id="user"><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
            </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
                    <%--if (user == null) {
        //if user tries to bypass authorization check, it will redirect to the login page
        //response.sendRedirect(request.getContextPath() + "/login.jsp"); 
            --%>
  <!--<button class="button" type="submit">Sign in</button>-->
    <%--
    } else {
        out.print("<a>" + user.getFirstName() + user.getLastName()"</a>");
    --%>
                    
                    
                    



    
    <!-- Begin page content -->
    <div class="container" style="margin-top:100px">
      <div class="page-header">
        <h1>Authentification Project</h1>
      </div>
        <a href="Test?action=getNonce">Get Nonce</a>
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
