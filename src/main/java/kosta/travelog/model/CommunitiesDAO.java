package kosta.travelog.model;

import java.util.Date;

public interface CommunitiesDAO {
	// 커뮤니티 생성
	public void addCommunity(int communityId, String communityTitle, String communityDescription, String communityHashtag,
			Date communityDate, String communityImage, char communityStatus, String userId);
	
	// 생성한 커뮤니티 아이디 찾기
	public String getCommunityId(String UserId);
}
