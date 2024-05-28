package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.CommunityPostService;
import kosta.travelog.service.CommunityService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class GetCommunityPostListAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {

        ResponseModel responseModel = null;
        JsonObject json = new JsonObject();

        boolean isMember = false;
        try {
            isMember = new CommunityService().isCommunityMember(Integer.parseInt(request.getParameter("id")), request.getParameter("userId"));

            if (isMember) {
                json.addProperty("data", new CommunityPostService().getCommunityPostListForMember(Integer.parseInt(request.getParameter("id"))).toString());

            } else {
                json.addProperty("data", new CommunityPostService().getCommunityPostListForGuest(Integer.parseInt(request.getParameter("id"))).toString());
            }

            responseModel = new ResponseModel(200, json, "success");

            request.setAttribute("data", responseModel);

        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }
        return new URLModel();
    }
}
