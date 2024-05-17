package kosta.travelog.model;

import java.util.Date;
import java.util.Objects;

public class CommentsVO {

    private String commentId;
    private String postComment;
    private Date commentDate;
    private char commentStatus;
    private int postId;
    private String userId;

    public CommentsVO(String commentId, String postComment, Date commentDate, char commentStatus, int postId, String userId) {
        super();
        setCommentId(commentId);
        setPostComment(postComment);
        setCommentDate(commentDate);
        setCommentStatus(commentStatus);
        setPostId(postId);
        setUserId(userId);
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPostComment() {
        return postComment;
    }

    public void setPostComment(String postComment) {
        this.postComment = postComment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public char getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(char commentStatus) {
        this.commentStatus = commentStatus;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
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
        CommentsVO that = (CommentsVO) o;
        return commentStatus == that.commentStatus && postId == that.postId && Objects.equals(commentId, that.commentId) && Objects.equals(postComment, that.postComment) && Objects.equals(commentDate, that.commentDate) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, postComment, commentDate, commentStatus, postId, userId);
    }

    @Override
    public String toString() {
        return "CommentsVO{" +
                "commentId='" + commentId + '\'' +
                ", postComment='" + postComment + '\'' +
                ", commentDate=" + commentDate +
                ", commentStatus=" + commentStatus +
                ", postId=" + postId +
                ", userId='" + userId + '\'' +
                '}';
    }
}
