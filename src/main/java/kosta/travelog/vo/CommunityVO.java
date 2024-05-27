package kosta.travelog.vo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommunityVO {
    private int communityId;
    private String communityTitle;
    private String communityDescription;
    private String communityHashtag;
    private LocalDate communityDate;
    private String communityImage;
    private char communityStatus;
    private String userId;
}