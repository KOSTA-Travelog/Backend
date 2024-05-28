package kosta.travelog.servlet.action;

import kosta.travelog.dto.LoginDTO;
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


/**
 * @author gyeoul
 */
@Slf4j
public class LoginAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        log.info("LoginAction");
        log.info("req: {}", request.getParameter("id"));
        log.info("req: {}", request.getParameter("pw"));
        try {
            AccountService service = new AccountService();
            LoginDTO member = service.login(UserVO.builder()
                    .email(request.getParameter("id"))
                    .password(request.getParameter("pw"))
                    .build());
            log.info("member: {}", member);
            if (member != null) {
                request.getSession().setAttribute("user", member);

                request.setAttribute("data", new ResponseModel(200, "success"));
            } else {
                request.setAttribute("data", new ResponseModel(400, "fail"));
            }
        } catch (DatabaseConnectException e) {
            log.info("Connection Error:{}", e.getMessage());
            request.setAttribute("data", new ResponseModel(500, "fail"));
        } catch (DatabaseQueryException e) {
            log.info("Query Error:{}", e.getMessage());
            request.setAttribute("data", new ResponseModel(500, "fail"));
        }
        return new URLModel("response.jsp");
    }
}