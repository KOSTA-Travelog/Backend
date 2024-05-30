package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.PostImageVO;
import kosta.travelog.vo.PostVO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Slf4j
public class PostDAOImpl implements PostDAO {

    private final Connection conn;

    public PostDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Collection<PostVO> getPostList() throws SQLException {

        String sql = Query.POST_LIST;
        List<PostVO> postList = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                postList.add(new PostVO(rs.getInt("post_id"),
                        rs.getString("post_title"),
                        rs.getString("post_description"),
                        rs.getString("post_hashtag"),
                        rs.getDate("post_date").toLocalDate(),
                        rs.getString("post_status").charAt(0),
                        rs.getString("user_id"),
                        rs.getInt("image_id"),
                        rs.getString("images")
                ));
            }

            return postList;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void addImage(PostImageVO image) {
        String sql = Query.INSERT_POST_IMAGE;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, image.getImages());
            pstmt.setInt(2, image.getPostId());

            int result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long addPost(PostVO post) throws DatabaseQueryException {
        String sql = Query.INSERT_POST;
        try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"POST_ID"})) {

            pstmt.setString(1, post.getPostTitle());
            pstmt.setString(2, post.getPostDescription());
            pstmt.setString(3, post.getPostHashtag());
            pstmt.setString(4, String.valueOf(post.getPostStatus()));
            pstmt.setString(5, post.getUserId());

            int result = pstmt.executeUpdate();
            if (result == 0) {
                throw new DatabaseQueryException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    log.info(String.valueOf(generatedKeys.getLong(1)));
                    return generatedKeys.getLong(1);
                } else {
                    throw new DatabaseQueryException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DatabaseQueryException("Creating post failed");
        }
    }

    @Override
    public void setPostStatus(char postStatus, int postId) {
        String sql = Query.UPDATE_POST_STATUS;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.valueOf(postStatus));
            pstmt.setInt(2, postId);

            int result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePostImage(int imageId) {
        String sql = Query.DELETE_POST_IMAGE;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, imageId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePost(int postId) {
        String sql = Query.DELETE_POST;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, postId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public PostVO getPost(int postId) {
        String sql = Query.POST;
        PostVO post = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, postId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    post = PostVO.builder().postId(rs.getInt("post_id"))
                            .postTitle(rs.getString("post_title"))
                            .postDescription(rs.getString("post_description"))
                            .postHashtag(rs.getString("post_hashtag"))
                            .postDate(rs.getDate("post_date").toLocalDate())
                            .postStatus(rs.getString("post_status").charAt(0)).build();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return post;
    }

    @Override
    public void setPost(PostVO post) {
        String sql = Query.UPDATE_POST;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, post.getPostTitle());
            pstmt.setString(2, post.getPostDescription());
            pstmt.setString(3, post.getPostHashtag());
            pstmt.setString(4, String.valueOf(post.getPostStatus()));
            pstmt.setInt(5, post.getPostId());

            pstmt.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public Collection<PostImageVO> getPostImageList(int postId) {
        String sql = Query.POST_IMAGE_LIST;
        List<PostImageVO> postImageList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, postId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    postImageList.add(PostImageVO.builder()
                            .imageId(rs.getInt("image_id"))
                            .images(rs.getString("images"))
                            .postId(rs.getInt("post_id")).build());
                }

                return postImageList;
            } catch (SQLException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countUserPost(String userId) {
        String sql = Query.COUNT_USER_POST;
        int countPost = 0;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    countPost = rs.getInt(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countPost;
    }

    @Override
    public List<PostVO> getPostPrimaryImageByUserId(String userId) {
        List<PostVO> post = new ArrayList<>();
        String sql = Query.POST_FIRST_IMAGE_BY_USERID;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    post.add(PostVO.builder()
                            .postId(rs.getInt("post_id"))
                            .imageId(rs.getInt("image_id"))
                            .images(rs.getString("images"))
                            .build());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return post;
    }


}
