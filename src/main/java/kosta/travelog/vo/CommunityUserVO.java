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
public class CommunityUserVO {
    private int communityMemberId;
    private LocalDate communityJoinDate;
    private int communityId;
    private String userId;
    private char communityMemberStatus;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("communityMemberId", this.getCommunityMemberId());
        json.addProperty("communityJoinDate", String.valueOf(this.getCommunityJoinDate()));
        json.addProperty("communityId", this.getCommunityId());
        json.addProperty("userId", this.getUserId());
        json.addProperty("communityMemberStatus", this.getCommunityMemberStatus());
        return json.toString();
    }
}
