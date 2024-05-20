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
    private String userStatus;
    private String bio;
}
