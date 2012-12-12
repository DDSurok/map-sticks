/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author d.duritskij
 */
public class UserForms extends HttpServlet {

    private void createFormEdit(int Id, StringBuilder response)
            throws ServletException, IOException {
        response.append("<html>");
        response.append("<head>");
        response.append("<title>Edit user page</title>");
        response.append("</head>");
        response.append("<body class=\"adminPage\">");
        response.append("Форма редактирования пользователя");
        response.append("</body>");
        response.append("</html>");
    }
    
    private void createFormSuccessAdd(HttpServletRequest request, StringBuilder response)
            throws ServletException, IOException {
        response.append("<html>");
        response.append("<head>");
        response.append("<title>Add user success</title>");
        response.append("</head>");
        response.append("<body class=\"adminPage\">");
        response.append("Пользователь успешно добавлен");
        response.append("</body>");
        response.append("</html>");
    }
    
    private void createFormSuccessEdit(HttpServletRequest request, StringBuilder response)
            throws ServletException, IOException {
        response.append("<html>");
        response.append("<head>");
        response.append("<title>Add user success</title>");
        response.append("</head>");
        response.append("<body class=\"adminPage\">");
        response.append("Изменения сохранены");
        response.append("</body>");
        response.append("</html>");
    }
    
    private void createFormFailure(HttpServletRequest request, StringBuilder response)
            throws ServletException, IOException {
        response.append("<html>");
        response.append("<head>");
        response.append("<title>Add user success</title>");
        response.append("</head>");
        response.append("<body class=\"adminPage\">");
        response.append("Возникла ошибка");
        response.append("</body>");
        response.append("</html>");
    }
    
    private void doWork (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder res = new StringBuilder();
        String mode = request.getParameter("mode");
        if (mode == null) {
            mode = "failure";
        }
        if (mode.equals("add")) {
            request.getRequestDispatcher("/admin-console/formAddUser.jsp").forward(request, response);
            return;
        } else if (mode.equals("edit")) {
            int id = Integer.valueOf(request.getParameter("user"));
            createFormEdit(id, res);
        } else if (mode.equals("add_")) {
            createFormSuccessAdd(request, res);
        } else if (mode.equals("edit_")) {
            createFormSuccessEdit(request, res);
        } else {
            createFormFailure(request, res);
        }
        ResponseText = res.toString();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.print(res.toString());
        } finally {
            out.close();
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
        return "";
    }
    
    private String ResponseText;
    
    public void setResponseText (String value) {
        ResponseText = value;
    }
    
    public String getResponseText () {
        return ResponseText;
    }
}
