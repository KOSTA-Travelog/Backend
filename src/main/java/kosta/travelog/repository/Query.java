package kosta.travelog.repository;

public interface Query {
    String LOGIN = "SELECT user_id, nickname, profile_image,user_status FROM users WHERE email = ? AND password = ?";
}