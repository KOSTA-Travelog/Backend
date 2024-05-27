package kosta.travelog.servlet.action;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
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
        ResponseModel responseModel;
        try {
            PostVO vo = PostVO.builder()
                    .postTitle(request.getParameter("postTitle"))
                    .postDescription(request.getParameter("postDescription"))
                    .postHashtag(request.getParameter("postHashtag"))
                    .postStatus(request.getParameter("postStatus").charAt(0))
                    .userId(request.getParameter("userId")).build();
            boolean post = new PostService().createPost(vo);
            if (post) {
                responseModel = new ResponseModel(201, "Created");
            } else {
                responseModel = new ResponseModel(500, "Failed");
            }
        } catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");
        } catch (DatabaseQueryException e) {
            responseModel = new ResponseModel(500, "데이터를 불러오지 못했습니다.");
        }
        request.setAttribute("result", responseModel);

        return new URLModel();
    }
}
