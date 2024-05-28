package kosta.travelog.servlet.action;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.CommentService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DeleteCommentAction implements Action {

    @Override
    public URLModel execute(HttpServletRequest request)
            throws ServletException, IOException {
        try {
            int commentId = Integer.parseInt(request.getParameter("commentId"));

            new CommentService().deleteComment(commentId);

            request.setAttribute("result", new ResponseModel(201, "deleted"));

        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }

        return new URLModel();
    }

}
