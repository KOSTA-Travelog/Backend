package kosta.travelog.vo;

import com.google.gson.JsonObject;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityPostVO {
    private int communityPostId;
    private int communityId;
    private int postId;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();

        json.addProperty("communityPostId" , this.getCommunityId());
        json.addProperty("communityId" , this.getCommunityId());
        json.addProperty("postId" , this.getPostId());
        return json.toString();
    }
}
