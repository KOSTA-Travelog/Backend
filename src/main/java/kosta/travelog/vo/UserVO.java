package kosta.travelog.vo;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author gyeoul
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {
    private String userId;
    private String name;
    private String email;
    @ToString.Exclude
    private String password;
    private String phoneNumber;
    private String profileImage;
    private String nickname;
    private LocalDate registrationDate;
    private char userStatus;
    private String bio;
    //
    //    @Override
    //    public String toString() {
    //        JsonObject json = new JsonObject();
    //        json.addProperty("userId", this.getUserId());
    //        json.addProperty("name", this.getName());
    //        json.addProperty("email", this.getEmail());
    //        json.addProperty("phoneNumber", this.getPhoneNumber());
    //        json.addProperty("profileImage", this.getProfileImage());
    //        json.addProperty("nickname", this.getName());
    //        json.addProperty("registrationDate", this.getRegistrationDate().toString());
    //        json.addProperty("userStatus", this.getUserStatus());
    //        json.addProperty("bio", this.getBio());
    //        return json.toString();
    //    }
}
