package kosta.travelog.service;

import kosta.travelog.exception.DatabaseConnectException;

public abstract class CommonService {

    void connectException(Exception e) throws DatabaseConnectException {
        throw new DatabaseConnectException("dataSource를 받아오지 못했습니다.\n" +
                String.format("%s %s", this.getClass(), e.getMessage()));
    }
}
