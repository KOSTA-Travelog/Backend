package kosta.travelog.servlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;

import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.service.AccountService;
import kosta.travelog.servlet.Action;
import kosta.travelog.servlet.ResponseModel;
import kosta.travelog.servlet.URLModel;

public class SearchNicknameAction implements Action {
	@Override
	public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		ResponseModel responseModel = null;
		try {
			JsonObject json = new JsonObject();
			json.addProperty("data", new AccountService().searchUser(nickname).toString());
			responseModel = new ResponseModel(200, json, "success");
		} catch (DatabaseConnectException e) {
            responseModel = new ResponseModel(500, "Server Error");
        } catch (DatabaseQueryException e) {
        	throw new RuntimeException(e);
        } finally {
            request.setAttribute("data", responseModel);
        }
		
		return new URLModel("response.jsp");
	}

}