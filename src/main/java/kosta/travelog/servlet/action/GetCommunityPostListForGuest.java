package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.CommunityPostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetCommunityPostListForGuest implements Action {

    @Override
    public URLModel execute(HttpServletRequest request)
            throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        ResponseModel responseModel = null;
        JsonObject json = new JsonObject();

        json.addProperty("data", new CommunityPostService().getCommunityPostListForGuest(Integer.parseInt(request.getParameter("communityId"))).toString());
        responseModel = new ResponseModel(200, json, "success");

        request.setAttribute("data", responseModel);

        return new URLModel();
    }

}
