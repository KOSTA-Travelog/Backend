package kosta.travelog.servlet.action;

import kosta.travelog.execption.DatabaseConnectException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.PostVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SetPostAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException {
        boolean result = new PostService().editPost(PostVO.builder().postTitle(request.getParameter("postTitle"))
                .postDescription(request.getParameter("postDescription"))
                .postHashtag(request.getParameter("postHashtag"))
                .postStatus(request.getParameter("postStatus").charAt(0))
                .postId(Integer.parseInt(request.getParameter("postId"))).build());

        if (result) {
            request.setAttribute("data", new ResponseModel(200, "success"));
        }

        return new URLModel();
    }
}
