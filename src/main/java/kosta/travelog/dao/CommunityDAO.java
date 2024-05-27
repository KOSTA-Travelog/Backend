package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommunityVO;

public interface CommunityDAO {
    /*communityId로 커뮤니티 정보 불러오기*/
    CommunityVO getCommunity(int communityId) throws DatabaseQueryException;

    /*커뮤니티 등록*/
    void addCommunity(CommunityVO vo) throws DatabaseQueryException;

    /*커뮤니티 수정*/
    void setCommunity(CommunityVO vo) throws DatabaseQueryException;

    /*커뮤니티 삭제*/
    void removeCommunity(int communityId) throws DatabaseQueryException;
}
