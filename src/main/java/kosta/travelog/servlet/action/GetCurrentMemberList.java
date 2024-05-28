package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.CommunityService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetCurrentMemberList implements Action {

    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;

        try {
            JsonObject json = new JsonObject();
            json.addProperty("data", new CommunityService().getCurrenetMemberList(Integer.parseInt(request.getParameter("communityId"))).toString());
            responseModel = new ResponseModel(200, json, "success");
        } catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");
        } finally {
            request.setAttribute("data", responseModel);
        }

        request.setAttribute("data", responseModel);

        return new URLModel();
    }

}
