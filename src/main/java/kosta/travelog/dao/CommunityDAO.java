package kosta.travelog.dao;

import kosta.travelog.vo.CommunityVO;

public interface CommunityDAO {
    /*communityId로 커뮤니티 정보 불러오기*/
    CommunityVO getCommunity(int communityId);

    /*커뮤니티 등록*/
    void addCommunity(CommunityVO vo);

    /*커뮤니티 수정*/
    void setCommunity(CommunityVO vo);

    /*커뮤니티 삭제*/
    void removeCommunity(int communityId);
}
