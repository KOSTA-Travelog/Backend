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

public class AddPostAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        try {
            PostVO vo = PostVO.builder()
                    .postTitle(request.getParameter("postTitle"))
                    .postDescription(request.getParameter("postDescription"))
                    .postHashtag(request.getParameter("postHashtag"))
                    .postStatus(request.getParameter("postStatus").charAt(0))
                    .userId(request.getParameter("userId")).build();

            new PostService().createPost(vo);

            request.setAttribute("result", new ResponseModel(201, "created"));

        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }

        return new URLModel();
    }
}
