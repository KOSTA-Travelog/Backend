package kosta.travelog.model;

public interface LikesDAO {

    public LikesVO getLikes(String id);
    public boolean addLikes(LikesVO like);
    public boolean deleteLikes(String id);
}
