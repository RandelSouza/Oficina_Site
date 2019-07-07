package Servlet;

import Dao.ControlaDao;
import Dao.PecaDao;
import Pojo.ControlaPojo;
import Pojo.PecaPojo;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PecaServlet extends HttpServlet {

    PecaDao pecaDao = new PecaDao();
    ArrayList listPecaPojo = null;
    PecaPojo pecaPojo = new PecaPojo();
    ControlaDao controlaDao = new ControlaDao();
    ControlaPojo controlaPojo = new ControlaPojo();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {  
        
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        int u_id = (int) session.getAttribute("u_id");        
        int p_id;        
        
        try {            
            if(request.getParameter("id").equals("cadastro")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{                                        
                    pecaPojo.setP_NOME(request.getParameter("nome"));
                    pecaPojo.setP_PRECO(Double.parseDouble(request.getParameter("preco")));             
                    p_id = pecaDao.salvar(pecaPojo);                                                         
                    
                    if(p_id != -1){
                        controlaPojo.setU_ID(u_id);
                        controlaPojo.setP_ID(p_id);                                       
                        controlaPojo.setC_ACAO("INSERT");
                    
                        controlaDao.salvar(controlaPojo);
                    }
                    
                    
                    response.sendRedirect("cadastroPeca.jsp");
                }                
            }
            
            if(request.getParameter("id").equals("listar")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{
                    listPecaPojo = (ArrayList<PecaPojo>) pecaDao.listar();            
                    request.setAttribute("listPecaPojo", listPecaPojo);                                                          
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Peca.jsp");
                    requestDispatcher.forward(request, response);
                }                
            }
            
            if(request.getParameter("id").equals("excluir")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{
                    pecaPojo.setP_ID(Integer.parseInt(request.getParameter("identificador")));                    
                    pecaDao.excluir(pecaPojo);                                     
                    
                    p_id = pecaPojo.getP_ID();
                    
                    if(p_id >= 1){
                        controlaPojo.setU_ID(u_id);
                        controlaPojo.setP_ID(p_id);                                       
                        controlaPojo.setC_ACAO("DELETE");                    
                        controlaDao.salvar(controlaPojo);
                    }                                
                    
                    response.sendRedirect("PecaServlet?id=listar");
                }                
            }
            
            if(request.getParameter("id").equals("editar")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{     
                    pecaPojo.setP_ID(Integer.parseInt(request.getParameter("id-peca")));
                    pecaPojo.setP_NOME(request.getParameter("recipient-name"));
                    pecaPojo.setP_PRECO(Double.parseDouble(request.getParameter("preco")));
                    pecaDao.editar(pecaPojo);
                    p_id = pecaPojo.getP_ID();
                    if(p_id >= 1){
                        controlaPojo.setU_ID(u_id);
                        controlaPojo.setP_ID(p_id);
                        controlaPojo.setC_ACAO("UPDATE");
                        controlaDao.salvar(controlaPojo);
                    }                                                                                
                    response.sendRedirect("PecaServlet?id=listar");
                }                
            }
                  
            
            if(request.getParameter("id").equals("entrada")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{                                         
                   
                    pecaPojo.setP_ID(Integer.parseInt(request.getParameter("peca")));
                    pecaPojo.setP_UNIDADE(Integer.parseInt(request.getParameter("u_entrada")));                                       
                    pecaDao.editar_entrada(pecaPojo);
                    
                    p_id = pecaPojo.getP_ID();
                    
                    if(p_id >= 1){
                        controlaPojo.setU_ID(u_id);
                        controlaPojo.setP_ID(p_id);                                       
                        controlaPojo.setC_ACAO("ENTRADA");                    
                        controlaDao.salvar(controlaPojo);
                    }                                                            
                    
                    response.sendRedirect("PecaServlet?id=listar");
                }                
            }
                  
            if(request.getParameter("id").equals("saida")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{                                         
                   
                    pecaPojo.setP_ID(Integer.parseInt(request.getParameter("peca")));
                    pecaPojo.setP_UNIDADE(Integer.parseInt(request.getParameter("u_saida")));                                       
                    pecaDao.editar_saida(pecaPojo);
                    
                    p_id = pecaPojo.getP_ID();
                    
                    if(p_id >= 1){
                        controlaPojo.setU_ID(u_id);
                        controlaPojo.setP_ID(p_id);                                       
                        controlaPojo.setC_ACAO("SAIDA");                    
                        controlaDao.salvar(controlaPojo);
                    }                                                            
                    
                    response.sendRedirect("PecaServlet?id=listar");
                }               
            }                                   
            
        } catch (SQLException ex) {
            Logger.getLogger(PecaServlet.class.getName()).log(Level.SEVERE, null, ex);                       
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PecaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(PecaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short deion of the servlet.
     *
     * @return a String containing servlet deion
     */
    @Override
    public String getServletInfo() {
        return "Short deion";
    }// </editor-fold>
}