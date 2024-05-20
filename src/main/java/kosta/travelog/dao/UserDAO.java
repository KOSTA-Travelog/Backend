package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.UserVO;

public interface UserDAO {
    UserVO login(UserVO user) throws DatabaseQueryException;
}
