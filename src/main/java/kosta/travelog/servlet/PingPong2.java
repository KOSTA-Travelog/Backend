package kosta.travelog.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PingPong2 implements Action {

    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {

        Map<String, String> data = new HashMap<>();
        data.put("response", "pong");

        return new URLModel(data);
    }
}
