package kosta.travelog.servlet.action;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.CommunityMemberService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@Slf4j
public class SetPendingMemberToCommunity implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {

        int communityMemberId = Integer.parseInt(request.getParameter("communityMemberId"));
        String notificationId = request.getParameter("notificationId");

        try {
            boolean result = new CommunityMemberService().editPendingMemberToMember(communityMemberId, notificationId);
            log.info(String.valueOf(result));
            request.setAttribute("data", new ResponseModel(200, "success"));

        } catch (DatabaseConnectException e) {
            request.setAttribute("data", new ResponseModel(500, "server error"));
        } catch (SQLException e) {
            request.setAttribute("data", new ResponseModel(500, "server error"));
        }

        return new URLModel();
    }
}
