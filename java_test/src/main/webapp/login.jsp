<%-- 
    Document   : login
    Created on : 07-mar-2015, 19:14:39
    Author     : victux
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>Login</title>
    </head>
    <body>
       <% if(request.getParameter("error")!=null){ %>
            <p><%=request.getParameter("error")%></p>               
       <% } %>
        <h1>Login form</h1>
        <form action="${pageContext.request.contextPath}/login" method="POST">
            <label>username:</label><input type="text" name="username" value=""/><br/>
            <label>password:</label><input type="password" name="password" value=""/><br/>
            <input type="submit" value="enviar">
        </form>      
        
    </body>
</html>
