package kosta.travelog.repository;

public interface Query {
    String LOGIN = "SELECT user_id, nickname, profile_image,user_status FROM users WHERE email = ? AND password = ?";
    String GET_COMMENT = "SELECT comment_id, post_comment, comment_date, user_id FROM Comments WHERE comment_status=? and post_id=?";
    String GET_COMMENT_COUNT = "SELECT COUNT(comment_id) FROM Comments WHERE post_id = ?";
    String ADD_COMMENT = "INSERT INTO comments (comment_id, post_comment, comment_date, comment_status, post_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_COMMENT = "UPDATE comments SET post_comment=? WHERE comment_id=?";
    String DELETE_COMMENT = "UPDATE comments SET comment_status=? WHERE comment_id=?";
}