package kosta.travelog.service;

import kosta.travelog.dao.UserDAOImpl;
import kosta.travelog.dto.LoginDTO;
import kosta.travelog.dto.UserProfileDTO;
import kosta.travelog.exception.BadRequestException;
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
import java.util.Collections;
import java.util.List;

@Slf4j
public class AccountService extends CommonService {
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
        UserVO vo = user;
        if (user == null) {
            return null;
        }
        try {
            vo = new UserDAOImpl(dataSource.getConnection()).login(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return LoginDTO.builder().userId(vo.getUserId()).profileImage(vo.getProfileImage()).nickname(vo.getNickname()).userStatus(vo.getUserStatus()).build();
    }

    /* */
    public boolean register(UserVO user, String vaildPassword) throws DatabaseQueryException, DatabaseConnectException, BadRequestException {
        if (user == null) {
            return false;
        }
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getPhoneNumber() == null || user.getNickname() == null) {
            throw new BadRequestException("Required inputs are missing.");
        }
        if (user.getEmail().isEmpty() || !user.getPassword().equals(vaildPassword)) {
            return false;
        }
        try {
            new UserDAOImpl(dataSource.getConnection()).addUser(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return true;
    }

    public List<UserProfileDTO> searchUser(String nickname) throws DatabaseQueryException, DatabaseConnectException {
        if (nickname == null) {
            return Collections.emptyList();
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
            connectException(e);
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
            connectException(e);
        }
        return dto;
    }


    public String findAccount(UserVO user) throws DatabaseQueryException, DatabaseConnectException {
        try {
            return new UserDAOImpl(dataSource.getConnection()).findUserEmail(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return "";
    }

    public String verifyUser(UserVO user) throws DatabaseConnectException, DatabaseQueryException {
        try {
            return new UserDAOImpl(dataSource.getConnection()).checkUser(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return "";
    }

    public boolean editPassword(UserVO user) throws DatabaseConnectException, DatabaseQueryException {
        try {
            new UserDAOImpl(dataSource.getConnection()).setPassword(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return true;
    }

    public boolean cancelAccount(String userId) throws DatabaseConnectException, DatabaseQueryException {
        try {
            new UserDAOImpl(dataSource.getConnection()).removeUser(userId);
        } catch (SQLException e) {
            connectException(e);
        }
        return true;
    }

    public boolean editUserInfo(UserVO user) throws DatabaseConnectException, DatabaseQueryException {
        try {
            new UserDAOImpl(dataSource.getConnection()).setUserInfo(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return true;
    }

}
