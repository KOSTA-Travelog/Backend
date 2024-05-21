package kosta.travelog.servlet.action;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SetOpenStatusAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException {

        try {

            boolean result = new PostService().editPostStatus
                    (request.getParameter("postStatus").charAt(0),
                            Integer.parseInt(request.getParameter("postId")));

            if (result) {
                request.setAttribute("result", new ResponseModel(200, "success"));
            }

        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }


        return new URLModel();
    }
}
