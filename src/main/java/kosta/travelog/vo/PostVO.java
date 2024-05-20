package kosta.travelog.vo;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostVO {
    private int postId;
    private String postTitle;
    private String postDescription;
    private String postHashtag;
    private String postDate;
    private char postStatus;
    private String userId;
    private int imageId;
    private String images;
}
