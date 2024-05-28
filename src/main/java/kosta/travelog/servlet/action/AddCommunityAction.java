package kosta.travelog.servlet.action;

import kosta.travelog.dto.LoginDTO;
import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.CommunityService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.CommunityVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class AddCommunityAction implements Action {

    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        try {
            LoginDTO member = (LoginDTO) request.getSession().getAttribute("user");
            String userId = member.getUserId();

            String communityTitle = request.getParameter("communityTitle");
            String communityDescription = request.getParameter("communityDescription");
            String communityHashtag = request.getParameter("communityHashtag");
            String communityImage = request.getParameter("communityImage");
            String communityStatus = request.getParameter("communityStatus");

            boolean result = new CommunityService().createCommunity(CommunityVO.builder()
                    .communityTitle(communityTitle)
                    .communityDescription(communityDescription)
                    .communityHashtag(communityHashtag)
                    .communityImage(communityImage)
                    .communityStatus(communityStatus.charAt(0))
                    .userId(userId).build());

            if (result) {
                request.setAttribute("data", new ResponseModel(201, "created"));
            } else {
                throw new BadRequestException("Failed to create community.");
            }

        } catch (BadRequestException e) {
            request.setAttribute("data", new ResponseModel(400, e.getMessage()));
        } catch (DatabaseConnectException e) {
            request.setAttribute("data", new ResponseModel(500, "Server Error"));
        }
        return new URLModel();
    }
}
