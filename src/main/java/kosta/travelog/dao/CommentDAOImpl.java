package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.CommentVO;

import java.sql.*;
import java.time.LocalDate;

public class CommentDAOImpl implements CommentDAO {

    private final Connection conn;

    public CommentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public CommentVO getComment(char commentStatus, int postId) throws DatabaseQueryException {
        String sql = Query.GET_COMMENT;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(commentStatus));
            ps.setInt(2, postId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return CommentVO.builder()
                            .commentId(rs.getInt("COMMENT_ID"))
                            .postComment(rs.getString("POST_COMMENT"))
                            .commentDate(rs.getDate("COMMENT_DATE").toLocalDate())
                            .commentStatus(rs.getString("COMMENT_STATUS").charAt(0))
                            .postId(rs.getInt("POST_ID"))
                            .userId(rs.getString("USER_ID"))
                            .build();
                }
            }
            return null;
            
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public int getCommentCount(int postId) throws DatabaseQueryException {
        String sql = Query.GET_COMMENT_COUNT;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, postId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }

        return 0;
    }

    @Override
    public void addComment(CommentVO vo) throws DatabaseQueryException {
        String sql = Query.ADD_COMMENT;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vo.getCommentId());
            ps.setString(2, vo.getPostComment());
            ps.setDate(3, Date.valueOf(vo.getCommentDate()));
            ps.setString(4, String.valueOf(vo.getCommentStatus()));
            ps.setInt(5, vo.getPostId());
            ps.setString(6, vo.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public void updateComment(int commentId, String postComment) throws DatabaseQueryException {
        String sql = Query.UPDATE_COMMENT;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(2, commentId);
            ps.setString(1, postComment);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public void deleteComment(int commentId) throws DatabaseQueryException {
    	String sql = Query.DELETE_COMMENT;
    	try (PreparedStatement ps = conn.prepareStatement(sql)){
    		ps.setInt(1, commentId);
    		ps.executeUpdate();
    	} catch (SQLException e) {
    		throw new DatabaseQueryException(e.getMessage());
    	}
    }
}
