package kosta.travelog.servlet.action;

import com.google.gson.JsonObject;
import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.AccountService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class CheckAccountAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        ResponseModel responseModel = null;

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        log.info(email);
        log.info(password);

        try {

            JsonObject json = new JsonObject();

            String user = new AccountService().verifyUser(UserVO.builder()
                    .email(email)
                    .password(password)
                    .build());

            if (user == null) {
                throw new BadRequestException("cannot find user");
            }

            json.addProperty("data", user);
            responseModel = new ResponseModel(200, json, "success");

        } catch (BadRequestException e) {
            responseModel = new ResponseModel(400, e.getMessage());

        } catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");

        } finally {
            request.setAttribute("data", responseModel);
        }

        return new URLModel();
    }
}
