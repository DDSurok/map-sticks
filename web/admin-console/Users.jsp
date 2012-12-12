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
        <link rel="stylesheet" type="text/css" href="Admin.css">
        <script src="../common/common.js" type="text/javascript"></script>
        <title>Users</title>
        <script type="text/javascript">
            var ballooneFrameView = function() {
                window.document.getElementById("frameDiv").removeAttribute("style");
            }
            var createDivAdd = function() {
                highlighterElementById("headerDiv");
                highlighterElementById("mainDiv");
                var dds = new DDSurok;
                dds.init();
                document.getElementById("mainDiv").disabledElement();
                document.getElementById("controlDiv").disabledElement();
                window.parent.ballooneFrame.location.href = "UserModifity/?mode=add";
                //window.parent.ballooneFrame.location.reload(true);
                //ballooneFrame.contectWindow.onload = ballooneFrameView;
                ballooneFrameView();
            };
            var highlighterElementById = function(elemId) {
                var elem = document.getElementById(elemId);
                elem.className = elem.className + " highlighter";
            };
        </script>
    </head>
    <body class="width950">
        <div id="headerDiv" class="pageItem header zindex10">Пользователи</div>
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
                    session.setAttribute("utils", userUtil);
                }
            }
        %>
        <div>
            <table class="noBorder noMarginNoPadding maxWidth">
                <colgroup>
                    <col width="100%" />
                </colgroup>
                <tbody>
                    <tr>
                        <td>
                            <div id="mainDiv" class="maxWidth noMarginNoPadding zindex10">
                                <iframe id="mainFrame" src="UserList.jsp" class="noBorder noMarginNoPadding maxWidth"></iframe>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div id="controlDiv" class="pageItem maxWidth zindex10">
                                <button onclick='window.getElementById("MainFrame").location.reload()'>Обновить</button>
                                &Tab;
                                <button onclick="createDivAdd()">Добавить</button>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        <div id="frameDiv" class="balloon" style="display:none;">
            <iframe id="ballooneFrame" class="balloon" name="ballooneFrame" src="UserModifity/?mode=null"></iframe>
        </div>
    </body>
</html>
