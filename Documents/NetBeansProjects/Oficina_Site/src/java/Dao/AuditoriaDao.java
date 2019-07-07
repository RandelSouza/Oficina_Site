package Dao;

import Connection.ConnectionFactory;
import Pojo.AuditoriaPojo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AuditoriaDao {
    ConnectionFactory connect = new ConnectionFactory();
    String sql = "";
    
    
     // SELECT
    public ArrayList<AuditoriaPojo> listar() throws SQLException{
        sql = "select * from view_auditoria_pecas ORDER BY A_ID;";            
        ArrayList<AuditoriaPojo> listAuditoriaPojo = new ArrayList<>();        
        connect.connection();                
        try {
            connect.executaSql(sql);
                   
               while (connect.rst.next()){                
                    AuditoriaPojo auditoriaPojo = new AuditoriaPojo();
                    auditoriaPojo.setP_ID(connect.rst.getInt("P_ID"));             
                    auditoriaPojo.setA_ID(connect.rst.getInt("A_ID"));             
                    auditoriaPojo.setA_DESCRICAO(connect.rst.getString("A_DESCRICAO"));             
                    listAuditoriaPojo.add(auditoriaPojo);                                                                                        
                }                               
            connect.disconect();                        
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao listar Pecas!");
        }                    
        return listAuditoriaPojo;       
    }
    
      public ArrayList<AuditoriaPojo> listar_entrada() throws SQLException{
        sql = "select * from view_auditoria_pecas_entrada ORDER BY A_ID;";            
        ArrayList<AuditoriaPojo> listAuditoriaPojo = new ArrayList<>();        
        connect.connection();                
        try {
            connect.executaSql(sql);
                   
               while (connect.rst.next()){                
                    AuditoriaPojo auditoriaPojo = new AuditoriaPojo();
                    auditoriaPojo.setP_ID(connect.rst.getInt("P_ID"));             
                    auditoriaPojo.setA_ID(connect.rst.getInt("A_ID"));             
                    auditoriaPojo.setA_DESCRICAO(connect.rst.getString("A_DESCRICAO"));             
                    listAuditoriaPojo.add(auditoriaPojo);                                                                                        
                }                               
            connect.disconect();                        
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao listar Pecas!");
        }                    
        return listAuditoriaPojo;       
    }
      
    public ArrayList<AuditoriaPojo> listar_saida() throws SQLException{
        sql = "select * from view_auditoria_pecas_saida ORDER BY A_ID;";            
        ArrayList<AuditoriaPojo> listAuditoriaPojo = new ArrayList<>();        
        connect.connection();                
        try {
            connect.executaSql(sql);
                   
               while (connect.rst.next()){                
                    AuditoriaPojo auditoriaPojo = new AuditoriaPojo();
                    auditoriaPojo.setP_ID(connect.rst.getInt("P_ID"));             
                    auditoriaPojo.setA_ID(connect.rst.getInt("A_ID"));             
                    auditoriaPojo.setA_DESCRICAO(connect.rst.getString("A_DESCRICAO"));             
                    listAuditoriaPojo.add(auditoriaPojo);                                                                                        
                }                               
            connect.disconect();                        
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao listar Pecas!");
        }                    
        return listAuditoriaPojo;       
    }
    
    public ArrayList<AuditoriaPojo> listar_entrada_saida() throws SQLException{
        sql = "select * from view_auditoria_pecas_entrada_saida ORDER BY A_ID;";            
        ArrayList<AuditoriaPojo> listAuditoriaPojo = new ArrayList<>();        
        connect.connection();                
        try {
            connect.executaSql(sql);
                   
               while (connect.rst.next()){                
                    AuditoriaPojo auditoriaPojo = new AuditoriaPojo();
                    auditoriaPojo.setP_ID(connect.rst.getInt("P_ID"));             
                    auditoriaPojo.setA_ID(connect.rst.getInt("A_ID"));             
                    auditoriaPojo.setA_DESCRICAO(connect.rst.getString("A_DESCRICAO"));             
                    listAuditoriaPojo.add(auditoriaPojo);                                                                                        
                }                               
            connect.disconect();                        
        } catch (SQLException ex) {
            Logger.getLogger(PecaDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao listar Pecas!");
        }                    
        return listAuditoriaPojo;       
    }
    
}
