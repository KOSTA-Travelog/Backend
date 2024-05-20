package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                //user_id, nickname, profile_image,user_status
                if (rs.next()) {
                    return UserVO.builder()
                            .userId(rs.getString("USER_ID"))
                            .profileImage(rs.getString("PROFILE_IMAGE"))
                            .nickname(rs.getString("NICKNAME"))
                            .userStatus(rs.getString("USER_STATUS"))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("로그인 쿼리 실행에 실패했습니다.\n" + e.getMessage());
        }

        return null;
    }
}
