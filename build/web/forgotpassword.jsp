<%-- 
    Document   : resetpassword.jsp
    Created on : Mar 31, 2016, 10:32:00 PM
    Author     : shao dai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password page</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <% if (session.getAttribute("emailAttempts") != null && (Integer)session.getAttribute("emailAttempts") > 0) { %>
        <p>That username does not exist</p>
        <% } %>
        <form name="resetform-email" method="POST" action="GetQuestions">
            Enter your email: <input name="email" type="email" /> 
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
