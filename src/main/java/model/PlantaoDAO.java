
package model;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
