package kosta.travelog.model;

public interface CommentsDAO {

    public int getCountComment(int postId);
    public CommentsVO getComment(int postId, int commentStatus);
}
