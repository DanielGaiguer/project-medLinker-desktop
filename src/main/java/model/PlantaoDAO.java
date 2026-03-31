
package model;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;


/**
 *
 * @author gaigu
 */
public class PlantaoDAO {
    public List<PlantaoBean> listarPlantoes() {
        List<PlantaoBean> listPlantoes = new ArrayList();
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("Select * from plantoes");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                PlantaoBean plantao = new PlantaoBean();
                
                plantao.setId(rs.getInt("id"));
                plantao.setHospital_id(rs.getInt("hospital_id"));
                plantao.setTitulo(rs.getString("titulo"));
                plantao.setEspecialidade(rs.getString("especialidade"));
                plantao.setData_plantao(rs.getDate("data_plantao").toLocalDate());
                plantao.setHora_inicio(rs.getTimestamp("hora_inicio"));
                plantao.setHora_fim(rs.getTimestamp("hora_fim"));
                plantao.setValor(rs.getDouble("valor"));
                plantao.setStatus(rs.getString("status"));
                
                
                listPlantoes.add(plantao);
            }
            
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        return listPlantoes;
    }
    
    public boolean cadastrarPlantao(PlantaoBean plantao, String hospital_name) {
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Configura os parâmetros
            stmt.setInt(1, hospital_id);
            stmt.setString(2, plantao.getTitulo());
            stmt.setString(3, plantao.getEspecialidade());
            
            // CONVERSÃO CORRETA: java.util.Date → java.sql.Date/Time
            java.util.Date utilDate = plantao.getData_plantao_asDate(); // método que retorna java.util.Date do PlantaoBean
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            stmt.setDate(4, sqlDate);
            
            java.util.Date utilHoraInicio = plantao.getHora_inicio();
            java.util.Date utilHoraFim = plantao.getHora_fim();
            stmt.setTime(5, new Time(utilHoraInicio.getTime()));
            stmt.setTime(6, new Time(utilHoraFim.getTime()));
            
            stmt.setDouble(7, plantao.getValor());
            stmt.setString(8, plantao.getStatus());
            
            // Executa o insert
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar plantão: " + e.getMessage());
            e.printStackTrace();
            return false;
        } 
    }
}
