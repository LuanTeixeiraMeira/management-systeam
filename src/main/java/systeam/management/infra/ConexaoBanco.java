package systeam.management.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL = "jdbc:postgresql://localhost:5432/tarefas";
    private static final String User = "postgres";
    private static final String Password = "475400";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, User, Password);
    }
}
