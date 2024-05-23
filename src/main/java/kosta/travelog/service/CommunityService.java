package kosta.travelog.service;

import kosta.travelog.dao.CommunityDAOImpl;
import kosta.travelog.dto.CommunityDTO;
import kosta.travelog.exception.BadRequestException;
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
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    public boolean createCommunity(CommunityVO vo) {
        try (Connection conn = dataSource.getConnection()) {
            new CommunityDAOImpl(conn).addCommunity(vo);

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

}
