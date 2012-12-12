<%-- 
    Document   : UserList
    Created on : 12.12.2012, 9:13:50
    Author     : d.duritskij
--%>
<%@page import="java.util.Collection"%>
<%@page import="ru.ddsurok.utils.UserUtil"%>
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
    <body class="zindex10">
        <%
            Object temp = session.getAttribute("utils");
            UserUtil userUtil = null;
            try {
                if (temp != null) {
                    userUtil = (UserUtil) temp;
                }
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
        <c:if test='${userCount=="0"}'><span>Список пользователей пуст!</span></c:if>
    </body>
</html>
