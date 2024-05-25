package kosta.travelog.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {
    private String userId;
    private String profileImage;
    private String nickname;
    private char userStatus;

}
