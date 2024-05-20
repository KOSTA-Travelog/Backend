package kosta.travelog.service;

import kosta.travelog.dao.PostDAO;
import kosta.travelog.dao.PostDAOImpl;
import kosta.travelog.execption.DatabaseConnectException;
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

        List<PostVO> data = new ArrayList<>();


        try (Connection conn = dataSource.getConnection()) {
            try{
                PostDAO dao = new PostDAOImpl(conn);
                data = (ArrayList) dao.getPostList();
                log.info(data.toString());

            } catch(Exception e) {

            }

        } catch(Exception e) {

        }
        return data;
    }


}
