package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommentVO;

import java.sql.SQLException;
import java.util.Collection;

public interface CommentDAO {

    void addComment(CommentVO vo) throws DatabaseQueryException;

    void updateComment(CommentVO vo) throws DatabaseQueryException;

    void deleteComment(int commentId) throws DatabaseQueryException;

    Collection<CommentVO> getCommentListByPostId(int postId) throws SQLException, DatabaseQueryException;

    int countCommentByPostId(int postId) throws SQLException, DatabaseQueryException;

}
