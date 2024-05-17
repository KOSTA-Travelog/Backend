package kosta.travelog.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/*")
public class FrontControllerServlet extends HttpServlet{

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. cmd
        String cmd = req.getPathInfo();
        // 2. Action
        Action a = ActionFactory.getAction(cmd);
        URLModel url = a.execute(req);
        // 3. View
        if(url.isFlag()) {
            resp.sendRedirect(url.getPage());
        }
        else {
            req.getRequestDispatcher("/" + url.getPage()).forward(req, resp);
        }
    }
}
