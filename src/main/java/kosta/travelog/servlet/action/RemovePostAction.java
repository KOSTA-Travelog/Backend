package kosta.travelog.servlet.action;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RemovePostAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {

        try {
            new PostService().deletePost(Integer.parseInt(request.getParameter("postId")));

            request.setAttribute("result", new ResponseModel(200, "success"));

        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }

        return new URLModel();
    }
}
