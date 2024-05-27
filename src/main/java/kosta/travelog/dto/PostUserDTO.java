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
public class PostUserDTO {
    private int postId;
    private String postTitle;
    private String postDescription;
    private String postHashtag;
    private LocalDate postDate;
    private char postStatus;
    private String userId;
    private int imageId;
    private String images;
    private String profileImage;
    private String nickname;


    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("postId", this.getPostId());
        json.addProperty("postTitle", this.getPostTitle());
        json.addProperty("postDescription", this.getPostDescription());
        json.addProperty("postHashtag", this.getPostHashtag());
        json.addProperty("postDate", this.getPostDate().toString());
        json.addProperty("postStatus", this.getPostStatus());
        json.addProperty("userId", this.getUserId());
        json.addProperty("imageId", this.getImageId());
        json.addProperty("images", this.getImages());
        json.addProperty("profileImage", this.getProfileImage());
        json.addProperty("nickname", this.getNickname());
        return json.toString();
    }
}
