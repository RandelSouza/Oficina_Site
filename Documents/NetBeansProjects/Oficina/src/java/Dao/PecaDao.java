package Dao;

import Connection.ConnectionFactory;
import Pojo.PecaPojo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PecaDao {
    ConnectionFactory connect = new ConnectionFactory();
    String sql = "";
    
    // INSERT
    public void salvar(PecaPojo pecaPojo){
        sql = "INSERT INTO Peca(P_NOME, P_PRECO, P_UNIDADE) VALUES(?, ?, ?);";                
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setString(1, pecaPojo.getP_NOME());
            pst.setDouble(2, pecaPojo.getP_PRECO());
            pst.setInt(3, pecaPojo.getP_UNIDADE());            
            pst.execute();
            connect.disconect();            
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar a Peca: " + ex.getMessage());
        }                        
    }
    
    // SELECT
    public ArrayList<PecaPojo> listar() throws SQLException{
        sql = "SELECT * FROM Peca;";            
        ArrayList<PecaPojo> listPecaPojo = new ArrayList<>();        
        connect.connection();                
        try {
            connect.executaSql(sql);
                   
               while (connect.rst.next()){                
                    PecaPojo pecaPojo = new PecaPojo();
                    pecaPojo.setP_ID(Integer.parseInt(connect.rst.getString("P_ID")));             
                    pecaPojo.setP_NOME(connect.rst.getString("P_NOME"));             
                    pecaPojo.setP_PRECO(Double.parseDouble(connect.rst.getString("P_PRECO")));             
                    pecaPojo.setP_UNIDADE(Integer.parseInt(connect.rst.getString("P_UNIDADE")));             
                    listPecaPojo.add(pecaPojo);                                                                                        
                }                               
            connect.disconect();                        
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao listar Pecas!");
        }                    
        return listPecaPojo;       
    }
    
    // DELETE
    public void excluir(PecaPojo pecaPojo){                
        sql = "DELETE FROM Peca WHERE P_ID=?;";
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);            
            pst.setInt(1, pecaPojo.getP_ID());
            pst.execute();
            connect.disconect();         
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir Peca!");
        }        
    }   
    
   // UPDATE
    public void editar(PecaPojo pecaPojo){        
        sql = "UPDATE Peca SET P_NOME=?, P_PRECO=?, P_UNIDADE=? WHERE P_ID=?;";
        try {
            connect.connection();                             
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setString(1, pecaPojo.getP_NOME());
            pst.setDouble(2, pecaPojo.getP_PRECO());
            pst.setInt(3, pecaPojo.getP_UNIDADE());
            pst.setInt(4, pecaPojo.getP_ID());            
            pst.execute();
            connect.disconect();                                
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao alterar Peca!");
        }       
    }
}