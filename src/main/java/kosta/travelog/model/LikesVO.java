package kosta.travelog.model;

import java.util.Objects;

public class LikesVO {

    private String likeId;
    private String postId;
    private String userId;

    public LikesVO(String likeId, String userId, String postId) {
        super();
        setLikeId(likeId);
        setUserId(userId);
        setPostId(postId);
    }

    public String getLikeId() {
        return likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikesVO likesVO = (LikesVO) o;
        return Objects.equals(likeId, likesVO.likeId) && Objects.equals(postId, likesVO.postId) && Objects.equals(userId, likesVO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(likeId, postId, userId);
    }

    @Override
    public String toString() {
        return "LikesVO{" +
                "likeId='" + likeId + '\'' +
                ", postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
