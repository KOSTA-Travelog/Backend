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

public class AddPostImageAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        try {
            boolean result = new PostService().createImage(PostVO.builder()
                    .postTitle(request.getParameter("postTitle"))
                    .postId(Integer.parseInt(request.getParameter("postId"))).build());

            JsonObject json = new JsonObject();
            json.addProperty("result", result);
            request.setAttribute("result", new ResponseModel(201, json, "created"));

        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }

        return new URLModel();
    }
}
