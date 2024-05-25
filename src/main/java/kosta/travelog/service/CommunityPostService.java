package kosta.travelog.service;

import kosta.travelog.dao.CommunityPostDAOImpl;
import kosta.travelog.dto.CommunityPostDTO;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommunityPostVO;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommunityPostService {

    private final DataSource dataSource;

    public CommunityPostService() throws DatabaseConnectException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sun");
        } catch (NamingException e) {
            throw new DatabaseConnectException("dataSource를 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
    }

    public boolean createCommunityPost(CommunityPostVO vo) {
        try (Connection conn = dataSource.getConnection()) {
            new CommunityPostDAOImpl(conn).addCommunityPost(vo);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;

    }

    public boolean deleteCommunityPost(int communityPostId) {
        try (Connection conn = dataSource.getConnection()) {
            new CommunityPostDAOImpl(conn).removeCommunityPost(communityPostId);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }


    /* 커뮤니티 멤버 아닌 경우 커뮤니티 별 게시글 목록*/
    public List<CommunityPostDTO> getCommunityPostListForGuest(int communityId) {
        List<CommunityPostDTO> dto;
        try (Connection conn = dataSource.getConnection()) {
            dto = (ArrayList<CommunityPostDTO>) new CommunityPostDAOImpl(conn).communityPostListForGuest(communityId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    /*커뮤니티 멤버인 경우 커뮤니티별 게시글 목록 */
    public List<CommunityPostDTO> getCommunityPostListForMember(int communityId) {
        List<CommunityPostDTO> dto;
        try (Connection conn = dataSource.getConnection()) {
            dto = (ArrayList<CommunityPostDTO>) new CommunityPostDAOImpl(conn).communityPostListForMember(communityId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }


}
