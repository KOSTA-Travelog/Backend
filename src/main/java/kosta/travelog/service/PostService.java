package kosta.travelog.service;

import kosta.travelog.dao.PostDAO;
import kosta.travelog.dao.PostDAOImpl;
import kosta.travelog.dto.PostImageDTO;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.vo.PostVO;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PostService {
    private final DataSource dataSource;

    public PostService() throws DatabaseConnectException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sun");
        } catch (NamingException e) {
            throw new DatabaseConnectException("dataSource를 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
    }

    public List<PostVO> postList() throws SQLException {
        List<PostVO> data;
        try (Connection conn = dataSource.getConnection()) {
            PostDAO dao = new PostDAOImpl(conn);
            data = (List<PostVO>) dao.getPostList();
            log.info(data.toString());
            return data;
        }
    }


    public boolean createPost(PostVO post) {

        try (Connection conn = dataSource.getConnection()) {
            new PostDAOImpl(conn).addPost(post);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean createImage(PostVO post) {
        try (Connection conn = dataSource.getConnection()) {
            new PostDAOImpl(conn).addImage(post);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean editPostStatus(char postStatus, int postId) {
        try (Connection conn = dataSource.getConnection()) {
            new PostDAOImpl(conn).setPostStatus(postStatus, postId);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deletePostImage(int imageId) {
        try (Connection conn = dataSource.getConnection()) {
            new PostDAOImpl(conn).removePostImage(imageId);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deletePost(int postId) {
        try (Connection conn = dataSource.getConnection()) {
            new PostDAOImpl(conn).removePost(postId);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public PostVO post(int postId) {
        PostVO post = null;
        try (Connection conn = dataSource.getConnection()) {
            post = new PostDAOImpl(conn).getPost(postId);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return post;
    }

    public boolean editPost(PostVO post) {
        try (Connection conn = dataSource.getConnection()) {
            new PostDAOImpl(conn).setPost(post);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }

    public List<PostImageDTO> imageList(int postId) {
        List<PostImageDTO> images = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            List<PostVO> vo = (ArrayList<PostVO>) new PostDAOImpl(conn).getPostImageList(postId);
            for (PostVO post : vo) {
                images.add(PostImageDTO.builder()
                        .imageId(post.getImageId())
                        .images(post.getImages())
                        .postId(post.getPostId()).build());
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return images;
    }

    public int countUserPostNumber(String userId) {
        int num = 0;
        try (Connection conn = dataSource.getConnection()) {
            num = new PostDAOImpl(conn).countUserPost(userId);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return num;
    }
}


