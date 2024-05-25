package kosta.travelog.servlet.action;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.CommunityPostService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RemoveCommunityPostAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {

        try {
            boolean result = new CommunityPostService().deleteCommunityPost(Integer.parseInt(request.getParameter("communityPostId")));
            if (result) {
                request.setAttribute("result", new ResponseModel(200, "success"));
            }
        } catch (DatabaseConnectException e) {
            request.setAttribute("data", new ResponseModel(500, "Server Error"));
        }

        return new URLModel();
    }
}
