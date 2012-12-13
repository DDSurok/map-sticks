<%--
    Document   : formAddUser
    Created on : 07.12.2012, 12:33:47
    Author     : d.duritskij
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="Admin.css" rel="stylesheet">
        <script src="../common/jsSHA-256.js"></script>
        <script src="../common/common.js"></script>
        <script type="text/javascript">
            var backupStyle = function(elem) {
                elem.removeClass("required");
            }
            var sendUserInfo = function () {
                var dds = new DDSurok;
                dds.init();
                var password = document.getElementById("password");
                if (password.value.length < 6) {
                    var sha256 = new Sha256();
                    if (password.value != "&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;")
                        document.getElementById("hashpswd").value = sha256.hash(password.value, true);
                    document.getElementById("adduserForm").submit();
                } else {
                    password.addClass("required");
                    password.onchange = backupStyle(password);
                }
            }
        </script>
    </head>
    <body>
        <div class="pageItem header" style="font-size:large;">Новый пользователь</div>
        <div class="pageItem">
            <form id="addUserForm" method="POST" action="UserModifity/">
                <%! void parameter2Attribute(HttpServletRequest request, String name) {
                        if (request.getParameter(name) == null)
                        request.setAttribute(name, "");
                    else
                        request.setAttribute(name, request.getParameter(name));
                    }
                %>
                <%
                    parameter2Attribute(request, "nick");
                    parameter2Attribute(request, "hashpswd");
                    parameter2Attribute(request, "family");
                    parameter2Attribute(request, "name");
                    parameter2Attribute(request, "email");
                    parameter2Attribute(request, "isLocked");
                    parameter2Attribute(request, "idBaned");
                    if (request.getAttribute("hashpswd").equals(""))
                        request.setAttribute("password", "");
                    else
                        request.setAttribute("password", "&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;");
                %>
                <input type="hidden" name="mode" value="add_" />
                <table class="noBorderMargin5 maxWidth">
                    <colgroup>
                        <col class="width5percent" />
                        <col class="width10percent" />
                        <col class="width10percent" />
                        <col class="width10percent" />
                        <col class="width10percent" />
                        <col class="width10percent" />
                        <col class="width10percent" />
                        <col class="width10percent" />
                        <col class="width10percent" />
                        <col class="width10percent" />
                        <col class="width5percent" />
                    </colgroup>
                    <tbody>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>Ник:&nbsp;</span><span class="required">*</span>
                            </td>
                            <td colspan="6">
                                <input type="text" name="nick" value='<%= request.getAttribute("nick") %>' maxlength="50" class="maxWidth" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>Пароль:&nbsp;</span><span class="required">*</span>
                            </td>
                            <td colspan="6">
                                <input type="text" id="password" value='<%= request.getAttribute("password") %>' maxlength="100" class="maxWidth" />
                                <input type="hidden" id="hashpswd" name="hashpswd" value='<%= request.getAttribute("hashpswd") %>' />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>Фамилия:&nbsp;</span>
                            </td>
                            <td colspan="6">
                                <input type="text" name="family" value='<%= request.getAttribute("family") %>' maxlength="100" class="maxWidth" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>Имя:&nbsp;</span>
                            </td>
                            <td colspan="6">
                                <input type="text" name="name" value='<%= request.getAttribute("name") %>' maxlength="100" class="maxWidth" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>E-mail:&nbsp;</span>
                            </td>
                            <td colspan="6">
                                <input type="text" name="email" value='<%= request.getAttribute("email") %>' maxlength="100" class="maxWidth" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>Блокировка:&nbsp;</span>
                            </td>
                            <td class="alignCenter">
                                <input type="checkbox" name="isLocked" value='<%= request.getAttribute("isLocked") %>' />
                            </td>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>В бан-листе:&nbsp;</span>
                            </td>
                            <td class="alignCenter">
                                <input type="checkbox" name="isBaned" value='<%= request.getAttribute("isBaned") %>' />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="11">
                                <div style="text-align: center;">
                                    <input type="button" value="Добавить" onClick="sendUserInfo()" />
                                    <span>&Tab;</span>
                                    <input type="button" value="Закрыть" onClick="window.top.closeBallonneFrame(window.top.document);">
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
