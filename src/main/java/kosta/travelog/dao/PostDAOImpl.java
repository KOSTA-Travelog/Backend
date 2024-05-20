package kosta.travelog.dao;

import kosta.travelog.repository.Query;
import kosta.travelog.vo.PostVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
                        rs.getString("post_date"),
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
    public boolean addImage() {
        return false;
    }

    @Override
    public boolean addPost() {
        return false;
    }

    @Override
    public boolean setPostStatus() {
        return false;
    }

    @Override
    public boolean setPost() {
        return false;
    }

    @Override
    public boolean removePostImage() {
        return false;
    }

    @Override
    public boolean removePost() {
        return false;
    }

    @Override
    public Collection<PostVO> getPost() {
        return null;
    }

    @Override
    public Collection<PostVO> getPostImageList() {
        return null;
    }

    @Override
    public int countUserPost() {
        return 0;
    }
}
