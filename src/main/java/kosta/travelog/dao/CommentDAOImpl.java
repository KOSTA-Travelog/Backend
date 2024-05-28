package kosta.travelog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.CommentVO;

public class CommentDAOImpl implements CommentDAO{

	private final Connection conn;
	
	public CommentDAOImpl (Connection conn){
		this.conn = conn;
	}
	
	@Override
	public void addComment(CommentVO vo) throws DatabaseQueryException {
		String sql = Query.ADD_COMMENT;
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, vo.getPostComment());
			ps.setString(2, String.valueOf(vo.getCommentStatus()));
			ps.setInt(3, vo.getPostId());
			ps.setString(4, vo.getUserId());
			
			int result = ps.executeUpdate();
		} catch (SQLException e) {
			throw new DatabaseQueryException(e.getMessage());
		}
	}

	@Override
	public void updateComment(CommentVO vo) throws DatabaseQueryException {
		String sql = Query.UPDATE_COMMENT;
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, vo.getPostComment());
			ps.setString(2, String.valueOf(vo.getCommentStatus()));
			ps.setString(3, vo.getUserId());
			
			int result = ps.executeUpdate();
		} catch (SQLException e){
			throw new DatabaseQueryException(e.getMessage());
		}
		
	}

	@Override
	public void deleteComment(int commentId) throws DatabaseQueryException {
		String sql = Query.DELETE_COMMENT;
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, commentId);
			
			int result = ps.executeUpdate();
		} catch (SQLException e){
			throw new DatabaseQueryException(e.getMessage());
		}
		
	}

	@Override
	public Collection<CommentVO> getCommentListByPostId(int postId) throws SQLException, DatabaseQueryException {
		String sql = Query.GET_COMMENT;
		List<CommentVO> commentList = new ArrayList<>();
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, postId);
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()){
					commentList.add(new CommentVO(rs.getInt("comment_id"),
												rs.getString("post_comment"),
												rs.getDate("comment_date").toLocalDate(),
												rs.getString("comment_status").charAt(0),
												rs.getInt("post_id"),
												rs.getString("user_id")));
				}
			}
		} catch (SQLException e){
			throw new DatabaseQueryException(e.getMessage());
		}
		
		return commentList;
	}

	@Override
	public int countCommentByPostId(int postId) throws SQLException, DatabaseQueryException {
		String sql = Query.COUNT_COMMENT;
		int result = 0;
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, postId);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()){
					result = rs.getInt(1);
				}
			}
		} catch (SQLException e){
			throw new DatabaseQueryException(e.getMessage());
		}
		return result;
	}
}
