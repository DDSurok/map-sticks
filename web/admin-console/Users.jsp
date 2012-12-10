<%--
    Document   : Users
    Created on : 07.12.2012, 12:33:47
    Author     : d.duritskij
--%>

<%@page import="org.hibernate.SessionFactory"%>
<%@page import="java.util.Collection"%>
<%@page import="ru.ddsurok.utils.UserUtil"%>
<%@page import="ru.ddsurok.datamodel.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Users.css">
        <title>Users</title>
    </head>
    <body class="adminPage">
        <div class="adminPageItem adminHeaderDiv">Пользователи</div>
        <%
            Object temp = session.getAttribute("utils");
            UserUtil userUtil = null;
            try {
                if (temp != null) {
                    userUtil = (UserUtil)temp;
                }
            } catch (Throwable th) {
            } finally {
                if (userUtil == null) {
                    userUtil = new UserUtil();
                }
                Collection list = userUtil.getAllUsers();
                if (list != null) {
                    request.setAttribute("userList", list);
                    request.setAttribute("userCount", String.valueOf(list.size()));
                    session.setAttribute("utils", userUtil);
                } else {
                    request.setAttribute("userCount", "0");
                    request.setAttribute("utils", userUtil);
                }
            }
        %>
        <c:if test='${userCount!="0"}'>
        <table border="0" class="usersView">
            <c:forEach items="${userList}" var="row">
            <tr>
                <td><div class="">${row.getId()}</div></td>
                <td>${row.getNickName()}</td>
                <td>${row.getEmail()}</td>
            </tr>
            </c:forEach>
        </table>
        </c:if>
        <c:if test='${userCount=="0"}'><span>Список пользователей пуст!</span></c:if>
        <button onclick="updateUsers()" value="Обновить список">
    </body>
    <script lang="text/javascript">
        updateUsers = function () {
            window.location.reload();
        }
    </script>
    
</html>
