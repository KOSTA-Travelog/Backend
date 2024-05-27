package kosta.travelog.servlet.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.URLModel;

public class DeleteCommentAction implements Action {

	@Override
	public URLModel execute(HttpServletRequest request)
			throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
		// TODO Auto-generated method stub
		return null;
	}

}
