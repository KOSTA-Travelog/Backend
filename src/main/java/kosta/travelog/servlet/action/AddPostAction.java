package kosta.travelog.servlet.action;

import kosta.travelog.dto.LoginDTO;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.PostVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class AddPostAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel;
        try {
            LoginDTO member = (LoginDTO) request.getSession().getAttribute("user");
            String userId = member.getUserId();
            log.info(userId);

            PostVO vo = PostVO.builder()
                    .postTitle(request.getParameter("postTitle"))
                    .postDescription(request.getParameter("postDescription"))
                    .postHashtag(request.getParameter("postHashtag"))
                    .postStatus(request.getParameter("postStatus").charAt(0))
                    .userId(userId).build();

            boolean post = new PostService().createPost(vo);
            if (post) {
                responseModel = new ResponseModel(201, "Created");
            } else {
                responseModel = new ResponseModel(500, "Failed");
            }
        } catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");
        }
        request.setAttribute("result", responseModel);

        return new URLModel();
    }
}
