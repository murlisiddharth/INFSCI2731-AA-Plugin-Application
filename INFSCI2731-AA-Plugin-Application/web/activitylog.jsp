<%-- 
    Document   : activitylog
    Created on : Apr 20, 2016, 6:05:11 PM
    Author     : shaoNPC
--%>
<%@page import="dataAccessObject.ActivityLogDao"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
        <style>
            .container { width:100%; }
            table { border-collapse:collapse; padding-bottom:1em; }
            tr.columnname { border-bottom:1px #000 solid; }
            tr.columnname td { text-align:center; }
            td { border: 1px #000 solid; padding:0 1em 0 1em; white-space:nowrap; }
            td.center { text-align:center; }
        </style>
        <title>Activity Log</title>
    </head>
    <body>
        <div class="container">
            <h1>Activity Log</h1>
            <%
                String queryterm = request.getParameter("term"); 
                String querycolumn = request.getParameter("column"); 
                String querysort = request.getParameter("sort"); 
                String querylimit = request.getParameter("limit"); 
                
                int limit = 20;
                String displaysearch = "";
                String method = "LIKE";
                
                ResultSet rs;
                ActivityLogDao ald = new ActivityLogDao();
                if (queryterm == null || queryterm.equals("") || querycolumn == null || querycolumn.equals("") || querysort == null || querysort.equals("") || querylimit == null || querylimit.equals("")) {
                    if(querylimit != null) {
                        try {
                            limit = Integer.parseInt(querylimit);
                        } catch (NumberFormatException e) {
                            
                        }
                    }
                    rs = ald.getActivityLog(limit);
                } else {
                    if (querycolumn.equals("id")) {
                        querycolumn = "activity_log.id";
                        method = "=";
                    }
                    if (querycolumn.equals("activity_count") || querycolumn.equals("account_info_id")) {
                        method = "=";
                    }
                    rs = ald.getActivityLog(queryterm, querycolumn, querysort, querylimit, method);
                    displaysearch = "Searched '" + queryterm + "' on '" + querycolumn + "', sort '" + querysort + "', limit '" + querylimit + "'";
                }

                if (rs != null) {

                    ResultSetMetaData rsmd = rs.getMetaData();
            %>         
            <h3><%=displaysearch%></h3>
            <div style="display:table; padding-bottom:1em;">
                <div style="display:table-cell;">Search Term: <input type="text" id="term"></div>
                <div style="display:table-cell; padding-left:1em;">Column: <select id="column">
            <%
                    for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                        %>
                        <option value="<%=rsmd.getColumnName(i)%>"><%=rsmd.getColumnName(i)%></option>
                        <%
                    }
            %>
                </select></div>
                <div style="display:table-cell; padding-left:1em;">Limit: 
                    <select id="limit">
                        <option value="20">20</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                        <option value="200">200</option>
                    </select>
                </div>
                <div style="display:table-cell; padding-left:1em;">Sort: 
                    <select id="sort">
                        <option value="DESC">Descending</option>
                        <option value="ASC">Ascending</option>

                    </select>
                </div>
                <div style="display:table-cell; padding-left:1em;">
                    <a type="button" href="#" id="btnSearchLog" class="btn btn-default" onclick="searchLog();">Search</a>
                </div>
                <div style="display:table-cell; padding-left:1em;">
                    <a type="button" href="#" id="btnSearchLog" class="btn btn-default" onclick="resetLog();">Reset</a>
                </div>
            </div>
            <table class="data-table">
                <tr class="columnname">
            <%
                    for(int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                        %>
                        <td><%=rsmd.getColumnName(i)%></td>
                        <%
                    }
            %>

                </tr>
            <%
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
            } else {
                %> 
                <h3><%=displaysearch%></h3>
                <p>There has been an error with data retrieval.</p> 
                <div style="display:table-cell; padding-left:1em;">
                    <a type="button" href="#" id="btnSearchLog" class="btn btn-default" onclick="resetLog();">Reset</a>
                </div>
                <%
            }
            %>
            </table>
            <!-- FOOTER -->
            <br/>
	<div class="container marketing">

            <footer>
                    <p>
                            &copy; 2016 E-Commerce Security &middot; <a href="#">Privacy</a>
                            &middot; <a href="#">Terms</a>
                    </p>
            </footer>

	</div>
	<!-- /.container -->
        </div>
            <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
            <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
            <script>
                function searchLog() {
                    var term = document.getElementById("term").value;
                    var column = document.getElementById("column").value;
                    var limit = document.getElementById("limit").value;
                    var sort = document.getElementById("sort").value;
                    
                    var url = "activitylog.jsp?term=" + term + "&column=" + column + "&limit=" + limit + "&sort=" + sort;
                    window.location = url;
                }
                function resetLog() {
                    window.location = "activitylog.jsp";
                }
            </script>
    </body>
</html>
