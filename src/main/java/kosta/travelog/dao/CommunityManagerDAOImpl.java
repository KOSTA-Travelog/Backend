package kosta.travelog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import kosta.travelog.dto.CommunityDTO;
import kosta.travelog.dto.CountMemberDTO;
import kosta.travelog.dto.InviteMemberDTO;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;

public class CommunityManagerDAOImpl implements CommunityManagerDAO{

	public Connection conn;
	
	public CommunityManagerDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Collection<CommunityDTO> myCommunityList() throws DatabaseQueryException {
		String sql = Query.MY_COMMUNITY_LIST;
		try(PreparedStatement ps = conn.prepareStatement(sql)){
			
		} catch(SQLException e) {
			throw new DatabaseQueryException(e.getMessage());
		}
		return null;
	}

	@Override
	public Collection<CommunityDTO> allCommunityList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CountMemberDTO> countMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CountMemberDTO> getCountMemberByCommunityId(int communityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CommunityDTO> userCreatedCommunityList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<CommunityDTO> userJoinedCommunityList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<InviteMemberDTO> pendingInvitationList(String communityId, char communityMemberStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<InviteMemberDTO> currentMemberList(String communityId, char communityMemberStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<InviteMemberDTO> communityPostListForGuest(char communityMemberStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<InviteMemberDTO> communityPostListForMember(char communityMemberStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
