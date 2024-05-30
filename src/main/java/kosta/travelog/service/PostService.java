package kosta.travelog.service;

import kosta.travelog.dao.ImageDAOImpl;
import kosta.travelog.dao.PostDAOImpl;
import kosta.travelog.dao.UserDAOImpl;
import kosta.travelog.dto.PostUserDTO;
import kosta.travelog.dto.UserPostImageDTO;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.vo.PostImageVO;
import kosta.travelog.vo.PostVO;
import kosta.travelog.vo.UserVO;
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

    public List<PostUserDTO> postList() throws SQLException {
        List<PostUserDTO> PostUser = new ArrayList<>();
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            List<PostVO> postData = (ArrayList<PostVO>) new PostDAOImpl(conn).getPostList();

            for (PostVO post : postData) {
                UserVO user = new UserDAOImpl(conn).getProfile(post.getUserId());
                String imageUrl = new ImageDAOImpl(conn).getFirstImage(post.getPostId());

                PostUser.add(PostUserDTO.builder()
                        .postId(post.getPostId())
                        .postTitle(post.getPostTitle())
                        .postDescription(post.getPostDescription())
                        .postHashtag(post.getPostHashtag())
                        .postDate(post.getPostDate())
                        .postStatus(post.getPostStatus())
                        .profileImage(user.getProfileImage())
                        .nickname(user.getNickname())
                        .imageId(post.getImageId())
                        .image(imageUrl)
                        .build());

            }
            conn.commit();

        } catch (DatabaseQueryException e) {
            log.error(e.getMessage());
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
        return PostUser;
    }


    public long createPost(PostVO post) throws DatabaseQueryException, DatabaseConnectException {

        try (Connection conn = dataSource.getConnection()) {
            return new PostDAOImpl(conn).addPost(post);

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DatabaseConnectException(e.getMessage());
        }
    }

    public boolean createImage(PostImageVO post) {

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

    public PostUserDTO post(int postId) {
        PostVO post = null;
        UserVO user = null;

        try (Connection conn = dataSource.getConnection()) {
            user = new UserDAOImpl(conn).getPostWriterNickname(postId);
            post = new PostDAOImpl(conn).getPost(postId);
            log.info(String.valueOf(user));
            log.info(String.valueOf(post));
            return PostUserDTO.builder().postId(post.getPostId())
                    .postTitle(post.getPostTitle())
                    .postDescription(post.getPostDescription())
                    .postHashtag(post.getPostHashtag())
                    .postDate(post.getPostDate())
                    .postStatus(post.getPostStatus())
                    .profileImage(user.getProfileImage())
                    .nickname(user.getNickname())
                    .build();

        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
        return null;
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

    public List<PostImageVO> imageList(int postId) {
        List<PostImageVO> images = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            images = (ArrayList<PostImageVO>) new PostDAOImpl(conn).getPostImageList(postId);

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

    public List<UserPostImageDTO> getPostFirstImage(String userId) {
        List<UserPostImageDTO> imageList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            List<PostVO> vo = new PostDAOImpl(conn).getPostPrimaryImageByUserId(userId);

            for (PostVO post : vo) {
                imageList.add(UserPostImageDTO.builder()
                        .postId(post.getPostId())
                        .imageId(post.getImageId())
                        .image(post.getImages())
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return imageList;
    }
}


