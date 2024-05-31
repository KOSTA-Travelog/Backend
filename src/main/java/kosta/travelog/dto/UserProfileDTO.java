package kosta.travelog.dto;

import com.google.gson.JsonObject;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDTO {
    private String userId;
    private String nickname;
    private String bio;
    private String profileImage;
    private char userStatus;
    private char communityMemberStatus;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("userId", this.getUserId());
        json.addProperty("nickname", this.getNickname());
        json.addProperty("profileImage", this.getProfileImage());
        json.addProperty("userStatus", this.getUserStatus());
        json.addProperty("bio", this.getBio());
        json.addProperty("communityMemberStatus", this.getCommunityMemberStatus());
        return json.toString();
    }
}
