package kosta.travelog.servlet.action;

import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.CommunityMemberService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.CommunityUserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AddMemberToCommunity implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        try {
            String communityId = request.getParameter("communityId");
            String userId = request.getParameter("userId");


            if (communityId == null || userId == null) {
                throw new BadRequestException("Required inputs are missing.");
            }

            boolean result = new CommunityMemberService().enrollCommunityMember(CommunityUserVO.builder()
                    .communityId(Integer.parseInt(communityId))
                    .userId(userId).build());

            if (result) {
                request.setAttribute("data", new ResponseModel(201, "created"));
            } else {
                throw new BadRequestException("Failed to add post to community.");
            }

        } catch (BadRequestException e) {
            request.setAttribute("data", new ResponseModel(400, e.getMessage()));
        } catch (DatabaseConnectException e) {
            request.setAttribute("data", new ResponseModel(500, "Server Error"));
        }
        return new URLModel();
    }
}
