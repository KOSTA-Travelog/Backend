package kosta.travelog.servlet.action;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.AccountService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;
import kosta.travelog.vo.UserVO;

public class SearchNicknameAction implements Action {
	@Override
	public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		try {
			AccountService service = new AccountService();
			Collection<UserVO> userList = service.searchUser(nickname);
			if(!(userList.equals(null))){
				request.getSession().setAttribute("users", userList);
				request.setAttribute("data", new ResponseModel(200,"success"));
			}else{
				request.setAttribute("data", new ResponseModel(400,"fail"));
			}
		} catch (DatabaseConnectException e) {
			request.setAttribute("data", new ResponseModel(500, "fail"));
		}
		
		return new URLModel("response.jsp");
	}

}