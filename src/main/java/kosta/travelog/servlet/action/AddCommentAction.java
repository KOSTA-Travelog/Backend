package kosta.travelog.servlet.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.CommentService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.CommentVO;

public class AddCommentAction implements Action {

	@Override
	public URLModel execute(HttpServletRequest request)
			throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
		try{ CommentVO vo = CommentVO.builder()
				.postComment(request.getParameter("postComment"))
				.commentStatus(request.getParameter("commentStatus").charAt(0))
				.postId(Integer.parseInt(request.getParameter("postId")))
				.userId(request.getParameter("userId"))
				.build();
			
			new CommentService().createComment(vo);
			
			request.setAttribute("result", new ResponseModel(201, "created"));
		} catch (DatabaseConnectException e){
			throw new RuntimeException(e);
		}
		return new URLModel();
	}

}
