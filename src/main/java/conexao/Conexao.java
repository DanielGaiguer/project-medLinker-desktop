package conexao;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/medlinker";
    private static final String USER = "root";
    private static final String PASSWORD = "Daniboy2@";
    
    public static Connection conectar() {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return conn;
        
    }
    
    public void testConnection () {
        Connection conn = conectar();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar");
        }
//        }else {
//            JOptionPane.showMessageDialog(null, "Conectado com sucesso");
//        }
    }
}
