package kosta.travelog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kosta.travelog.dao.CommentDAOImpl;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentService {
	
	private final DataSource dataSourse;
	
	public CommentService() throws DatabaseConnectException{
		try {
			Context context = new InitialContext();
			dataSourse = (DataSource) context.lookup("java:comp/env/jdbc/sun");
		} catch (NamingException e) {
			throw new DatabaseConnectException("database를 받아오지 못했습니다.\n" + 
					String.format("%s %s", this.getClass(), e.getMessage()));
		}
		
	}
	
	public void createComment(CommentVO vo){
		try(Connection conn = dataSourse.getConnection()){
			new CommentDAOImpl(conn).addComment(vo);
		} catch (SQLException e){
			throw new RuntimeException(e);
		} catch (DatabaseQueryException e){
			throw new RuntimeException(e);
		}
	}
	
	public void editComment(CommentVO vo){
		try(Connection conn = dataSourse.getConnection()){
			new CommentDAOImpl(conn).updateComment(vo);
		} catch (SQLException e){
			throw new RuntimeException(e);
		} catch	(DatabaseQueryException e){
			throw new RuntimeException(e);
		}
	}
	
	public void deleteComment(int commentId){
		try(Connection conn = dataSourse.getConnection()){
			new CommentDAOImpl(conn).deleteComment(commentId); 
		} catch (SQLException e){
			throw new RuntimeException(e);
		} catch (DatabaseQueryException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<CommentVO> commentList(int postId){
		List<CommentVO> vo = new ArrayList<>();
		try(Connection conn = dataSourse.getConnection()){
			vo = (List<CommentVO>) new CommentDAOImpl(conn).getCommentListByPostId(postId);
		} catch (SQLException e){
			throw new RuntimeException(e);
		} catch (DatabaseQueryException e){
			throw new RuntimeException(e);
		}
		return vo;
	}
	
	public int countComment(int postId){
		int count = 0;
		try(Connection conn = dataSourse.getConnection()){
			count = (int) new CommentDAOImpl(conn).countCommentByPostId(postId);
		} catch (SQLException e){
			throw new RuntimeException(e);
		} catch (DatabaseQueryException e){
			throw new RuntimeException(e);
		}
		return count;
	}
}

