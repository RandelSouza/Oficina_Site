package Servlet;

import Dao.UsuarioDao;
import Pojo.UsuarioPojo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
/**
 *
 * @author rande
 */
public class UsuarioServlet extends HttpServlet {
    UsuarioPojo usuarioPojo = new UsuarioPojo();
    UsuarioDao usuarioDao = new UsuarioDao();
    
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
            throws ServletException, IOException {                  
            HttpSession session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");
            
            try {
                if (request.getParameter("id").equals("cadastro")) {
                    if(usuario == null){
                    response.sendRedirect("index.jsp");
                } else{
                    usuarioPojo.setU_LOGIN(request.getParameter("login"));
                    usuarioPojo.setU_SENHA(request.getParameter("senha"));
                    usuarioPojo.setF_FID(Integer.parseInt(request.getParameter("funcionarios")));
                
                    usuarioDao.salvar(usuarioPojo);
                                                          
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("cadastroUsuario.jsp");
                    requestDispatcher.forward(request, response);
                 }
            } 
        } catch (IOException | ServletException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel salvar Usuário: " + e.getMessage());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
