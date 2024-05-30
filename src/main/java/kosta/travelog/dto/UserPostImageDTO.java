package kosta.travelog.dto;

import com.google.gson.JsonObject;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPostImageDTO {
    private int postId;
    private int imageId;
    private String image;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();

        json.addProperty("postId", this.getPostId());
        json.addProperty("imageId", this.getImageId());
        json.addProperty("image", this.getImage());

        return json.toString();
    }
}
