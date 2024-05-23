package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.CommunityPostVO;

public interface CommunityPostDAO {
    /* 커뮤니티에 게시글 등록*/
    void addCommunityPost(CommunityPostVO vo) throws DatabaseQueryException;

    /*커뮤니티의 게시글 등록 해제*/
    void removeCommunityPost(int communityPostId);

}
