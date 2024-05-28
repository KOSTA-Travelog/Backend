package kosta.travelog.servlet.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.CommentService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

public class CommentListAction implements Action {

	@Override
	public URLModel execute(HttpServletRequest request)
			throws ServletException, IOException, DatabaseConnectException, DatabaseQueryException {
		ResponseModel responseModel = null;
		JsonObject json = new JsonObject();
		
		json.addProperty("data", new CommentService().commentList(Integer.parseInt(request.getParameter("commentId"))).toString());
		responseModel = new ResponseModel(200, json, "success");
		
		request.setAttribute("data", responseModel);
		return new URLModel();
	}

}
