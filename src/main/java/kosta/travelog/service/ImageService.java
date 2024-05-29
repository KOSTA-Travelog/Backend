package kosta.travelog.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import kosta.travelog.dao.ImageDAOImpl;
import kosta.travelog.exception.BadRequestException;
import kosta.travelog.exception.DatabaseConnectException;
import kosta.travelog.exception.DatabaseQueryException;
import kosta.travelog.repository.S3Repository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class ImageService extends CommonService {
    private final S3Repository repository;
    private final DataSource dataSource;

    public ImageService() throws DatabaseConnectException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sun");
            repository = new S3Repository();
        } catch (NamingException e) {
            throw new DatabaseConnectException("dataSource를 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
    }

    public void addImage(Part imagePart, int postId) throws BadRequestException, DatabaseConnectException, DatabaseQueryException {
        String rawName = imagePart.getSubmittedFileName();
        UUID uuid = UUID.randomUUID();
        String fileName = String.format("%s%s", uuid, rawName.substring(rawName.lastIndexOf('.')));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(imagePart.getContentType());
        metadata.setContentLength(imagePart.getSize());
        try (
                InputStream imageStream = imagePart.getInputStream();
                Connection conn = dataSource.getConnection()
        ) {
            ImageDAOImpl dao = new ImageDAOImpl(conn);
            String url = repository.add(fileName, imageStream, metadata);
            dao.addImage(url, postId);
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        } catch (SQLException e) {
            throw new DatabaseConnectException(e.getMessage());
        }
    }
}
