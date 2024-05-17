package kosta.travelog.model;

import java.util.Date;

public interface PostsDAO {	
	//게시글 작성
	public void addPost(int postId, String postTitle, String postDescription, String postHashtag, Date postDate,
			char postStatus, String userId);
	
	//홈 게시글 목록 보기 (전체공개 게시글 조회)
	//post_id, post_title, post_description, post_hashtag, post_date, post_status, user_id
	public PostsVO getPosts(char postStatus);
	
	//게시글 정보 조회 (게시글 수정)
	//post_title, post_description, post_hashtag, post_date, post_status, user_id
	public PostsVO getPost(String postId, char postStatus);
}	
