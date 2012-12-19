<%-- 
    Document   : UserList
    Created on : 12.12.2012, 9:13:50
    Author     : d.duritskij
--%>
<%@page import="java.util.Collection"%>
<%@page import="ru.ddsurok.utils.user.UserUtil"%>
<%@page import="ru.ddsurok.datamodel.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Admin.css">
        <title>Список пользователей</title>
    </head>
    <body>
        <%
            UserUtil util = UserUtil.getUserUtil(session);
            Collection list = util.getAllUsers();
            request.setAttribute("userList", list);
            request.setAttribute("userCount", String.valueOf(list.size()));
        %>
        <c:if test='${userCount>0}'>
        <table class="noBorderMargin5">
            <c:forEach items="${userList}" var="row">
            <tr>
                <td><div class="">${row.getId()}</div></td>
                <td>${row.getNickName()}</td>
                <td>${row.getEmail()}</td>
            </tr>
            </c:forEach>
        </table>
        </c:if>
        <c:if test='${userCount==0}'><span>Список пользователей пуст!</span></c:if>
    </body>
</html>
