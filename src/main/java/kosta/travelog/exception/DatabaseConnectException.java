package kosta.travelog.exception;

public class DatabaseConnectException extends Exception{
    public DatabaseConnectException() {
        this("데이터 베이스 연결 중 오류가 발생했습니다.");
    }
    public DatabaseConnectException(String message) {
        super(message);
    }
}
