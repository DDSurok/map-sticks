/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.ddsurok.datamodel.db.User;
import ru.ddsurok.utils.UserUtil;

/**
 *
 * @author d.duritskij
 */
public class UserWorker extends HttpServlet {

    private void parameter2Attribute(HttpServletRequest request, String name) {
        if (request.getParameter(name) == null) {
            request.setAttribute(name, "");
        }
    else {
            request.setAttribute(name, request.getParameter(name));
        }
    }

    private void doWork (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if (mode == null) {
            request.getRequestDispatcher("stub/500.html").forward(request, response);
            return;
        }
        if (mode.equals("add")) {
            parameters2Attributes(request);
            hashpswd2Password(request);
            request.setAttribute("mode", "add_");
            request.getRequestDispatcher("UserModifity.jsp").forward(request, response);
            return;
        }
        if (mode.equals("edit")) {
            try {
                Integer id = Integer.valueOf(request.getParameter("id"));
                UserUtil utils = new UserUtil();
                utils.getUserById(id);
                parameters2Attributes(request);
                hashpswd2Password(request);
                request.setAttribute("mode", "edit_");
                request.getRequestDispatcher("UserModifity.jsp").forward(request, response);
            } catch (NumberFormatException ex) {
                request.getRequestDispatcher("stub/500.html").forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("errorMessage", ex.getMessage());
                request.getRequestDispatcher("stub/ErrorMessagePrint.jsp").forward(request, response);
            } catch (Throwable th) {
                request.getRequestDispatcher("stub/500.html").forward(request, response);
            }
            return;
        }
        if (mode.equals("add_")) {
            User user = new User();
            user.setNick(request.getParameter("nick"));
            user.setHashpswd(request.getParameter("hashpswd"));
            user.setFamily(request.getParameter("family"));
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setIslocked(Boolean.valueOf(request.getParameter("isLocked")));
            user.setIsbaned(Boolean.valueOf(request.getParameter("isBaned")));
            UserUtil utils = new UserUtil();
            try {
                utils.addUser(user);
                request.setAttribute("defMode", "0");
                request.getRequestDispatcher("AddUserSuccess.html").forward(request, response);
            } catch(Throwable th) {
                // TODO: Добавить обработчик ошибкок и перенаправление ошибки в контекст запроса
                request.setAttribute("errorCode", "1");
                request.getRequestDispatcher("UserModifity.jsp").forward(request, response);
            }
            return;
        }
        if (mode.equals("edit_")) {
            User user = new User();
            user.setId(Integer.valueOf(request.getParameter("id")));
            user.setNick(request.getParameter("nick"));
            user.setHashpswd(request.getParameter("hashpswd"));
            user.setFamily(request.getParameter("family"));
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setIslocked(Boolean.valueOf(request.getParameter("isLocked")));
            user.setIsbaned(Boolean.valueOf(request.getParameter("isBaned")));
            UserUtil utils = new UserUtil();
            try {
                utils.updateUser(user.getId(), user);
                request.setAttribute("defMode", "1");
                request.getRequestDispatcher("EditUserSuccess.html").forward(request, response);
            } catch(Throwable th) {
                // TODO: Добавить обработчик ошибкок и перенаправление ошибки в контекст запроса
                request.setAttribute("defMode", "0");
                request.getRequestDispatcher("UserModifity.jsp").forward(request, response);
            }
            return;
        }
        request.getRequestDispatcher("stub/404.html").forward(request, response);
    }

    private void parameters2Attributes(HttpServletRequest request) {
        parameter2Attribute(request, "nick");
        parameter2Attribute(request, "hashpswd");
        parameter2Attribute(request, "family");
        parameter2Attribute(request, "name");
        parameter2Attribute(request, "email");
        parameter2Attribute(request, "isLocked");
        parameter2Attribute(request, "idBaned");
    }
    
    private void hashpswd2Password(HttpServletRequest request) {
        if (request.getAttribute("hashpswd").equals("")) {
            request.setAttribute("password", "");
        }
        else {
            request.setAttribute("password", "&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;&bull;");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doWork(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doWork(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Сервлет выполняет обработку запросов об изменении данных"
                + " о пользователе, добавлении нового пользователя и"
                + " удаленнии пользователя из системы.";
    }
}
