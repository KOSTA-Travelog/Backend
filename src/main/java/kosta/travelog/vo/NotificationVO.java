package kosta.travelog.vo;

import com.google.gson.JsonObject;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationVO {
	private String notificationId;
	private char notificationType;
	private char notificationRead;
	private String userId;
	private String userId2;
	private LocalDate notificationDate;
	private int communityId;
	private String followId;
	private String likeId;
	private int commentId;
	private int commentReplyId;
	private int communityMemberId;
	
	@Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("notificationId", this.getNotificationId());
        json.addProperty("notificationType", this.getNotificationType());
        json.addProperty("notificationRead", this.getNotificationType());
        json.addProperty("userId", this.getUserId());
        json.addProperty("userId2", this.getUserId2());
        json.addProperty("notificationDate", String.valueOf(this.getNotificationRead()));
        json.addProperty("communityId", this.getCommunityId());
        json.addProperty("likeId", this.getLikeId());
        json.addProperty("commentId", this.getCommentId());
        json.addProperty("commentReplyId", this.getCommentId());
        json.addProperty("communityMemberId", this.getCommunityMemberId());
        return json.toString();
    }
}
