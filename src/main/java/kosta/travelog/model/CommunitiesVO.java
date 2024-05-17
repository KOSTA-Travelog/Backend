package kosta.travelog.model;

import java.util.Date;

public class CommunitiesVO {
	private int communityId;
	private String communityTitle;
	private String communityDescription;
	private String communityHashtag;
	private Date communityDate;
	private String communityImage;
	private char communityStatus;
	private String userId;
	
	public CommunitiesVO(int communityId, String communityTitle, String communityDescription, String communityHashtag,
			Date communityDate, String communityImage, char communityStatus, String userId) {
		super();
		setCommunityId(communityId);
		setCommunityTitle(communityTitle);
		setCommunityDescription(communityDescription);
		setCommunityHashtag(communityHashtag);
		setCommunityDate(communityDate);
		setCommunityImage(communityImage);
		setCommunityStatus(communityStatus);
		setUserId(userId);
	}
	
	public int getCommunityId() {
		return communityId;
	}
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	public String getCommunityTitle() {
		return communityTitle;
	}
	public void setCommunityTitle(String communityTitle) {
		this.communityTitle = communityTitle;
	}
	public String getCommunityDescription() {
		return communityDescription;
	}
	public void setCommunityDescription(String communityDescription) {
		this.communityDescription = communityDescription;
	}
	public String getCommunityHashtag() {
		return communityHashtag;
	}
	public void setCommunityHashtag(String communityHashtag) {
		this.communityHashtag = communityHashtag;
	}
	public Date getCommunityDate() {
		return communityDate;
	}
	public void setCommunityDate(Date communityDate) {
		this.communityDate = communityDate;
	}
	public String getCommunityImage() {
		return communityImage;
	}
	public void setCommunityImage(String communityImage) {
		this.communityImage = communityImage;
	}
	public char getCommunityStatus() {
		return communityStatus;
	}
	public void setCommunityStatus(char communityStatus) {
		this.communityStatus = communityStatus;
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
		result = prime * result + ((communityDate == null) ? 0 : communityDate.hashCode());
		result = prime * result + ((communityDescription == null) ? 0 : communityDescription.hashCode());
		result = prime * result + ((communityHashtag == null) ? 0 : communityHashtag.hashCode());
		result = prime * result + communityId;
		result = prime * result + ((communityImage == null) ? 0 : communityImage.hashCode());
		result = prime * result + communityStatus;
		result = prime * result + ((communityTitle == null) ? 0 : communityTitle.hashCode());
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
		CommunitiesVO other = (CommunitiesVO) obj;
		if (communityDate == null) {
			if (other.communityDate != null)
				return false;
		} else if (!communityDate.equals(other.communityDate))
			return false;
		if (communityDescription == null) {
			if (other.communityDescription != null)
				return false;
		} else if (!communityDescription.equals(other.communityDescription))
			return false;
		if (communityHashtag == null) {
			if (other.communityHashtag != null)
				return false;
		} else if (!communityHashtag.equals(other.communityHashtag))
			return false;
		if (communityId != other.communityId)
			return false;
		if (communityImage == null) {
			if (other.communityImage != null)
				return false;
		} else if (!communityImage.equals(other.communityImage))
			return false;
		if (communityStatus != other.communityStatus)
			return false;
		if (communityTitle == null) {
			if (other.communityTitle != null)
				return false;
		} else if (!communityTitle.equals(other.communityTitle))
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
		return "CommunitiesVO [communityId=" + communityId + ", communityTitle=" + communityTitle
				+ ", communityDescription=" + communityDescription + ", communityHashtag=" + communityHashtag
				+ ", communityDate=" + communityDate + ", communityImage=" + communityImage + ", communityStatus="
				+ communityStatus + ", userId=" + userId + "]";
	}
	
	
}
