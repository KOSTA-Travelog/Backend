package kosta.travelog.servlet.action;

import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.CommunityMemberService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.CommunityUserVO;
import kosta.travelog.vo.NotificationVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@Slf4j
public class AddPendingMember implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
        try {

            String communityId = request.getParameter("communityId");
            String userId = request.getParameter("userId");
            String userId2 = (String) request.getSession().getAttribute("userId");

            log.info(userId2);
            if (communityId == null || userId == null || userId2 == null) {
                throw new BadRequestException("Required inputs are missing.");
            }

            log.info(communityId);
            log.info(userId);

            boolean result = new CommunityMemberService().addUserToPendingList(CommunityUserVO.builder()
                            .communityId(Integer.parseInt(communityId))
                            .userId(userId).build(),
                    NotificationVO.builder()
                            .userId(userId)
                            .userId2(userId2)
                            .communityId(Integer.parseInt(communityId)).build());

            if (result) {
                request.setAttribute("data", new ResponseModel(201, "created"));
            } else {
                throw new BadRequestException("Failed to add community member");
            }

        } catch (BadRequestException e) {
            request.setAttribute("data", new ResponseModel(400, e.getMessage()));
        } catch (DatabaseConnectException e) {
            request.setAttribute("data", new ResponseModel(500, "Server Error"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new URLModel();
    }
}
