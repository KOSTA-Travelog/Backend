package kosta.travelog.servlet;

import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RemoveAccountAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        try {
            String userId = request.getParameter("userId");
            if (userId == null) {
                throw new BadRequestException("Required inputs are missing.");
            }

            new AccountService().cancelAccount(userId);

            request.setAttribute("result", new ResponseModel(200, "success"));

        } catch (DatabaseConnectException e) {
            request.setAttribute("data", new ResponseModel(500, "Server Error"));
        } catch (BadRequestException e) {
            request.setAttribute("data", new ResponseModel(400, e.getMessage()));

        }
        return new URLModel();
    }
}
