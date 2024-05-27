package kosta.travelog.servlet.action;

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
public class SetUserAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        try {
            String name = request.getParameter("name");
            String nickname = request.getParameter("nickname");
            String profileImage = request.getParameter("profileImage");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String bio = request.getParameter("bio");
            String userId = request.getParameter("userId");

            if (userId == null || phoneNumber == null || password == null || nickname == null || name == null) {
                throw new BadRequestException("Required inputs are missing.");
            }

            new AccountService().editUserInfo(UserVO.builder()
                    .name(name)
                    .nickname(nickname)
                    .profileImage(profileImage)
                    .password(password)
                    .phoneNumber(phoneNumber)
                    .bio(bio)
                    .userId(userId).build());

            request.setAttribute("result", new ResponseModel(200, "success"));

        } catch (DatabaseConnectException e) {
            request.setAttribute("data", new ResponseModel(500, "Server Error"));
        } catch (BadRequestException e) {
            request.setAttribute("data", new ResponseModel(400, e.getMessage()));

        }
        return new URLModel();
    }
}
