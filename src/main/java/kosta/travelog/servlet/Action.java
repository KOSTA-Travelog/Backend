package kosta.travelog.servlet;


import kosta.travelog.exception.DatabaseConnectException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Action {
    URLModel execute(HttpServletRequest request) throws ServletException, IOException, DatabaseConnectException;
}
