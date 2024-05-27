package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.CommunityService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class GetMyCommunityListAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        JsonObject json = new JsonObject();

        json.addProperty("data", new CommunityService().getMyCommunityList(request.getParameter("userId")).toString());
        request.setAttribute("data", new ResponseModel(200, json, "success"));

        return new URLModel();
    }
}
