package kosta.travelog.servlet;


import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ping", value = "/ping")
public class PingPong extends HttpServlet{

    private String message;

    @Override
    public void init() {
        message = "pong";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> data = new HashMap<>();
        data.put("response", "pong");

        req.setAttribute("data", data);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
