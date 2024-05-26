package kosta.travelog.service;

import kosta.travelog.dao.UserDAOImpl;
import kosta.travelog.dto.LoginDTO;
import kosta.travelog.dto.UserProfileDTO;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    public LoginDTO login(UserVO user) throws DatabaseConnectException, DatabaseQueryException {
        if (user == null) {
            return null;
        }
        try {
            UserVO vo = new UserDAOImpl(dataSource.getConnection()).login(user);
            return LoginDTO.builder().userId(vo.getUserId())
                    .profileImage(vo.getProfileImage())
                    .nickname(vo.getNickname())
                    .userStatus(vo.getUserStatus()).build();
        } catch (SQLException e) {
            throw new DatabaseConnectException("dataSource에서 connection을 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
    }

    public List<UserProfileDTO> searchUser(String nickname) throws DatabaseQueryException {
        if (nickname == null) {
            return null;
        }
        List<UserProfileDTO> dto = new ArrayList<>();
        try {
            List<UserVO> vo = (ArrayList<UserVO>) new UserDAOImpl(dataSource.getConnection()).searchUser(nickname);

            for (UserVO user : vo) {
                dto.add(UserProfileDTO.builder()
                        .userId(user.getUserId())
                        .nickname(user.getNickname())
                        .bio(user.getBio())
                        .profileImage(user.getProfileImage())
                        .userStatus(user.getUserStatus()).build());
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("dataSource에서 connection을 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
        return dto;
    }

    public UserProfileDTO getProfile(String userId) throws DatabaseQueryException, DatabaseConnectException {

        if (userId == null) {
            return null;
        }
        UserProfileDTO dto = null;
        try {
            UserVO user = new UserDAOImpl(dataSource.getConnection()).getProfile(userId);
            dto = UserProfileDTO.builder()
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .bio(user.getBio())
                    .profileImage(user.getProfileImage())
                    .userStatus(user.getUserStatus()).build();
        } catch (SQLException e) {
            throw new DatabaseConnectException("dataSource에서 connection을 받아오지 못했습니다.\n" +
                    String.format("%s %S", this.getClass(), e.getMessage())
            );
        }
        return dto;
    }

    /* */
    public boolean register(UserVO user) throws SQLException, DatabaseQueryException {
        try {
            new UserDAOImpl(dataSource.getConnection()).addUser(user);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public String findAccount(UserVO user) {
        try {
            return new UserDAOImpl(dataSource.getConnection()).findUserEmail(user);
        } catch (SQLException e) {
            log.error("SQL Exception: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            log.error("Database Query Exception: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String verifyUser(UserVO user) {
        try {
            return new UserDAOImpl(dataSource.getConnection()).checkUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean editPassword(UserVO user) {
        try {
            new UserDAOImpl(dataSource.getConnection()).setPassword(user);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean cancelAccount(String userId) {
        try {
            new UserDAOImpl(dataSource.getConnection()).removeUser(userId);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean editUserInfo(UserVO user) {
        try {
            new UserDAOImpl(dataSource.getConnection()).setUserInfo(user);
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
