package kosta.travelog.servlet.action;

import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.PostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SetOpenStatusAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;
        try {
            boolean result = new PostService().editPostStatus
                    (request.getParameter("postStatus").charAt(0),
                            Integer.parseInt(request.getParameter("postId")));
            if (result) {
                responseModel = new ResponseModel(200, "success");
            } else {
                throw new BadRequestException("Unable Status");
            }
        } catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");

        } catch (BadRequestException e) {
            responseModel = new ResponseModel(400, e.getMessage());
        } finally {
            request.setAttribute("data", responseModel);
        }

        return new URLModel();
    }
}
