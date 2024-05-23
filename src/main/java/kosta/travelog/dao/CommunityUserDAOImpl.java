package kosta.travelog.dao;

import kosta.travelog.repository.Query;
import kosta.travelog.vo.CommunityUserVO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CommunityUserDAOImpl implements CommunityUserDAO {
    private final Connection conn;

    public CommunityUserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addCommunityCreatorToMember(CommunityUserVO vo) {

    }

    @Override
    public void askAddMember(CommunityUserVO vo) {
        String sql = Query.INSERT_ASK_COMMUNITY_MEMBER;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vo.getCommunityId());
            ps.setString(2, vo.getUserId());
            ps.setString(3, String.valueOf(vo.getCommunityMemberStatus()));

            int result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setMember(int communityMemberId) {
        String sql = Query.UPDATE_COMMUNITY_MEMBER;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, communityMemberId);

            int result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMember(int communityMemberId) {
        String sql = Query.DELETE_COMMUNITY_MEMBER;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, communityMemberId);

            int result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
