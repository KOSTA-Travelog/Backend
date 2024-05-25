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
import java.sql.SQLException;

public class AddUserAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String nickname = request.getParameter("nickname");

            if (name == null || email == null || password == null || phoneNumber == null || nickname == null) {
                throw new BadRequestException("Required inputs are missing.");
            }

            boolean result = new AccountService().register(UserVO.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .nickname(nickname).build());

            if (result) {
                request.setAttribute("data", new ResponseModel(201, "created"));
            } else {
                throw new BadRequestException("Failed to create community.");
            }

        } catch (BadRequestException e) {
            request.setAttribute("data", new ResponseModel(400, e.getMessage()));
        } catch (DatabaseConnectException e) {
            request.setAttribute("data", new ResponseModel(500, "Server Error"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new URLModel();
    }
}
