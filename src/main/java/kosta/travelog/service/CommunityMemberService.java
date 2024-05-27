package kosta.travelog.service;

import kosta.travelog.dao.CommunityUserDAOImpl;
import kosta.travelog.dao.NotificationDAOImpl;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommunityUserVO;
import kosta.travelog.vo.NotificationVO;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class CommunityMemberService {

    private final DataSource dataSource;

    public CommunityMemberService() throws DatabaseConnectException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sun");
        } catch (NamingException e) {
            throw new DatabaseConnectException("dataSource를 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
    }

    public boolean addUserToPendingList(CommunityUserVO user, NotificationVO notification) throws SQLException {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            new CommunityUserDAOImpl(conn).addPendingMember(user);
            new NotificationDAOImpl(conn).addPendingCommunityMember(notification);

            conn.commit();
        } catch (SQLException | DatabaseQueryException e) {
            log.error(e.getMessage());
            conn.rollback();
            return false;
        } finally {
            conn.close();
        }
        return true;
    }

    public boolean enrollCommunityMember(CommunityUserVO vo) {
        try (Connection conn = dataSource.getConnection()) {
            new CommunityUserDAOImpl(conn).addCommunityCreatorToMember(vo);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean leaveCommunity(int communityMemberId) {
        try (Connection conn = dataSource.getConnection()) {
            new CommunityUserDAOImpl(conn).removeMember(communityMemberId);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean editPendingMemberToMember(int communityMemberId, String notificationId) throws SQLException {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            new CommunityUserDAOImpl(conn).updateMemberToCommunity(communityMemberId);
            new NotificationDAOImpl(conn).acceptCommunityInvite(notificationId);

            conn.commit();
        } catch (SQLException e) {
            log.error(e.getMessage());
            conn.rollback();
            return false;
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            conn.rollback();
            return false;
        } finally {
            conn.close();
        }
        return true;
    }


}
