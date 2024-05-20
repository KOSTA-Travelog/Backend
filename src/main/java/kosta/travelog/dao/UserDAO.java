package kosta.travelog.dao;

import java.util.Collection;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.UserVO;

public interface UserDAO {
    UserVO login(UserVO user) throws DatabaseQueryException;

	Collection<UserVO> searchUser(String nickname);
	
	UserVO loadProfile(String userId) throws DatabaseQueryException;
}
