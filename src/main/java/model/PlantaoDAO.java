
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
                String statusBD = rs.getString("status");
                PlantaoBean.StatusPlantao status =
                        PlantaoBean.StatusPlantao.valueOf(statusBD);
                
                plantao.setId(rs.getInt("id"));
                plantao.setHospital_id(rs.getInt("hospital_id"));
                plantao.setTitulo(rs.getString("titulo"));
                plantao.setEspecialidade(rs.getString("especialidade"));
                plantao.setData_plantao(rs.getDate("data_plantao").toLocalDate());
                plantao.setHora_inicio(rs.getTimestamp("hora_inicio"));
                plantao.setHora_fim(rs.getTimestamp("hora_fim"));
                plantao.setValor(rs.getDouble("valor"));
                plantao.setStatus(status);
                
                
                listPlantoes.add(plantao);
            }
            
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        return listPlantoes;
    }
    
    public boolean cadastrarPlantao(PlantaoBean plantao) {
        
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("insert into plantoes (hospital_id, titulo, especialidade, data_plantao, hora_inicio, hora_fim, valor, status) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)");
            
            // Configura os parâmetros
            stmt.setInt(1, plantao.getHospital_id());
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
            stmt.setString(8, plantao.getStatus().name());
            
            // Executa o insert
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar plantão: " + e.getMessage());
            e.printStackTrace();
            return false;
        } 
    }
    
    public void toggleDeactive(int idPlantao){
        String sql = "update plantoes set status = ? where id = ?";
        String sqlCreate = "insert into plantoes_reservados values (?, ?)";
        SessaoUsuario usuarioLogado = SessaoUsuario.getInstance();
        
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, PlantaoBean.StatusPlantao.preenchido.name());
            stmt.setInt(2, idPlantao);
            
            stmt.executeUpdate();
            
        } catch(Exception e){
            throw new RuntimeException(e);
        }     
        
         try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sqlCreate)) {
             
             //System.out.println(usuarioLogado.getLoggedUser().getId());
            
            stmt.setInt(1, usuarioLogado.getId());
            stmt.setInt(2, idPlantao);
            
            stmt.executeUpdate();
            
        } catch(Exception e){
            throw new RuntimeException(e);
        }        
    }
    
        public List<PlantaoBean> listarPlantoesReservados(int idUsuario) {
    List<PlantaoBean> listPlantoes = new ArrayList<>();

    try {
        Connection conn = Conexao.conectar();

        String sql = """
            SELECT p.*
            FROM plantoes_reservados pr
            JOIN plantoes p ON p.id = pr.plantao_id
            WHERE pr.usuario_id = ?
        """;

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            PlantaoBean plantao = new PlantaoBean();

            String statusBD = rs.getString("status");
            PlantaoBean.StatusPlantao status =
                    PlantaoBean.StatusPlantao.valueOf(statusBD);

            plantao.setId(rs.getInt("id"));
            plantao.setHospital_id(rs.getInt("hospital_id"));
            plantao.setTitulo(rs.getString("titulo"));
            plantao.setEspecialidade(rs.getString("especialidade"));
            plantao.setData_plantao(rs.getDate("data_plantao").toLocalDate());

            plantao.setHora_inicio(rs.getTimestamp("hora_inicio"));
            plantao.setHora_fim(rs.getTimestamp("hora_fim"));

            plantao.setValor(rs.getDouble("valor"));
            plantao.setStatus(status);

            listPlantoes.add(plantao);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return listPlantoes;
}
}
