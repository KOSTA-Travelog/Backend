package kosta.travelog.service;

import kosta.travelog.dao.UserDAOImpl;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author gyeoul
 */
@Slf4j
public class AccountService {
    private final DataSource dataSource;

    public AccountService() throws DatabaseConnectException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sun");
        } catch (NamingException e) {
            throw new DatabaseConnectException("dataSource를 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
    }

    public void register() {

    }

    public UserVO login(UserVO user) throws DatabaseConnectException, DatabaseQueryException {
        if (user == null) {
            return null;
        }
        try {
            return new UserDAOImpl(dataSource.getConnection()).login(user);
        } catch (SQLException e) {
            throw new DatabaseConnectException("dataSource에서 connection을 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
    }

    public Collection<UserVO> searchUser(String nickname) throws DatabaseQueryException {
        if (nickname == null) {
            return null;
        }
        try {
            return new UserDAOImpl(dataSource.getConnection()).searchUser(nickname);
        } catch (SQLException e) {
            throw new DatabaseQueryException("dataSource에서 connection을 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }

    }

    public UserVO getProfile(String userId) throws DatabaseQueryException, DatabaseConnectException {
 
        if (userId == null) {
            return null;
        }
        try {
            return new UserDAOImpl(dataSource.getConnection()).getProfile(userId);
        } catch (SQLException e) {
            throw new DatabaseConnectException("dataSource에서 connection을 받아오지 못했습니다.\n" +
                    String.format("%s %S", this.getClass(), e.getMessage())
            );
        }
    }

}
