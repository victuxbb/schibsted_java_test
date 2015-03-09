<%-- 
    Document   : pag_1
    Created on : 07-mar-2015, 19:22:51
    Author     : victux
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PAG_1</title>
    </head>
    <body>
        <h1>PAG_1</h1>
        <h1>Hello ${username}</h1>
        <form method="GET" action="${pageContext.request.contextPath}/logout">
            <input type="submit" value="logout">
        </form>
    </body>
</html>
