package kosta.travelog.dao;

import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.Query;
import kosta.travelog.vo.PostImageVO;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class ImageDAOImpl implements ImageDAO {

    private final Connection conn;

    public ImageDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addImage(String fileName, int postId) throws DatabaseQueryException {
        log.info("fileName: {}, postId: {}", fileName, postId);
        try (PreparedStatement ps = conn.prepareStatement(
                Query.ADD_POST_IMAGE
        )) {
            ps.setString(1, fileName);
            ps.setInt(2, postId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseQueryException("Creating Image failed");
        }
    }

    @Override
    public List<PostImageVO> getImages() {
        return Collections.emptyList();
    }

    @Override
    public String getFirstImage(int postId) throws DatabaseQueryException {
        try (PreparedStatement ps = conn.prepareStatement(
                Query.GET_POST_FIRST_IMAGE
        )) {
            ps.setInt(1, postId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("images");
                }
            }

        } catch (SQLException e) {
            throw new DatabaseQueryException("Creating Image failed");
        }
        return "";
    }
}
