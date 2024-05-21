package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.PostVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class ImageListAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException {

        JsonObject json = new JsonObject();

        List<PostVO> vo = null;
        try {
            vo = new PostService().imageList(Integer.parseInt(request.getParameter("postId")));
        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }

        json.addProperty("imageList", vo.toString());

        request.setAttribute("data", new ResponseModel(200, json, "success"));

        return new URLModel();
    }
}
