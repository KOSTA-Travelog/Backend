package kosta.travelog.dao;

import kosta.travelog.dto.CommunityDTO;
import kosta.travelog.dto.CommunityPostDTO;
import kosta.travelog.dto.CountMemberDTO;
import kosta.travelog.dto.InviteMemberDTO;
import kosta.travelog.exception.DatabaseQueryException;

import java.util.Collection;

public interface CommunityManagerDAO {
    /* 커뮤니티 목록 불러오기(myCommunities) community_member_status = 1인 경우만*/
    Collection<CommunityDTO> myCommunityList(String userId) throws DatabaseQueryException;

    /* 커뮤니티 목록 불러오기(AllCommunities)*/
    Collection<CommunityDTO> allCommunityList() throws DatabaseQueryException;

    /* 커뮤니티별 멤버 수*/
    CountMemberDTO getCountMemberByCommunityId(int communityId) throws DatabaseQueryException;

    /* 내가 만든 커뮤니티 목록*/
    Collection<CommunityDTO> userCreatedCommunityList(String userId) throws DatabaseQueryException;

    /* 가입한 커뮤니티 목록*/
    Collection<CommunityDTO> userJoinedCommunityList(String userId) throws DatabaseQueryException;

    /* (멤버 추가 화면)초대 수락 대기중 목록 community_member_status = 2*/
    Collection<InviteMemberDTO> pendingInvitationList(int communityId) throws DatabaseQueryException;

    /* (멤버변경)커뮤니티 멤버 목록 현재 멤버 community_member_status = 1*/
    Collection<InviteMemberDTO> currentMemberList(int communityId) throws DatabaseQueryException;

    /* 커뮤니티 멤버 아닌 경우 커뮤니티 별 게시글 목록*/
    Collection<CommunityPostDTO> communityPostListForGuest(char communityMemberStatus) throws DatabaseQueryException;

    /*커뮤니티 멤버인 경우 커뮤니티별 게시글 목록 */
    Collection<CommunityPostDTO> communityPostListForMember(char communityMemberStatus) throws DatabaseQueryException;
}
