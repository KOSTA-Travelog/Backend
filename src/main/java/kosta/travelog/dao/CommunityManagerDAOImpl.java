package kosta.travelog.dao;

import kosta.travelog.dto.CommunityDTO;
import kosta.travelog.dto.CommunityPostDTO;
import kosta.travelog.dto.CountMemberDTO;
import kosta.travelog.dto.InviteMemberDTO;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommunityManagerDAOImpl implements CommunityManagerDAO {

    public Connection conn;

    public CommunityManagerDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Collection<CommunityDTO> myCommunityList(String userId) throws DatabaseQueryException {
        String sql = Query.MY_COMMUNITY_LIST;
        List<CommunityDTO> myCommunityList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    myCommunityList.add(new CommunityDTO(rs.getInt("community_id"),
                            rs.getString("community_title"),
                            rs.getString("community_description"),
                            rs.getString("community_hashtag"),
                            rs.getDate("community_date").toLocalDate(),
                            rs.getString("community_image"),
                            rs.getString("community_status").charAt(0),
                            rs.getInt("countMember")
                    ));
                }
            }
            return myCommunityList;
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public Collection<CommunityDTO> allCommunityList() throws DatabaseQueryException {
        String sql = Query.ALL_COMMUNITY_LIST;
        List<CommunityDTO> allCommunityList = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                allCommunityList.add(new CommunityDTO(rs.getInt("community_id"),
                        rs.getString("community_title"),
                        rs.getString("community_description"),
                        rs.getString("community_hashtag"),
                        rs.getDate("community_date").toLocalDate(),
                        rs.getString("community_image"),
                        rs.getString("community_status").charAt(0),
                        rs.getInt("countMemmber")
                ));
            }
            return allCommunityList;
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public CountMemberDTO getCountMemberByCommunityId(int communityId) throws DatabaseQueryException {
        String sql = Query.COUNT_COMMUNITY_MEMBER;
        CountMemberDTO result = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, communityId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new CountMemberDTO(rs.getInt("community_id"),
                            rs.getInt("member_count"));
                }
            }
            return result;

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public Collection<CommunityDTO> userCreatedCommunityList(String userId) throws DatabaseQueryException {
        String sql = Query.MY_CREATED_COMMUNITY_LIST;
        List<CommunityDTO> myCreatedCommunityList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    myCreatedCommunityList.add(new CommunityDTO(rs.getInt("community_id"),
                            rs.getString("community_title"),
                            rs.getString("community_description"),
                            rs.getString("community_hashtag"),
                            rs.getDate("community_date").toLocalDate(),
                            rs.getString("community_image"),
                            rs.getString("community_status").charAt(0),
                            rs.getInt("member_count")
                    ));
                }
            }
            return myCreatedCommunityList;

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }

    }

    @Override
    public Collection<CommunityDTO> userJoinedCommunityList(String userId) throws DatabaseQueryException {
        String sql = Query.JOINED_COMMUNITY_LIST;
        List<CommunityDTO> joinedCommunity = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    joinedCommunity.add(new CommunityDTO(rs.getInt("community_id"),
                            rs.getString("community_title"),
                            rs.getString("community_description"),
                            rs.getString("community_hashtag"),
                            rs.getDate("community_date").toLocalDate(),
                            rs.getString("community_image"),
                            rs.getString("community_status").charAt(0),
                            rs.getInt("member_count")
                    ));
                }
            }
            return joinedCommunity;

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }

    }

    @Override
    public Collection<InviteMemberDTO> pendingInvitationList(int communityId) throws DatabaseQueryException {
        String sql = Query.WAITING_MEMBER_LIST;
        List<InviteMemberDTO> waitingMembers = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, communityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    waitingMembers.add(new InviteMemberDTO(rs.getInt("community_id"),
                            rs.getString("community_member_status").charAt(0),
                            rs.getString("profile_image"),
                            rs.getString("nickname"),
                            rs.getString("bio")
                    ));
                }
            }
            return waitingMembers;

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }

    }

    @Override
    public Collection<InviteMemberDTO> currentMemberList(int communityId) throws DatabaseQueryException {
        String sql = Query.CURRENT_MEMBER_LIST;
        List<InviteMemberDTO> currentMembers = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, communityId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    currentMembers.add(new InviteMemberDTO(rs.getInt("community_id"),
                            rs.getString("community_member_status").charAt(0),
                            rs.getString("profile_image"),
                            rs.getString("nickname"),
                            rs.getString("bio")
                    ));
                }
            }
            return currentMembers;

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
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
