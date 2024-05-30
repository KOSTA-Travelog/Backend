package kosta.travelog.servlet.action;

import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.AccountService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.Hashing;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class AddUserAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel data;
        try {
            String passwordCheck = request.getParameter("passwordCheck");


            String pw = request.getParameter("pw");
            Hashing hasher = new Hashing();
            boolean result = new AccountService()
                    .register(UserVO.builder()
                                    .name(request.getParameter("name"))
                                    .email(request.getParameter("email"))
                                    .password(hasher.getHex(pw))
                                    .phoneNumber(request.getParameter("phoneNumber"))
                                    .nickname(request.getParameter("nickname")).build(),
                            hasher.getHex(passwordCheck)
                    );

            if (result) {
                data = new ResponseModel(201, "created");
            } else {
                data = new ResponseModel(500, "회원가입에 실패했습니다.");
            }

        } catch (BadRequestException e) {
            data = new ResponseModel(400, e.getMessage());
        } catch (DatabaseConnectException e) {
            data = new ResponseModel(500, "Server Error");
        } catch (DatabaseQueryException e) {
            data = new ResponseModel(500, "Server Error");
        }
        request.setAttribute("data", data);

        return new URLModel();
    }
}
