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
public class CommunityVO {
    private int communityId;
    private String communityTitle;
    private String communityDescription;
    private String communityHashtag;
    private LocalDate communityDate;
    private String communityImage;
    private char communityStatus;
    private String userId;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("communityId", this.getCommunityId());
        json.addProperty("communityTitle", this.getCommunityTitle());
        json.addProperty("communityDescription", this.getCommunityDescription());
        json.addProperty("communityHashtag", this.getCommunityHashtag());
        json.addProperty("communityDate", String.valueOf(this.getCommunityDate()));
        json.addProperty("communityImage", this.getCommunityImage());
        json.addProperty("communityStatus", this.getCommunityStatus());
        json.addProperty("userId", this.getUserId());
        return json.toString();
    }
}