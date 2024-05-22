package kosta.travelog.dao;

import kosta.travelog.vo.CommunityUserVO;

public interface CommunityUserDAO {
    /* 커뮤니티 멤버 추가(가입 대기)*/
    void askAddMember(CommunityUserVO vo);

    /*커뮤니티 멤버 추가(수락 완료)*/
    void setMember();

    /* 커뮤니티 탈퇴하기*/
    void removeMember(int communityMemberId);

    /* 커뮤니티 주인 커뮤니티 멤버 추가*/
    void addCommunityCreatorToMember(CommunityUserVO vo);

}
