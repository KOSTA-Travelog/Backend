package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class GetPostimageListAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        JsonObject json = new JsonObject();
        ResponseModel responseModel = null;

        try {
            json.addProperty("data", new PostService().getPostFirstImage(request.getParameter("userId")).toString());
            responseModel = new ResponseModel(200, json, "success");
        } catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");
        }
        request.setAttribute("data", responseModel);
        return new URLModel();
    }
}
