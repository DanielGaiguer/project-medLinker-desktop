/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class HospitalDAO {
    public void cadastar(HospitalBean hospital) {
        
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("Select * from usuarios where (usuario = ? and senha = ?)");
            
            stmt.setString(1, hospital.getUsuario());
            stmt.setString(2, hospital.getSenha());
            
            rs = stmt.executeQuery();
            String usuarioId = null;
            
            while(rs.next()){
                if(rs.getString("id") == null){
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                    return;
                }
                usuarioId = rs.getString("id");
            }
            
            stmt = conn.prepareStatement("INSERT INTO hospitais (usuario_id, nome, cidade, estado) values (?, ?, ?, ?)");
            
            stmt.setString(1, usuarioId);
            stmt.setString(2, hospital.getNome());
            stmt.setString(3, hospital.getCidade());   
            stmt.setString(4, hospital.getEstado()); 
            
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Hospital cadastrado com sucesso!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
