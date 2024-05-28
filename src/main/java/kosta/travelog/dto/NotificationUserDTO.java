package kosta.travelog.dto;

import com.google.gson.JsonObject;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationUserDTO {
	private String nickname;
	private String bio;
	private char notificationType;
	private int communityId;
	
	@Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("nickname", this.getNickname());
        json.addProperty("bio", this.getBio());
        json.addProperty("notificationType", this.getNotificationType());
        json.addProperty("communityId", this.getCommunityId());
        return json.toString();
	}
}
