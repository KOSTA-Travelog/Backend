package kosta.travelog.exception;

public class DatabaseQueryException extends Exception{
    public DatabaseQueryException() {
        this("쿼리 실행 도중 오류가 발생했습니다.");
    }
    public DatabaseQueryException(String message) {
        super(message);
    }
}
