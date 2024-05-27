package kosta.travelog.servlet.action;

import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.AccountService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SetPasswordAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;
        try {
            boolean result = new AccountService().editPassword(UserVO.builder()
                    .password(request.getParameter("password"))
                    .userId(request.getParameter("userId")).build());
            if (result) {
                responseModel = new ResponseModel(200, "success");
            } else {
                throw new BadRequestException("");
            }
        } catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");
        } catch (DatabaseQueryException e) {
            responseModel = new ResponseModel(500, "데이터를 불러오지 못했습니다.");
        } catch (BadRequestException e) {
            responseModel = new ResponseModel(400, e.getMessage());
        } finally {
            request.setAttribute("data", responseModel);
        }

        return new URLModel();
    }
}
