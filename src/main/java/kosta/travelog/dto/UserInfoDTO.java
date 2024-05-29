package kosta.travelog.dto;

import com.google.gson.JsonObject;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String nickname;
    private String bio;
    private String profileImage;

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("name", this.getName());
        json.addProperty("email", this.getEmail());
        json.addProperty("phoneNumber", this.getPhoneNumber());
        json.addProperty("nickname", this.getNickname());
        json.addProperty("bio", this.getBio());
        json.addProperty("profileImage", this.getProfileImage());

        return json.toString();
    }
}
