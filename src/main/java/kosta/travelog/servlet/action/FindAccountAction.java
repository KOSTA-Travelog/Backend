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
public class FindAccountAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;

        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");

        try {

            JsonObject json = new JsonObject();

            String email = new AccountService().findAccount(UserVO.builder()
                    .name(name)
                    .phoneNumber(phoneNumber)
                    .build());

            if (email == null) {
                throw new BadRequestException("cannot find user");
            }

            json.addProperty("data", email);
            responseModel = new ResponseModel(200, json, "success");

        } catch (DatabaseConnectException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "Server Error");
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "데이터를 불러오지 못했습니다.");
        } catch (BadRequestException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(400, e.getMessage());
        } finally {
            request.setAttribute("data", responseModel);
        }
        return new URLModel();
    }
}
