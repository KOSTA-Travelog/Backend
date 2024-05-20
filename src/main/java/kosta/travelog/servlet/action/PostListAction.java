package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class PostListAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;
        try {
            JsonObject json = new JsonObject();
            json.addProperty("data", new PostService().postList().toString());
            responseModel = new ResponseModel(200, json, "success");
        } catch (DatabaseConnectException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "Server Error");
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "포스트 호출에 실패했습니다.");
        } finally {
            request.setAttribute("data", responseModel);
        }
        return new URLModel();
    }
}
