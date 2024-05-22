package kosta.travelog.servlet.action;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.AccountService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoadProfileAction implements Action {

    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        String userId = request.getParameter("userId");
        AccountService service = new AccountService();
        UserVO profile = service.getProfile(userId);
        if (profile != null) {
            request.getSession().setAttribute("profile", profile);

        }

        return null;
    }

}
