/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Connection.ConnectionFactory;
import Pojo.ControlaPojo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class ControlaDao {
    ConnectionFactory connect = new ConnectionFactory();
    String sql = "";
    
    // INSERT
    public void salvar(ControlaPojo controlaPojo) throws ParseException{
        sql = "INSERT INTO Controla(U_ID, P_ID, C_ACAO, C_DATA, C_HORA) VALUES(?, ?, ?, CURRENT_DATE, CURRENT_TIME);";                       
        try {
            connect.connection();
                        
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setInt(1, controlaPojo.getU_ID());
            pst.setInt(2, controlaPojo.getP_ID());          
            pst.setString(3, controlaPojo.getC_ACAO());                             
            pst.execute();
            connect.disconect();            
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar Dados de Controle: " + ex.getMessage());
        }                        
    }    
        
     public void salvar_dados_listar(ControlaPojo controlaPojo) throws ParseException{
         /*Não é viavél no momento porque p_id é chave primaria de controla*/
        sql = "INSERT INTO Controla(U_ID, C_ACAO, C_DATA, C_HORA) VALUES(?, ?, CURRENT_DATE, CURRENT_TIME);";                       
        try {
            connect.connection();
                        
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setInt(1, controlaPojo.getU_ID());        
            pst.setString(2, controlaPojo.getC_ACAO());                             
            pst.execute();
            connect.disconect();            
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar Dados de Controle: " + ex.getMessage());
        }                                  
    }    
}
