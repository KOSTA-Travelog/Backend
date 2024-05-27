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
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;
        try {
            String userId = request.getParameter("userId");
            if (userId == null) {
                throw new BadRequestException("Required inputs are missing.");
            }

            new AccountService().cancelAccount(userId);

            responseModel = new ResponseModel(200, "success");

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
