package cc.openhome.gossip;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member.view")
public class Member extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Member</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>User" + request.getParameter("username") + " Hello!</h1>");
        out.println("<a href='index.html'>Back to Home</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
