package kosta.travelog.servlet.action;

import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
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
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        ResponseModel responseModel = null;
        try {
            String communityMemberId = request.getParameter("communityMemberId");
            if (communityMemberId == null || communityMemberId.isEmpty()) {
                throw new BadRequestException("Required inputs are missing.");
            }

            boolean result = new CommunityMemberService().leaveCommunity(
                    Integer.parseInt(communityMemberId));
            if (result) {
                responseModel = new ResponseModel(200, "success");
            } else {
                throw new BadRequestException("Failed to remove community member");
            }

        } catch (DatabaseConnectException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(500, "Server Error");
        } catch (BadRequestException e) {
            log.error(e.getMessage());
            responseModel = new ResponseModel(400, e.getMessage());
        } finally {
            request.setAttribute("data", responseModel);
        }
        return new URLModel();
    }
}