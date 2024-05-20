package kosta.travelog.repository;

public interface Query {
    String LOGIN = "SELECT user_id, nickname, profile_image,user_status FROM users WHERE email = ? AND password = ?";
    String SEARCHUSER = "SELECT user_id,nickname, bio, profile_image, user_status FROM users WHERE nickname LIKE ?;";
    String LOADPROFILE = "SELECT user_id,nickname, bio, profile_image, user_status FROM users WHERE user_id = ?;";
}