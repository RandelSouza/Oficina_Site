package Dao;

import Connection.ConnectionFactory;
import Pojo.UsuarioPojo;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioDao {   
    ConnectionFactory connect = new ConnectionFactory();
           
    String sql = "";
    UsuarioPojo usuarioPojo = new UsuarioPojo();   
     
    public boolean login (UsuarioPojo usuarioPojo){
        sql = "select f.F_ID \n" +
              "from Funcionario as f, Usuario as U " +
              "where u.U_LOGIN = '"+usuarioPojo.getU_LOGIN()+"' and u.U_SENHA = '"+usuarioPojo.getU_SENHA()+"' and f.F_ID = u.F_ID and U.u_estado = false";
      
        connect.connection();        
        
        try {
            connect.executaSql(sql);             
            if (connect.rst.next()){                
                return true;
            }                               
            connect.disconect();    
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return  false;
    }
      
     
    public int getU_ID (UsuarioPojo usuarioPojo){
        sql = "select u.U_ID \n" +
              "from Funcionario as f, Usuario as u " +
              "where u.U_LOGIN = '"+usuarioPojo.getU_LOGIN()+"' and u.U_SENHA = '"+usuarioPojo.getU_SENHA()+"' and f.F_ID = u.F_ID";
      
        connect.connection();        
        
        try {
            connect.executaSql(sql);             
            if (connect.rst.next()){                
                return connect.rst.getInt("U_ID");
            }                               
            connect.disconect();    
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }         
        
        return -1;
    }
    
    
    
    
     public void salvar (UsuarioPojo UsuarioPojo){
        sql = "INSERT INTO Usuario(U_lOGIN, U_SENHA,F_ID, U_ESTADO) VALUES(?,?,?,?);";                
        try {
            connect.connection();
            PreparedStatement pst = connect.connect.prepareStatement(sql);
            pst.setString(1, UsuarioPojo.getU_LOGIN());
            pst.setString(2, UsuarioPojo.getU_SENHA());
            pst.setInt(3, UsuarioPojo.getF_FID());
            pst.setBoolean(4, UsuarioPojo.isU_ESTADO());
            pst.execute();
            connect.disconect();
  
        } catch (SQLException ex) {            
            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar: " + ex.getMessage());
        }                        
    }
    
}