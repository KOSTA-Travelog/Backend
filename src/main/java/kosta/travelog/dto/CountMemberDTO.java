package kosta.travelog.dto;

import com.google.gson.JsonObject;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountMemberDTO {
    private int communityId;
    private int memberCount;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("communityId" , this.getCommunityId());
        json.addProperty("memberCount" , this.getMemberCount());
        return json.toString();
    }
}
