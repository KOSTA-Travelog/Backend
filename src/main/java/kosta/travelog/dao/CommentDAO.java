package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommentVO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface CommentDAO {
    CommentVO getComment(char commentStatus, int commentId) throws SQLException, DatabaseQueryException;

    int getCommentCount(int postId) throws SQLException, DatabaseQueryException;

    void addComment(int commentId, String postComment, LocalDate commentDate, char commentStatus, int postId, String userId) throws DatabaseQueryException;

    void updateComment(int commentId, String postComment) throws DatabaseQueryException;

    void deleteComment(int commentId, char commentStatus);
}
