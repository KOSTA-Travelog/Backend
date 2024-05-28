package kosta.travelog.servlet.action;

import kosta.travelog.dto.LoginDTO;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.service.CommunityMemberService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.CommunityUserVO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class AddMemberToCommunityAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
        try {
            String communityId = request.getParameter("communityId");

            LoginDTO member = (LoginDTO) request.getSession().getAttribute("user");
            String userId = member.getUserId();

            boolean result = new CommunityMemberService().enrollCommunityMember(CommunityUserVO.builder()
                    .communityId(Integer.parseInt(communityId))
                    .userId(userId).build());

            request.setAttribute("data", new ResponseModel(201, "created"));

        } catch (DatabaseConnectException e) {
            request.setAttribute("data", new ResponseModel(500, "Server Error"));
        }
        return new URLModel();
    }
}
