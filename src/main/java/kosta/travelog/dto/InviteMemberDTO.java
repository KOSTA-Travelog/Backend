package kosta.travelog.dto;

import com.google.gson.JsonObject;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteMemberDTO {
    private int communityMemberId;
    private int communityId;
    private char communityMemberStatus;
    private String profileImage;
    private String nickname;
    private String bio;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("communityMemberId", this.getCommunityMemberId());
        json.addProperty("communityId", this.getCommunityId());
        json.addProperty("communityMemberStatus", this.getCommunityMemberStatus());
        json.addProperty("profileImage", this.getProfileImage());
        json.addProperty("nickname", this.getNickname());
        json.addProperty("bio", this.getBio());

        return json.toString();
    }
}
