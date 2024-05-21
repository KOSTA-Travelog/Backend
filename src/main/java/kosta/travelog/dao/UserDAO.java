package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.UserVO;

import java.util.Collection;

public interface UserDAO {
    UserVO login(UserVO user) throws DatabaseQueryException;

	Collection<UserVO> searchUser(String nickname);

	UserVO loadProfile(String userId) throws DatabaseQueryException;
}
