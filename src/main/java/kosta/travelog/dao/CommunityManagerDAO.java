package kosta.travelog.dao;

import kosta.travelog.dto.CommunityDTO;
import kosta.travelog.dto.InviteMemberDTO;
import kosta.travelog.exception.DatabaseQueryException;

import java.util.Collection;

public interface CommunityManagerDAO {
    /* 커뮤니티 목록 불러오기(myCommunities) community_member_status = 1인 경우만*/
    Collection<CommunityDTO> myCommunityList(String userId) throws DatabaseQueryException;

    /* 커뮤니티 목록 불러오기(AllCommunities)*/
    Collection<CommunityDTO> allCommunityList() throws DatabaseQueryException;

    /* 내가 만든 커뮤니티 목록*/
    Collection<CommunityDTO> userCreatedCommunityList(String userId) throws DatabaseQueryException;

    /* 가입한 커뮤니티 목록*/
    Collection<CommunityDTO> userJoinedCommunityList(String userId) throws DatabaseQueryException;

    /* (멤버 추가 화면)초대 수락 대기중 목록 community_member_status = 2*/
    Collection<InviteMemberDTO> pendingInvitationList(int communityId) throws DatabaseQueryException;

    /* (멤버변경)커뮤니티 멤버 목록 현재 멤버 community_member_status = 1*/
    Collection<InviteMemberDTO> currentMemberList(int communityId) throws DatabaseQueryException;

    String getCommunityUserNickname(int communityId, String userId);

    /* 특정 커뮤니티장의 닉네임 가져오기*/
    String getCommunityCreatorNickname(int communityId);
}
