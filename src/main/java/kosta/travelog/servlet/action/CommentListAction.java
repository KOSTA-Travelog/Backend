package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.CommentService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CommentListAction implements Action {

    @Override
    public URLModel execute(HttpServletRequest request)
            throws ServletException, IOException {
        ResponseModel responseModel = null;
        JsonObject json = new JsonObject();

        try {
            json.addProperty("data", new CommentService().commentList(Integer.parseInt(request.getParameter("commentId"))).toString());
        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }
        responseModel = new ResponseModel(200, json, "success");

        request.setAttribute("data", responseModel);
        return new URLModel();
    }

}
