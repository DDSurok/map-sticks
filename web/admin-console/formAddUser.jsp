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
        <link type="text/css" href="../Admin.css" rel="stylesheet">
        <script src="../../common/jsSHA-256.js"></script>
        <script type="text/javascript">
            var sendUserInfo = function () {
                var sha256 = new Sha256();
                document.getElementById("hashpswd").value = sha256.hash(document.getElementById("password").value, true);
                document.getElementById("adduserForm").submit();
            }
        </script>
    </head>
    <body>
        <div class="pageItem header" style="font-size:large;">Новый пользователь</div>
        <div class="pageItem">
            <form id="addUserForm" method="POST" action="UserModifity/">
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
                                <input type="text" name="nick" value="" maxlength="50" class="maxWidth" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>Пароль:&nbsp;</span><span class="required">*</span>
                            </td>
                            <td colspan="6">
                                <input type="text" id="password" value="" maxlength="100" class="maxWidth" />
                                <input type="hidden" id="hashpswd" name="hashpswd" value="" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>Фамилия:&nbsp;</span>
                            </td>
                            <td colspan="6">
                                <input type="text" name="family" value="" maxlength="100" class="maxWidth" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>Имя:&nbsp;</span>
                            </td>
                            <td colspan="6">
                                <input type="text" name="name" value="" maxlength="100" class="maxWidth" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>E-mail:&nbsp;</span>
                            </td>
                            <td colspan="6">
                                <input type="text" name="email" value="" maxlength="100" class="maxWidth" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>Блокировка:&nbsp;</span>
                            </td>
                            <td class="alignCenter">
                                <input type="checkbox" name="isLocked" value="" />
                            </td>
                            <td>&nbsp;</td>
                            <td colspan="3">
                                <span>В бан-листе:&nbsp;</span>
                            </td>
                            <td class="alignCenter">
                                <input type="checkbox" name="isBaned" value="" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="11">
                                <div style="text-align: center;">
                                    <input type="button" value="Добавить" onClick="sendUserInfo()" />
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
