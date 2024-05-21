package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetPostAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException {

        JsonObject json = new JsonObject();

        json.addProperty("data", new PostService().post(Integer.parseInt(request.getParameter("postId"))).toString());

        request.setAttribute("data", new ResponseModel(200, json, "success"));

        return new URLModel();
    }
}
