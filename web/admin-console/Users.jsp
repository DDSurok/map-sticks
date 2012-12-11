<%--
    Document   : Users
    Created on : 07.12.2012, 12:33:47
    Author     : d.duritskij
--%>

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
        <!--script src="/common/common.js" lang="text/javascript"></script-->
        <title>Users</title>
    </head>
    <body class="adminPage" style="min-width:950px;max-width:950px;">
        <div id="headerDiv" class="adminPageItem adminHeaderDiv">Пользователи</div>
        <%
            Object temp = session.getAttribute("utils");
            UserUtil userUtil = null;
            try {
                if (temp != null) {
                    userUtil = (UserUtil) temp;
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
        <div id="mainDiv" class="adminPageItem">
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
            <button onclick="window.location.reload()">Обновить список</button><span>&Tab;</span>
            <button onclick="createDivAdd()">Добавить</button>
        </div>
        <div id="frameDiv" style="display: none;" class="adminPageFrame"></div>
    </body>
    <script lang="text/javascript">
        Object.prototype.disabledElement = function() {
            var all = this.childNodes;
            var inp, i=0;
            while(inp=all[i++]) {
                if ((inp != null)&&(inp.textContent.trim() != "")) {
                    if ((inp.tagName == "INPUT")
                        ||(inp.tagName == "BUTTON")
                        ||(inp.tagName == "SELECT")
                        ||(inp.tagName == "RADIO")) {
                        inp.disabled=true;
                    } else {
                        inp.disabledElement();
                    }
                }
            }
        };
        String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ''); };
        Document.prototype.childFrameClose = function(isSuccess) {
            if (isSuccess) {
                window.location.reload()
            } else {
                var elem = this.getElementById("frameDiv");
                elem.innerHTML = "";
                elem.setAttribute("style", "display: none;");
            }
        };
        var createDivAdd = function() {
            highlighterElementById("headerDiv");
            highlighterElementById("mainDiv");
            var elem = document.getElementById("mainDiv");
            elem.disabledElement();
            var frameElem = document.getElementById("frameDiv");
            frameElem.innerHTML = '<iframe src="UserEdit.jsp?mode=add" class="adminPageFrame" />';
            frameElem.removeAttribute("style");
        };
        var highlighterElementById = function(elemId) {
            var elem = document.getElementById(elemId);
            elem.className = elem.className + " highlighter";
        };
    </script>
</html>
