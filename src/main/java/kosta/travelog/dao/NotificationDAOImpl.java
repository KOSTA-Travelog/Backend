package kosta.travelog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kosta.travelog.dto.NotificationUserDTO;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.NotificationVO;

public class NotificationDAOImpl implements NotificationDAO{
	private final Connection conn;
	
	public NotificationDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void addPendingCommunityMember(NotificationVO vo) {
	}

	@Override
	public void acceptCommunityInvite(String notificationId, String userId) throws DatabaseQueryException {
		String sql = Query.ACCEPT_COMMUNITY_INVITE;
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, notificationId);
			ps.setNString(2, userId);
			
			int result = ps.executeUpdate();
		} catch (SQLException e){
			throw new DatabaseQueryException(e.getMessage());
		}
	}

	@Override
	public void rejectCommunityInvite(String notificationId, String userId) throws DatabaseQueryException {
		String sql = Query.REJECT_COMMUNITY_INVITE;
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, notificationId);
			ps.setString(2, userId);
			
			int result = ps.executeUpdate();
		} catch (SQLException e){
			throw new DatabaseQueryException(e.getMessage());
		}
	}

	@Override
	public Collection<NotificationUserDTO> pendingMemberList(int communityId, char notificationType) throws DatabaseQueryException {
		String sql = Query.PENDING_MEMBER_LIST;
		List<NotificationUserDTO> pendingMemberList = new ArrayList<>();
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1, communityId);
			ps.setString(2, String.valueOf(notificationType));
			
			try(ResultSet rs = ps.executeQuery()){
				pendingMemberList.add(new NotificationUserDTO(rs.getString("nickname"),
															rs.getString("bio"), 
															rs.getString("notification_type").charAt(0), 
															rs.getInt("community_id")
						));
			}
		} catch (SQLException e){
			throw new DatabaseQueryException(e.getMessage());
		}
		return pendingMemberList;
	}

	@Override
	public void removeNotification(String notificationId, String userId) throws DatabaseQueryException {
		String sql = Query.REMOVE_NOTIFICATION;
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, notificationId);
			ps.setString(2, userId);
			
			int result = ps.executeUpdate();
		} catch (SQLException e){
			throw new DatabaseQueryException(e.getMessage());
		}
	}

	@Override
	public Collection<NotificationVO> NotificationList(String userId) throws DatabaseQueryException {
		String sql = Query.NOTIFICATION_LIST;
		List<NotificationVO> notificationList = new ArrayList<>();
		
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, userId);
			try(ResultSet rs = ps.executeQuery()){
				notificationList.add(new NotificationVO(rs.getString("notification_id"), 
													rs.getString("notification_type").charAt(0), 
													rs.getString("notification_read").charAt(0), 
													rs.getString("user_id"), 
													rs.getString("user_id"), 
													rs.getDate("notification_date").toLocalDate(), 
													rs.getInt("community_id"), 
													rs.getString("follow_id"), 
													rs.getString("like_id"), 
													rs.getInt("comment_id"), 
													rs.getInt("comment_reply_id"), 
													rs.getInt("community_member_id")
						));
			}
		} catch (SQLException e){
			throw new DatabaseQueryException(e.getMessage());
		}
		return notificationList;
	}

}
