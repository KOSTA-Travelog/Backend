package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.execption.DatabaseConnectException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class PostListAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        try {

            //            Gson gson = new Gson();
            //
            //            String data = gson.toJson(new PostService().postList());
            JsonObject json = new JsonObject();

            json.addProperty("data", new PostService().postList().toString());

            request.setAttribute("data", new ResponseModel(200, json, "success"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }
        return new URLModel();
    }
}
