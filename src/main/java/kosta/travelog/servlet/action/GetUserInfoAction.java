package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.AccountService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class GetUserInfoAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {

//        LoginDTO member = (LoginDTO) request.getSession().getAttribute("user");
//        String userId = member.getUserId();

//        log.info(userId);
        JsonObject json = new JsonObject();

        try {
            json.addProperty("data", new AccountService().getLoginUserInfo(request.getParameter("userId")).toString());

            log.info(json.toString());
        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("data", new ResponseModel(200, json, "success"));

        return new URLModel();
    }
}
