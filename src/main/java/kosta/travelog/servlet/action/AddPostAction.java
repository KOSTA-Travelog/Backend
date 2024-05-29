package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.dto.LoginDTO;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
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
        JsonObject json = new JsonObject();
        try {
            LoginDTO member = (LoginDTO) request.getSession().getAttribute("user");
            String userId = member.getUserId();
            log.info(userId);

            long post = new PostService().createPost(PostVO.builder()
                    .postTitle(request.getParameter("postTitle"))
                    .postDescription(request.getParameter("postDescription"))
                    .postHashtag(request.getParameter("postHashtag"))
                    .postStatus(request.getParameter("postStatus").charAt(0))
                    .userId(userId)
                    .build()
            );
            json.addProperty("data", post);
            responseModel = new ResponseModel(201, json, "Created");
        } catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");
        } catch (DatabaseQueryException e) {
            responseModel = new ResponseModel(500, "Failed");
        }
        request.setAttribute("data", responseModel);
        return new URLModel();
    }
}
