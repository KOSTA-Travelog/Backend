package kosta.travelog.dao;

import kosta.travelog.repository.Query;
import kosta.travelog.vo.PostVO;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class PostDAOImpl implements PostDAO {

    Connection conn;

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
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void addImage(PostVO post) {
        String sql = Query.INSERT_POST_IMAGE;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, post.getPostTitle());
            pstmt.setInt(2, post.getPostId());

            int result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPost(PostVO post) {
        String sql = Query.INSERT_POST;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, post.getPostTitle());
            pstmt.setString(2, post.getPostDescription());
            pstmt.setString(3, post.getPostHashtag());
            pstmt.setString(4, String.valueOf(post.getPostStatus()));
            pstmt.setString(5, post.getUserId());

            int result = pstmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPostStatus(char postStatus, int postId) {
        String sql = Query.UPDATE_POST_STATUS;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, String.valueOf(postStatus));
            pstmt.setInt(2, postId);

            int result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePostImage(int imageId) {
        String sql = Query.DELETE_POST_IMAGE;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, imageId);
            pstmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removePost(int postId) {
        String sql = Query.DELETE_POST;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, postId);
            pstmt.executeUpdate();

        } catch (Exception e) {
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
                            .postStatus(rs.getString("post_status").charAt(0))
                            .userId(rs.getString("user_id")).build();
                }

            } catch (Exception e) {
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
        } catch (Exception e) {

        }
    }

    @Override
    public Collection<PostVO> getPostImageList(int postId) {
        String sql = Query.POST_IMAGE_LIST;
        List<PostVO> postImageList = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, postId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    postImageList.add(PostVO.builder()
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

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return countPost;
    }


}
