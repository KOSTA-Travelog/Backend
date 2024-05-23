package kosta.travelog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.CommunityPostVO;

public class CommunityPostDAOImpl implements CommunityPostDAO{

	private final Connection conn;
	public CommunityPostDAOImpl(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public void addCommunityPost(CommunityPostVO vo) throws DatabaseQueryException {
		String sql = Query.INSERT_COMMUNITY_POST;
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, vo.getCommunityId());
			ps.setInt(2, vo.getPostId());
			int result = ps.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeCommunityPost(int communityPostId) {
		String sql = Query.DELETE_COMMUNITY_POST;
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, communityPostId);
			
			int result = ps.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
