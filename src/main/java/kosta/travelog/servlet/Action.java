package kosta.travelog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface Action {

    URLModel execute(HttpServletRequest request) throws ServletException, IOException;
}
