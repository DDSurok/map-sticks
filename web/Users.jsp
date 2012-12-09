<%--
    Document   : Users
    Created on : 07.12.2012, 12:33:47
    Author     : d.duritskij
--%>

<%@page import="org.hibernate.SessionFactory"%>
<%@page import="java.util.Collection"%>
<%@page import="ru.ddsurok.utils.UserUtil"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        <h1>Users</h1>
        <%
            UserUtil userUtil = new UserUtil();
            Collection list = userUtil.getAllUsers();
            if (list != null) {
                request.setAttribute("userList", list);
                request.setAttribute("userCount", String.valueOf(list.size()));
            } else {
                request.setAttribute("userCount", "0");
            }
        %>
        <c:if test='${userCount!="0"}'>
        <table border=1>
            <c:forEach items="${userList}" var="row">
                <tr>
                    <td>${row.Id}</td>
                    <td>${row.NickName}</td>
                    <td>${row.Email}</td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
        <c:if test='${userCount=="0"}'><span>Список пользователей пуст!</span></c:if>
    </body>
</html>
