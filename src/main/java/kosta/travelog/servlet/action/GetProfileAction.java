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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetProfileAction implements Action {

	@Override
	public URLModel execute(HttpServletRequest request) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		ResponseModel responseModel = null;
		JsonObject json = new JsonObject();
		try {
			json.addProperty("data", new AccountService().getProfile(userId).toString());
			responseModel = new ResponseModel(200, json, "success");
		} catch (DatabaseQueryException e) {
			log.error(e.getMessage());
			responseModel = new ResponseModel(500, "Server Error");
		} catch (DatabaseConnectException e) {
			throw new RuntimeException(e);
		} finally {
			request.setAttribute("data", responseModel);
		}
		
		return new URLModel();
	}

}
