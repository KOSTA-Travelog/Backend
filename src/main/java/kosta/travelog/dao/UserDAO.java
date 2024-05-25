package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.UserVO;

import java.util.Collection;

public interface UserDAO {
    Collection<UserVO> searchUser(String nickname) throws DatabaseQueryException;

    UserVO login(UserVO user) throws DatabaseQueryException;

    UserVO getProfile(String userId) throws DatabaseQueryException;

    UserVO addUser(UserVO user);

    UserVO findUserEmail(UserVO user);

    UserVO checkUser(UserVO user);

    UserVO findPassword(UserVO user);

    UserVO removeUser(UserVO user);

    UserVO editUserInfo(UserVO user);

}
