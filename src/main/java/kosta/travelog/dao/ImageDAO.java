package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.PostImageVO;

import java.util.List;

public interface ImageDAO {
    void addImage(String fileName, int postId) throws DatabaseQueryException;

    List<PostImageVO> getImages();

    String getFirstImage(int postId) throws DatabaseQueryException;
}
