package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {

    public static Connection getConnection() {  // JDBC 표준 인터페이스가 제공하는 Connection
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection = {}, class = {}", connection, connection.getClass());
            // 객체 정보와 클래스 (타입) 정보

            return connection;
        } catch (SQLException e) {
//            e.printStackTrace();  // CheckException
            throw new IllegalStateException(e);  // RuntimeException
        }

    }


}
