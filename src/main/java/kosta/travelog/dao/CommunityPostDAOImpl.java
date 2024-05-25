package kosta.travelog.dao;

import kosta.travelog.dto.CommunityPostDTO;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.CommunityPostVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommunityPostDAOImpl implements CommunityPostDAO {

    private final Connection conn;

    public CommunityPostDAOImpl(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void addCommunityPost(CommunityPostVO vo) throws DatabaseQueryException {
        String sql = Query.INSERT_COMMUNITY_POST;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vo.getCommunityId());
            ps.setInt(2, vo.getPostId());
            int result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeCommunityPost(int communityPostId) {
        String sql = Query.DELETE_COMMUNITY_POST;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, communityPostId);

            int result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Collection<CommunityPostDTO> communityPostListForGuest(int communityId) throws DatabaseQueryException {
        String sql = Query.COMMUNITY_POST_LIST_FOR_GUEST;
        List<CommunityPostDTO> postsForGuest = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, communityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    postsForGuest.add(new CommunityPostDTO(rs.getInt("post_id"),
                            rs.getDate("post_date").toLocalDate(),
                            rs.getString("post_status").charAt(0),
                            rs.getInt("community_post_id"),
                            rs.getInt("community_id"),
                            rs.getInt("image_id"),
                            rs.getString("images")));
                }
            }
            return postsForGuest;

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public Collection<CommunityPostDTO> communityPostListForMember(int community_id) throws DatabaseQueryException {
        String sql = Query.COMMUNITY_POST_LIST_FOR_MEMBER;
        List<CommunityPostDTO> postsForMember = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, community_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    postsForMember.add(new CommunityPostDTO(rs.getInt("post_id"),
                            rs.getDate("post_date").toLocalDate(),
                            rs.getString("post_status").charAt(0),
                            rs.getInt("community_post_id"),
                            rs.getInt("community_id"),
                            rs.getInt("image_id"),
                            rs.getString("images")));

                }
            }
            return postsForMember;

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }

    }

}
