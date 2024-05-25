package kosta.travelog.servlet.action;

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
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        try {
            boolean result = new AccountService().editPassword(UserVO.builder()
                    .password(request.getParameter("password"))
                    .userId(request.getParameter("userId")).build());

            request.setAttribute("data", new ResponseModel(200, "success"));

        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        }

        return new URLModel();
    }
}
