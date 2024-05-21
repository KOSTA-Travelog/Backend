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
					return UserVO.builder().userId(rs.getString("USER_ID")).profileImage(rs.getString("PROFILE_IMAGE"))
							.nickname(rs.getString("NICKNAME")).userStatus(rs.getString("USER_STATUS")).build();
				}
			}
		} catch (SQLException e) {
			throw new DatabaseQueryException("로그인 쿼리 실행에 실패했습니다.\n" + e.getMessage());
		}

		return null;
	}

	@Override
	public Collection<UserVO> searchUser(String nickname) {
		String sql = Query.SEARCHUSER;
		Collection<UserVO> result = new ArrayList<UserVO>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, nickname);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					result.add(new UserVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public UserVO loadProfile(String userId) throws DatabaseQueryException {
		String sql = Query.LOADPROFILE;
		UserVO result = null;
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new UserVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5));

				}
			}
		} catch (SQLException e) {
			throw new DatabaseQueryException("프로필 불러오기 쿼리 실행에 실패했습니다.\n" + e.getMessage());
		}
		return result;
	}
}
