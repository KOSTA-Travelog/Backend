package kosta.travelog.servlet.action;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.CommunityService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.CommunityVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SetCommunityAction implements Action {
    @Override
    public URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {

        new CommunityService().editCommunity(CommunityVO.builder()
                .communityTitle(request.getParameter("communityTitle"))
                .communityDescription(request.getParameter("communityDescription"))
                .communityHashtag(request.getParameter("communityHashtag"))
                .communityImage(request.getParameter("communityImage"))
                .communityStatus(request.getParameter("communityStatus").charAt(0))
                .communityId(Integer.parseInt(request.getParameter("communityId")))
                .build());
        
        request.setAttribute("result", new ResponseModel(200, "success"));

        return new URLModel();
    }
}