package kosta.travelog.dao;

import kosta.travelog.dto.CommunityPostDTO;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommunityPostVO;

import java.util.Collection;

public interface CommunityPostDAO {

    /* 커뮤니티 멤버 아닌 경우 커뮤니티 별 게시글 목록*/
    Collection<CommunityPostDTO> communityPostListForGuest(int communityId) throws DatabaseQueryException;

    /*커뮤니티 멤버인 경우 커뮤니티별 게시글 목록 */
    Collection<CommunityPostDTO> communityPostListForMember(int communityId) throws DatabaseQueryException;

    /* 커뮤니티에 게시글 등록*/
    void addCommunityPost(CommunityPostVO vo) throws DatabaseQueryException;

    /*커뮤니티의 게시글 등록 해제*/
    void removeCommunityPost(int communityPostId);

}
