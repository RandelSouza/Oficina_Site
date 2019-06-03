package Dao;

import Connection.ConnectionFactory;
import Pojo.UsuarioPojo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao {   
    ConnectionFactory connect = new ConnectionFactory();
    String sql = "";
    UsuarioPojo usuarioPojo = new UsuarioPojo();   
     
    public boolean login (UsuarioPojo usuarioPojo){
        sql = "select f.F_ID \n" +
              "from Funcionario as f, Usuario as U " +
              "where u.U_LOGIN = '"+usuarioPojo.getU_LOGIN()+"' and u.U_SENHA = '"+usuarioPojo.getU_SENHA()+"' and f.F_ID = u.F_ID";
      
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
}