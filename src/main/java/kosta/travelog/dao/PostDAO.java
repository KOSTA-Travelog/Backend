package kosta.travelog.dao;

import kosta.travelog.vo.PostVO;

import java.sql.SQLException;
import java.util.Collection;

public interface PostDAO {
    /* 홈 게시글 목록 보기(전체 공개) */
    Collection<PostVO> getPostList() throws SQLException;

    /*   사진 삽입*/
    void addImage(PostVO vo);

    /*   게시글 작성*/
    void addPost(PostVO vo);

    /*    공개 상태 변경(request값 받기)*/
    void setPostStatus(char postStatus, int postId);

    /*  사진 삭제*/
    void removePostImage(int imageId);

    /*   게시글 삭제*/
    void removePost(int postId);

    /*  게시글 조회*/
    PostVO getPost(int postId);

    /*    게시글 수정*/
    void setPost(PostVO vo);

    /*    게시글 사진 불러오기*/
    Collection<PostVO> getPostImageList(int postId);

    /*   특정 사용자의 게시글 수*/
    int countUserPost(String userId);

}
