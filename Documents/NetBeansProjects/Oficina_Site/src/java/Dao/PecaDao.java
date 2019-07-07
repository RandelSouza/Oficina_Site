package Dao;

import Connection.ConnectionFactory;
import Pojo.PecaPojo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PecaDao {
    ConnectionFactory connect = new Connection.ConnectionFactory();
    String sql = "";
    
      // INSERT
    public int salvar(PecaPojo pecaPojo){
        sql = "INSERT INTO Peca(P_NOME, P_PRECO, P_UNIDADE, P_ESTADO) VALUES(?, ?, ?, ?);";                
        try {
            connect.connection();
                        
            PreparedStatement pst = connect.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, pecaPojo.getP_NOME());
            pst.setDouble(2, pecaPojo.getP_PRECO());
            pst.setInt(3, 0);
            pst.setBoolean(4, pecaPojo.isP_ESTADO());            
            pst.execute();
            
            final ResultSet rst = pst.getGeneratedKeys();
            if(rst.next()){
                return rst.getInt("P_ID");
            }
            connect.disconect();            
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar a Peca: " + ex.getMessage());         
        }                   
                
        return -1;
    }
    
    // SELECT
    public ArrayList<PecaPojo> listar() throws SQLException{
        sql = "SELECT * FROM Peca WHERE P_ESTADO != true;";            
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
                    pecaPojo.setP_VALOR_TOTAL(Double.parseDouble(connect.rst.getString("P_VALOR_TOTAL")));
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
        sql = "UPDATE Peca SET P_ESTADO=?, P_UNIDADE=? WHERE P_ID=?;";
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);            
            pst.setBoolean(1, true);
            pst.setInt(2, 0);
            pst.setInt(3, pecaPojo.getP_ID());
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
            pst.setInt(3, 0);
            pst.setInt(4, pecaPojo.getP_ID());            
            pst.execute();
            connect.disconect();                                
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao alterar Peca!");
        }       
    }
    
      public void editar_entrada(PecaPojo pecaPojo){        
        sql = "UPDATE Peca SET P_UNIDADE=? WHERE P_ID=?;";
        try {
            connect.connection();                             
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setInt(1, pecaPojo.getP_UNIDADE());
            pst.setInt(2, pecaPojo.getP_ID());            
            pst.execute();
            connect.disconect();                                
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao alterar a unidade!");
        }       
    }
      public void editar_saida(PecaPojo pecaPojo){        
        sql = "UPDATE Peca SET P_UNIDADE=? WHERE P_ID=?;";
        try {
            connect.connection();                             
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setInt(1, pecaPojo.getP_UNIDADE()*-1);
            pst.setInt(2, pecaPojo.getP_ID());            
            pst.execute();
            connect.disconect();                                
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao alterar a unidade!");
        }       
    }

}