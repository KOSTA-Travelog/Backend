package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommentVO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface CommentDAO {
    CommentVO getComment(char commentStatus, int postId) throws SQLException, DatabaseQueryException;

    int getCommentCount(int postId) throws SQLException, DatabaseQueryException;

    void addComment(CommentVO vo) throws DatabaseQueryException;

    void updateComment(int commentId, String postComment) throws DatabaseQueryException;

    void deleteComment(int commentId) throws DatabaseQueryException;
}
