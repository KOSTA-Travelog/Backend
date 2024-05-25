package kosta.travelog.servlet.action;

import kosta.travelog.exception.BadRequestException;
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

@Slf4j
public class RemoveCommunityMember implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        try {
            String communityMemberId = request.getParameter("communityMemberId");
            if (communityMemberId == null || communityMemberId == "") {
                throw new BadRequestException("Required inputs are missing.");
            }

            boolean result = new CommunityMemberService().leaveCommunity(
                    Integer.parseInt(communityMemberId));
            if (result) {
                request.setAttribute("result", new ResponseModel(200, "success"));
            }

        } catch (DatabaseConnectException e) {
            throw new RuntimeException(e);
        } catch (BadRequestException e) {
            request.setAttribute("data", new ResponseModel(400, e.getMessage()));

        }
        return new URLModel();
    }
}