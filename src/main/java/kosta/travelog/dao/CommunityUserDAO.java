package kosta.travelog.dao;

import kosta.travelog.vo.CommunityUserVO;

public interface CommunityUserDAO {
    /* 커뮤니티 멤버 추가(가입 대기) community_member_status = 2*/
    void addPendingMember(CommunityUserVO vo);

    /*커뮤니티 멤버 추가(수락 완료) update community_member_status = 2*/
    void updateMemberToCommunity(int communityMemberId);

    /* 커뮤니티 탈퇴하기*/
    void removeMember(int communityMemberId);

    /* 커뮤니티 멤버 추가 community_member_status = 1*/
    void addCommunityCreatorToMember(CommunityUserVO vo);

}
