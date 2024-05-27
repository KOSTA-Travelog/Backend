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
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;
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

            responseModel = new ResponseModel(200, "success");

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
