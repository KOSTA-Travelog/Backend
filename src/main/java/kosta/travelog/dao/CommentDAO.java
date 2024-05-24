package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommentVO;

import java.sql.SQLException;

public interface CommentDAO {

    void addComment(CommentVO vo) throws DatabaseQueryException;

    void updateComment(CommentVO vo) throws DatabaseQueryException;

    void deleteComment(int commentId) throws DatabaseQueryException;

    CommentVO getCommentListByPostId(int postId) throws SQLException, DatabaseQueryException;

    int countCommentByPostId(int postId) throws SQLException, DatabaseQueryException;

}
