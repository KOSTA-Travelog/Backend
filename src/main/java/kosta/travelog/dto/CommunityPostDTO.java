package kosta.travelog.dto;

import com.google.gson.JsonObject;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityPostDTO {
    private int postId;
    private LocalDate postDate;
    private char postStatus;
    private int communityPostId;
    private int communityId;
    private int imageId;
    private String image;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("postId", this.getPostId());
        json.addProperty("postDate", this.getPostDate().toString());
        json.addProperty("postStatus", this.getPostStatus());
        json.addProperty("communityPostId", this.getCommunityPostId());
        json.addProperty("communityId", this.getCommunityId());
        json.addProperty("imageId", this.getImageId());
        json.addProperty("image", this.getImage());
        return json.toString();
    }
}
