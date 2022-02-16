package cc.openhome.gossip;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register.do")
public class Register extends HttpServlet {

    private final String USERS = "/Users/brandon/Desktop/JavaWorkspace/ProjectGossip/gossip/src/main/webapp/users";
    private final String SUCCESS_VIEW = "success.view";
    private final String ERROR_VIEW = "error.view";

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmedPassword = req.getParameter("confirmedPassword");

        List<String> errors = new ArrayList<String>();
        if (isInvalidEmail(email)) {
            errors.add("未填写邮件或邮件格式不正确");
        }
        if (isInvalidUsername(username)) {
            errors.add("用户名为空或用户名已存在");
        }
        if (isInvalidPassword(password, confirmedPassword)) {
            errors.add("请确认密码符合格式并再次确认密码");
        }
        String resultPage = ERROR_VIEW;
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
        } else {
            resultPage = SUCCESS_VIEW;
            createUserData(email, username, password);
        }
        req.getRequestDispatcher(resultPage).forward(req, resp);
    }

    private void createUserData(String email, String username, String password) throws IOException {
        File userhome = new File(USERS + "/" + username);
        userhome.mkdir();
        BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
        writer.write(email + "\t" + password);
        writer.close();
    }

    private boolean isInvalidPassword(String password, String confirmedPassword) {
        return password == null || password.length() < 6 || password.length() > 16
                || !password.equals(confirmedPassword);
    }

    private boolean isInvalidUsername(String username) {
        for (String file : new File(USERS).list()) {
            if (file.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInvalidEmail(String email) {
        return email == null || !email.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    }
}