package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class UserDAOImpl implements UserDAO {

    private final Connection conn;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public UserVO login(UserVO user) throws DatabaseQueryException {
        String sql = Query.LOGIN;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            try (ResultSet rs = ps.executeQuery()) {
                // user_id, nickname, profile_image,user_status
                if (rs.next()) {
                    return UserVO.builder().userId(rs.getString("USER_ID"))
                            .profileImage(rs.getString("PROFILE_IMAGE"))
                            .nickname(rs.getString("NICKNAME"))
                            .userStatus(rs.getString("USER_STATUS").charAt(0)).build();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("로그인 쿼리 실행에 실패했습니다.\n" + e.getMessage());
        }

        return null;
    }

    @Override
    public Collection<UserVO> searchUser(String nickname) throws DatabaseQueryException {
        String sql = Query.SEARCH_USER;
        Collection<UserVO> result = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, '%' + nickname + '%');
            try (ResultSet rs = ps.executeQuery()) {
                //                user_id, nickname, bio, profile_image, user_status
                while (rs.next()) {
                    result.add(UserVO.builder()
                            .userId(rs.getString("user_id"))
                            .nickname(rs.getString("nickname"))
                            .bio(rs.getString("bio"))
                            .profileImage(rs.getString("profile_image"))
                            .userStatus(rs.getString("user_status").charAt(0)).build());
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("프로필 검색 쿼리 실행에 실패했습니다. \n" + e.getMessage());
        }

        return result;
    }

    @Override
    public UserVO getProfile(String userId) throws DatabaseQueryException {
        String sql = Query.GET_PROFILE;
        UserVO result = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = UserVO.builder()
                            .userId(rs.getString("user_id"))
                            .nickname(rs.getString("nickname"))
                            .bio(rs.getString("bio"))
                            .profileImage(rs.getString("profile_image"))
                            .userStatus(rs.getString("user_status").charAt(0)).build();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("프로필 불러오기 쿼리 실행에 실패했습니다.\n" + e.getMessage());
        }
        return result;
    }

    /**/
    @Override
    public void addUser(UserVO user) throws DatabaseQueryException {
        String sql = Query.SIGN_UP;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getNickname());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException("회원가입 쿼리 실행에 실패했습니다.\n" + e.getMessage());
        }
    }


    @Override
    public String findUserEmail(UserVO user) throws DatabaseQueryException {
        String sql = Query.FIND_EMAIL;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPhoneNumber());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("email");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
        return null;
    }

    @Override
    public String checkUser(UserVO user) throws DatabaseQueryException {
        String sql = Query.CHECK_USER;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("user_id");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
        return null;
    }

    @Override
    public void setPassword(UserVO user) throws DatabaseQueryException {
        String sql = Query.UPDATE_PASSWORD;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public void removeUser(String userId) throws DatabaseQueryException {
        String sql = Query.WITHDRAWAL;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }

    @Override
    public void setUserInfo(UserVO user) throws DatabaseQueryException {
        String sql = Query.UPDATE_USER_INFORMATION;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getNickname());
            ps.setString(3, user.getProfileImage());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getBio());
            ps.setString(7, user.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException(e.getMessage());
        }
    }
}
