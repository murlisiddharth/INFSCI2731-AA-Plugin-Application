<%-- 
    Document   : resetpassword
    Created on : Apr 7, 2016, 2:03:51 AM
    Author     : shaoNPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <form name="resetform-password" method="POST" action="ResetPasswordService">
            <input type="hidden" name="token" value="${param.token}">
            Enter your new password: <input name="password" type="password" />
            <br/><br/>
            Confirm your new password: <input name="confirm_password" type="password"/>
            <br/><br/>
            <input type="submit">
        </form>
    </body>
</html>
