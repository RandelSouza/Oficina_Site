package Servlet;

import Dao.FuncionarioDao;
import Pojo.FuncionarioPojo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rande
 */
public class FuncionarioServlet extends HttpServlet {
    FuncionarioPojo funcionarioPojo = new FuncionarioPojo();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    ArrayList<FuncionarioPojo> listFuncionarioPojo = new ArrayList<>();
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
                                
        if(request.getParameter("id").equals("cadastro")){            
            if(usuario == null){
                response.sendRedirect("index.jsp");
            }else{
                funcionarioPojo.setF_CPF(request.getParameter("cpf"));
                funcionarioPojo.setF_NOME(request.getParameter("nome"));

                funcionarioDao.salvar(funcionarioPojo);
                response.sendRedirect("cadastroFuncionario.jsp");
            }                
        }
        
        if(request.getParameter("id").equals("listar")){
            if(usuario == null){
                    response.sendRedirect("index.jsp");
            }else{
                try {
                    listFuncionarioPojo = (ArrayList<FuncionarioPojo>) funcionarioDao.listar();            
                    request.setAttribute("listFuncionarioPojo", listFuncionarioPojo);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Funcionario.jsp");
                    requestDispatcher.forward(request, response);                        
                } catch (SQLException ex) {
                Logger.getLogger(PecaServlet.class.getName()).log(Level.SEVERE, null, ex);                       
                }
            }            
        }
        
        if(request.getParameter("id").equals("excluir")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{
                    funcionarioPojo.setF_ID(Integer.parseInt(request.getParameter("identificador")));                    
                    funcionarioDao.excluir(funcionarioPojo);                                     
                    System.out.println("nome do funcionario"+ request.getParameter(" identificador"));                    
                    response.sendRedirect("FuncionarioServlet?id=listar");
                }                
            }
            
            if(request.getParameter("id").equals("editar")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{                                         
                    System.out.println("nome do funcionario"+ request.getParameter("id-funcionario"));                    
                    
                    funcionarioPojo.setF_ID(Integer.parseInt(request.getParameter("id-funcionario")));                    
                    funcionarioPojo.setF_NOME(request.getParameter("recipient-name"));
                    funcionarioPojo.setF_CPF(request.getParameter("cpf"));
                    
                    funcionarioDao.editar(funcionarioPojo);
                    
                    
                    response.sendRedirect("FuncionarioServlet?id=listar");
                }                
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
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
