package kosta.travelog.service;

import kosta.travelog.dao.UserDAOImpl;
import kosta.travelog.dto.LoginDTO;
import kosta.travelog.dto.UserInfoDTO;
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
import java.sql.Connection;
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
        try (Connection conn = dataSource.getConnection()) {
            vo = new UserDAOImpl(conn).login(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return LoginDTO.builder().userId(vo.getUserId()).profileImage(vo.getProfileImage()).nickname(vo.getNickname()).userStatus(vo.getUserStatus()).build();
    }

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

        try (Connection conn = dataSource.getConnection()) {
            new UserDAOImpl(conn).addUser(user);
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
        try (Connection conn = dataSource.getConnection()) {
            dto = (ArrayList<UserProfileDTO>) new UserDAOImpl(conn).searchUser(nickname);
//            for (UserProfileDTO user : dto) {
//                dto.add(UserProfileDTO.builder()
//                        .userId(user.getUserId())
//                        .nickname(user.getNickname())
//                        .bio(user.getBio())
//                        .profileImage(user.getProfileImage())
//                        .userStatus(user.getUserStatus())
//                        .communityMemberStatus(user.getCommunityMemberStatus())
//                        .build());
//            }
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
        try (Connection conn = dataSource.getConnection()) {
            UserVO user = new UserDAOImpl(conn).getProfile(userId);
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
        try (Connection conn = dataSource.getConnection()) {
            return new UserDAOImpl(conn).findUserEmail(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return "";
    }

    public String verifyUser(UserVO user) throws DatabaseConnectException, DatabaseQueryException {
        try (Connection conn = dataSource.getConnection()) {
            return new UserDAOImpl(conn).checkUser(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return "";
    }

    public boolean editPassword(UserVO user) throws DatabaseConnectException, DatabaseQueryException {
        try (Connection conn = dataSource.getConnection()) {
            new UserDAOImpl(conn).setPassword(user);
        } catch (SQLException e) {
            connectException(e);
        }
        return true;
    }

    public boolean cancelAccount(String userId) throws DatabaseConnectException, DatabaseQueryException {
        try (Connection conn = dataSource.getConnection()) {
            new UserDAOImpl(conn).removeUser(userId);
        } catch (SQLException e) {
            connectException(e);
        }
        return true;
    }

    public boolean editUserInfo(UserVO user, String vaildPassword) throws DatabaseConnectException, DatabaseQueryException, BadRequestException {
        log.info(user.getPassword());
        log.info(vaildPassword);

        if (!user.getPassword().equals(vaildPassword)) {
            return false;
        }

        if (user.getName() == null || user.getPassword() == null || user.getPhoneNumber() == null || user.getNickname() == null) {
            throw new BadRequestException("Required inputs are missing.");
        }

        try (Connection conn = dataSource.getConnection()) {

            new UserDAOImpl(conn).setUserInfo(user);

        } catch (SQLException e) {
            connectException(e);
        }
        return true;
    }

    public String getUserId(String nickname) throws DatabaseConnectException {
        try (Connection conn = dataSource.getConnection()) {
            return new UserDAOImpl(conn).getUserIdByNickname(nickname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
    }

    public UserInfoDTO getLoginUserInfo(String userId) {

        try (Connection conn = dataSource.getConnection()) {
            UserVO vo = new UserDAOImpl(conn).getCurrentUserInfo(userId);

            return UserInfoDTO.builder().name(vo.getName())
                    .email(vo.getEmail())
                    .phoneNumber(vo.getPhoneNumber())
                    .nickname(vo.getNickname())
                    .bio(vo.getBio())
                    .profileImage(vo.getProfileImage()).build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
    }

}
