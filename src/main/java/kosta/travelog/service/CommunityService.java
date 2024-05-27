package kosta.travelog.service;

import kosta.travelog.dao.CommunityDAOImpl;
import kosta.travelog.dao.CommunityManagerDAOImpl;
import kosta.travelog.dto.CommunityDTO;
import kosta.travelog.dto.InviteMemberDTO;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommunityVO;
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
public class CommunityService {
    private final DataSource dataSource;

    public CommunityService() throws DatabaseConnectException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sun");
        } catch (NamingException e) {
            throw new DatabaseConnectException("dataSource를 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
    }

    public CommunityDTO Community(int communityId) {
        CommunityDTO dto;
        try (Connection conn = dataSource.getConnection()) {
            dto = new CommunityDAOImpl(conn).getCommunity(communityId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    public boolean createCommunity(CommunityVO community) {
        try (Connection conn = dataSource.getConnection()) {
            new CommunityDAOImpl(conn).addCommunity(community);
//            new CommunityUserDAOImpl(conn).addCommunityCreatorToMember(communityUser);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean editCommunity(CommunityVO vo) {
        try (Connection conn = dataSource.getConnection()) {
            new CommunityDAOImpl(conn).setCommunity(vo);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteCommunity(int communityId) {
        try (Connection conn = dataSource.getConnection()) {
            new CommunityDAOImpl(conn).removeCommunity(communityId);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /* CommunityManagerDAO */
    /* 커뮤니티 목록 불러오기(myCommunities) community_member_status = 1인 경우만*/
    public List<CommunityDTO> getMyCommunityList(String userId) {
        List<CommunityDTO> dto = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            dto = (ArrayList<CommunityDTO>) new CommunityManagerDAOImpl(conn).myCommunityList(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    /* 커뮤니티 목록 불러오기(AllCommunities)*/
    public List<CommunityDTO> getAllCommunityList() {
        List<CommunityDTO> dto = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            dto = (ArrayList<CommunityDTO>) new CommunityManagerDAOImpl(conn).allCommunityList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    /* 내가 만든 커뮤니티 목록*/
    public List<CommunityDTO> getMyCreatedCommunityList(String userId) {
        List<CommunityDTO> dto = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            dto = (ArrayList<CommunityDTO>) new CommunityManagerDAOImpl(conn).userCreatedCommunityList(userId);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    /* 가입한 커뮤니티 목록*/
    public List<CommunityDTO> getJoinedCommunityList(String userId) {
        List<CommunityDTO> dto = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            dto = (ArrayList<CommunityDTO>) new CommunityManagerDAOImpl(conn).userJoinedCommunityList(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    public List<InviteMemberDTO> getPendingInvitationList(int communityId) {
        List<InviteMemberDTO> dto = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            dto = (ArrayList<InviteMemberDTO>) new CommunityManagerDAOImpl(conn).pendingInvitationList(communityId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    public List<InviteMemberDTO> getCurrenetMemberList(int communityId) {
        List<InviteMemberDTO> dto = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            dto = (ArrayList<InviteMemberDTO>) new CommunityManagerDAOImpl(conn).currentMemberList(communityId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    public boolean isCommunityMember(int communityId, String userId) {
        try (Connection conn = dataSource.getConnection()) {
            String nickname = new CommunityManagerDAOImpl(conn).getCommunityUserNickname(communityId, userId);
            if (nickname != null) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
