package kosta.travelog.model;

import java.util.Date;

public class PostsVO {
	private int postId;
	private String postTitle;
	private String postDescription;
	private String postHashtag;
	private Date postDate;
	private char postStatus;
	private String userId;
	
	public PostsVO(int postId, String postTitle, String postDescription, String postHashtag, Date postDate,
			char postStatus, String userId) {
		super();
		setPostId(postId);
		setPostTitle(postTitle);
		setPostDescription(postDescription);
		setPostHashtag(postHashtag);
		setPostDate(postDate);
		setPostStatus(postStatus);
		setUserId(userId);
	}
	
	public PostsVO(String postTitle, String postDescription, String postHashtag, Date postDate,
			char postStatus, String userId) {
		super();
		setPostTitle(postTitle);
		setPostDescription(postDescription);
		setPostHashtag(postHashtag);
		setPostDate(postDate);
		setPostStatus(postStatus);
		setUserId(userId);
	}
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostDescription() {
		return postDescription;
	}
	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}
	public String getPostHashtag() {
		return postHashtag;
	}
	public void setPostHashtag(String postHashtag) {
		this.postHashtag = postHashtag;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public char getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(char postStatus) {
		this.postStatus = postStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
		result = prime * result + ((postDescription == null) ? 0 : postDescription.hashCode());
		result = prime * result + ((postHashtag == null) ? 0 : postHashtag.hashCode());
		result = prime * result + postId;
		result = prime * result + postStatus;
		result = prime * result + ((postTitle == null) ? 0 : postTitle.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostsVO other = (PostsVO) obj;
		if (postDate == null) {
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		if (postDescription == null) {
			if (other.postDescription != null)
				return false;
		} else if (!postDescription.equals(other.postDescription))
			return false;
		if (postHashtag == null) {
			if (other.postHashtag != null)
				return false;
		} else if (!postHashtag.equals(other.postHashtag))
			return false;
		if (postId != other.postId)
			return false;
		if (postStatus != other.postStatus)
			return false;
		if (postTitle == null) {
			if (other.postTitle != null)
				return false;
		} else if (!postTitle.equals(other.postTitle))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PostsVO [postId=" + postId + ", postTitle=" + postTitle + ", postDescription=" + postDescription
				+ ", postHashtag=" + postHashtag + ", postDate=" + postDate + ", postStatus=" + postStatus + ", userId="
				+ userId + "]";
	}
	
	
}
