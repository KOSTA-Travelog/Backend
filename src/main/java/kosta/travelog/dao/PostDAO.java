package kosta.travelog.dao;

import kosta.travelog.vo.PostVO;

import java.sql.SQLException;
import java.util.Collection;

public interface PostDAO {
    /* 홈 게시글 목록 보기(전체 공개) */
    Collection<PostVO> getPostList() throws SQLException;

    /*   사진 삽입*/
    boolean addImage();

    /*   게시글 작성*/
    boolean addPost();

    /*    공개 상태 변경(request값 받기)*/
    boolean setPostStatus();

    /*    게시글 수정*/
    boolean setPost();

    /*  사진 삭제*/
    boolean removePostImage();

    /*   게시글 삭제*/
    boolean removePost();

    /*  게시글 조회*/
    Collection<PostVO> getPost();

    /*    게시글 사진 불러오기*/
    Collection<PostVO> getPostImageList();

    /*   특정 사용자의 게시글 수*/
    int countUserPost();

}
