<%-- 
    Document   : activitylog
    Created on : Apr 20, 2016, 6:05:11 PM
    Author     : shaoNPC
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="DbConnect.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            table { border-collapse:collapse; }
            tr.columnname { border-bottom:1px #000 solid; }
            tr.columnname td { text-align:center; }
            td { border: 1px #000 solid; }
            td.center { text-align:center; }
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Activity log</h1>
        <table style="border-collapse:collapse;">
            <tr class="columnname">
                <td>ID</td>
                <td>IP Address</td>
                <td>System Source</td>
                <td>Activity Count</td>
                <td>Description</td>
                <td>Account ID</td>
                <td>Created</td>
                <td>Updated</td>
            </tr>
        <%
            try {
                Connection connection = DbConnection.getConnection();
                String selectSQL = "SELECT * FROM INFSCI2731.activity_log, INFSCI2731.timestamps WHERE INFSCI2731.activity_log.timestamps_id = INFSCI2731.timestamps.id ORDER BY INFSCI2731.activity_log.id DESC";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()) {

        %>
                    <tr>
                        <td class="center"><%=rs.getInt("id")%></td>
                        <td><%=rs.getString("ip_addr")%></td>
                        <td><%=rs.getString("system_source")%></td>
                        <td class="center"><%=rs.getInt("activity_count")%></td>
                        <td><%=rs.getString("description")%></td>
                        <td class="center"><%=rs.getInt("account_info_id")%></td>
                        <td><%=rs.getTimestamp("create_time")%></td>
                        <td><%=rs.getTimestamp("update_time")%></td>
                    </tr>
        <%

            }
        %>
        </table>
        <%
                //rs.close();
                //preparedStatement.close();
                //connection.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        %>
    </body>
</html>
