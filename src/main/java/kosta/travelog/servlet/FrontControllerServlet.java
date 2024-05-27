package kosta.travelog.servlet;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/api/*")
public class FrontControllerServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 1. cmd
        String cmd = req.getPathInfo();

        // 2. Action
        Action a = ActionFactory.getAction(cmd);

        URLModel url = a.execute(req);

        // 3. View
        if (url.isFlag()) {
            resp.sendRedirect(url.getPage());
        } else {
            req.getRequestDispatcher("/" + url.getPage()).forward(req, resp);
        }
    }
}
