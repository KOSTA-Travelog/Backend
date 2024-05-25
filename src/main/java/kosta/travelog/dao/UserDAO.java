package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.UserVO;

import java.util.Collection;

public interface UserDAO {

    Collection<UserVO> searchUser(String nickname) throws DatabaseQueryException;

    UserVO login(UserVO user) throws DatabaseQueryException;

    UserVO getProfile(String userId) throws DatabaseQueryException;

    void addUser(UserVO user) throws DatabaseQueryException;

    String findUserEmail(UserVO user) throws DatabaseQueryException;

    String checkUser(UserVO user) throws DatabaseQueryException;

    void setPassword(UserVO user) throws DatabaseQueryException;

    void removeUser(String userId) throws DatabaseQueryException;

    void setUserInfo(UserVO user) throws DatabaseQueryException;

}
