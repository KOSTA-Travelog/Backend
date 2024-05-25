package kosta.travelog.dao;

import kosta.travelog.repository.Query;
import kosta.travelog.vo.CommunityUserVO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Slf4j
public class CommunityUserDAOImpl implements CommunityUserDAO {
    private final Connection conn;

    public CommunityUserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addCommunityCreatorToMember(CommunityUserVO vo) {
        String sql = Query.INSERT_CREATOR_TO_COMMUNITY_USERS;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vo.getCommunityId());
            ps.setString(2, vo.getUserId());

            int result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPendingMember(CommunityUserVO vo) {
        String sql = Query.INSERT_COMMUNITY_PENDING_MEMBER;
        log.info("dao");
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vo.getCommunityId());
            ps.setString(2, vo.getUserId());

            int result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMemberToCommunity(int communityMemberId) {
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
