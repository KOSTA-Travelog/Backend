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
public class CountUserPostAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;
        try {
            JsonObject json = new JsonObject();
            json.addProperty("data", new PostService().countUserPostNumber(request.getParameter("userId")));
            responseModel = new ResponseModel(200, json, "success");
        } catch (DatabaseConnectException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "Server Error");
        } finally {
            request.setAttribute("data", responseModel);
        }
        return new URLModel();
    }
}
