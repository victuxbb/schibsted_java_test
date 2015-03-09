<%-- 
    Document   : pag_3
    Created on : 07-mar-2015, 19:24:46
    Author     : victux
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PAG_3</title>
    </head>
    <body>
         <h1>PAG_3</h1>
         <h1>Hello ${username}</h1>
         <form method="GET" action="${pageContext.request.contextPath}/logout">
            <input type="submit" value="logout">
        </form>
    </body>
</html>
