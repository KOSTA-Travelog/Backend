package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.CommunityService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class GetAllCommunityListAction implements Action {

    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {

        ResponseModel responseModel = null;
        try {
            JsonObject json = new JsonObject();
            json.addProperty("data", new CommunityService().getAllCommunityList().toString());
            responseModel = new ResponseModel(200, json, "success");
        } catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");
        } finally {
            request.setAttribute("data", responseModel);
        }

        return new URLModel();
    }

}

