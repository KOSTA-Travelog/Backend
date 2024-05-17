package kosta.travelog.service;

import kosta.travelog.execption.DatabaseConnectException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ExampleService {
    private final DataSource dataSource;

    public ExampleService() throws DatabaseConnectException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/sun");
        } catch (NamingException e) {
            throw new DatabaseConnectException("dataSource를 받아오지 못했습니다.\n" +
                    String.format("%s %s", this.getClass(), e.getMessage())
            );
        }
    }

    public void getUserExample() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try {
                // conn.setAutoCommit(false);
                // CustomerDAO dao = new CustomerDAO(conn);
                // dao.addCustomer(id, pw, name);
                // PointDAO pointDAO = new PointDAO(conn);
                // pointDAO.updatePoint(id, 100);
                // conn.commit();
                // return true;
            } catch (Exception e) {
                // conn.rollback();
                // return false;
            } finally {
                // conn.setAutoCommit(true);
            }
        }
    }
}
