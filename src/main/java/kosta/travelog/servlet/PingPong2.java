package kosta.travelog.servlet;

import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PingPong2 implements Action {

    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("response", "pong");
        request.setAttribute("data", new ResponseModel(200, jsonObject, ""));
        return new URLModel("index.jsp");
    }
}
