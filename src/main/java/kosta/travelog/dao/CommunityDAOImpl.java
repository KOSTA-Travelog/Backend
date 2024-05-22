package kosta.travelog.dao;

import kosta.travelog.dto.CommunityDTO;
import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.CommunityVO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Slf4j
public class CommunityDAOImpl implements CommunityDAO {

    private final Connection conn;

    public CommunityDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public CommunityDTO getCommunity(int communityId) throws DatabaseQueryException {
        String sql = Query.COMMUNITY;
        CommunityDTO result = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, communityId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = CommunityDTO.builder()
                            .communityId(rs.getInt(1))
                            .communityTitle(rs.getString(2))
                            .communityDescription(rs.getString(3))
                            .communityHashtag(rs.getString(4))
                            .communityDate(rs.getDate(5).toLocalDate())
                            .communityImage(rs.getString(6))
                            .communityStatus(rs.getString(7).charAt(0))
                            .countMember(rs.getInt(8))
                            .build();
                } else {
                    log.warn("No data found for communityId: " + communityId);
                    throw new BadRequestException("No data found for communityId: " + communityId);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
        return result;
    }

    @Override
    public void addCommunity(CommunityVO vo) throws DatabaseQueryException {
        String sql = Query.INSERT_COMMUNITY;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vo.getCommunityTitle());
            ps.setString(2, vo.getCommunityDescription());
            ps.setString(3, vo.getCommunityHashtag());
            ps.setString(4, vo.getCommunityImage());
            ps.setInt(5, vo.getCommunityStatus());
            ps.setString(6, vo.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public void setCommunity(CommunityVO vo) throws DatabaseQueryException {
        String sql = Query.UPDATE_COMMUNITY;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vo.getCommunityTitle());
            ps.setString(2, vo.getCommunityDescription());
            ps.setString(3, vo.getCommunityHashtag());
            ps.setString(4, vo.getCommunityImage());
            ps.setString(5, String.valueOf(vo.getCommunityStatus()));
            ps.setInt(6, vo.getCommunityId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public void removeCommunity(int communityId) throws DatabaseQueryException {
        String sql = Query.DELETE_COMMUNITY;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, communityId);
            
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

}
