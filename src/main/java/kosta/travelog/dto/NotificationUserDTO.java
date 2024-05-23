package kosta.travelog.dto;

import java.time.LocalDate;

import com.google.gson.JsonObject;

import kosta.travelog.vo.NotificationVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
