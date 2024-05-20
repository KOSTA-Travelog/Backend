package kosta.travelog.vo;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO implements Serializable{
    private int commentId;
    private String postComment;
    private LocalDate commentDate;
    private char commentStatus;
    private int postId;
    private String userId;
}
