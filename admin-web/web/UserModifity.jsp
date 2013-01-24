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
        <link type="text/css" href="common/Admin.css" rel="stylesheet">
        <script src="common/jsSHA-256.js"></script>
        <script src="common/common.js"></script>
        <script type="text/javascript">
            function backupStyle(elem) {
                elem.removeClass("required");
            }
            function validate() {
                return true;
            }
            function sendUserInfo() {
                if (validate()) {
                    var dds = new DDSurok;
                    dds.init();
                    var password = document.getElementById("password");
                    if (password.value != "&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;")
                        document.getElementById("hashpswd").value = Sha256.hash(password.value, true);
                    document.getElementById("addUserForm").submit();
                }
            }
        <c:if test='${errorCode==0}'>
            document.body.onload = function() {
                alert('Введенные данные не верны.\n\n<%= request.getAttribute("errorMessage") %>');
            }
        </c:if>
        </script>
    </head>
    <body>
        <div class="pageItem header" style="font-size:large;">Информация о пользователе</div>
        <div class="pageItem">
            <form id="addUserForm" method="POST" action="worker">
                <input type="hidden" name="mode" value='<%= request.getAttribute("mode") %>' />
                <c:if test='${mode=="edit_"}'>
                    <input type="hidden" name="id" value='<%= request.getAttribute("id") %>' />
                </c:if>
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
                                <input type="password" id="password" value='<%= request.getAttribute("password") %>' maxlength="100" class="maxWidth" />
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
                                <input type="checkbox" name="isLocked" value="true"<c:if test='${isLocked=="true"}'> checked</c:if> />
                            </td>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>В бан-листе:&nbsp;</span>
                            </td>
                            <td class="alignCenter">
                                <input type="checkbox" name="isBaned" value="true"<c:if test='${isBaned=="true"}'> checked</c:if> />
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
