package conexao;

import java.sql.*;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/medlinker";
    private static final String USER = "root";
    private static final String PASSWORD = "Daniboy2@";
    
    private Conexao(){}
    
    public static synchronized Connection conectar() {
        Connection conn = null;
        try{
            if(conn == null || conn.isClosed()){
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        }catch( SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
