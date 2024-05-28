package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.BadRequestException;
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
public class GetPostAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;

        try {
            JsonObject json = new JsonObject();

            PostVO post = new PostService().post(Integer.parseInt(request.getParameter("postId")));
            if (post == null) {
                throw new BadRequestException("Cannot find post");
            }
            json.addProperty("data", post.toString());
            responseModel = new ResponseModel(200, json, "success");

        } catch (DatabaseConnectException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "Server Error");
        } catch (DatabaseQueryException e) {
            responseModel = new ResponseModel(500, "데이터를 불러오지 못했습니다.");
        } catch (BadRequestException e) {
            responseModel = new ResponseModel(400, e.getMessage());
        } finally {
            request.setAttribute("data", responseModel);
        }
        return new URLModel();
    }
}
