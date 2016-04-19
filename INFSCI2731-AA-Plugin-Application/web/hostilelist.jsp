<%-- 
    Document   : hostilelist
    Created on : Apr 10, 2016, 3:21:53 AM
    Author     : Zhirun Tian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hostile actions</title>
    </head>
    <body>
        <a href="Hostile?action=getHostile">Get Hostile entry</a>
        <h1>Get Hostile entry</h1>
    </body>
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
    
    
</html>
