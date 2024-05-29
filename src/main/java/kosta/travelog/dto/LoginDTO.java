package kosta.travelog.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class LoginDTO implements Serializable {
    private String userId;
    private String profileImage;
    private String nickname;
    private char userStatus;


}
