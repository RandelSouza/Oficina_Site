package Servlet;

import Dao.AuditoriaDao;
import Pojo.AuditoriaPojo;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuditoriaServlet extends HttpServlet {
    private static Font fontHeader = new Font(Font.FontFamily.COURIER, 18, Font.BOLD);
    private static Font fontDefault = new Font(Font.FontFamily.TIMES_ROMAN, 12);
    private static Font fontRed = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static Font fontBold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    AuditoriaDao auditoriaDao = new AuditoriaDao();
    ArrayList listAuditoriaPojo = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param title
     * @param request servlet request
     * @param subject
     * @param keyWords
     * @param response servlet response
     * @param document
     * @param author
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    
    public void addMetaData(String title, String subject, String[] keyWords, String author, Document document){
        String kyePhrase = "";
        
        document.addTitle(title);
        document.addSubject(subject);
        for(String key : keyWords){
            kyePhrase += " "+key;
        }
        document.addKeywords(kyePhrase);
        document.addAuthor(author);
        document.addCreator(author);
    }
    
    public void gerarPdfAuditoriaPecas(OutputStream out) throws IOException, DocumentException, SQLException{        
        try {             
            Document document = new Document(PageSize.LETTER, 80, 80, 75, 75);
            PdfWriter.getInstance(document, out);                                    
            document.open();
            
            String[] kyeWords = new String[3];            

            kyeWords[0] = "Peça";
            kyeWords[1] = "Auditoria";
            kyeWords[2] = "Oficina";
            
            addMetaData("Auditoria Peça", "Pagina para geração de arquivos de auditoria de peça", kyeWords, "Davi Oliveira", document);            
                        
            Paragraph paragraph = new Paragraph();
            Font font = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);                                    
            
            paragraph.add(new Phrase("Auditoria de Peças", fontHeader));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));                                               
            document.add(paragraph);
                        
            // Tabela com 1 coluna*/            
            PdfPTable table = new PdfPTable(1);
            table.setTotalWidth(500);
            table.setLockedWidth(true);
                        
            
            PdfPCell c1 = new PdfPCell(new Phrase("Relatório Descritivo de Ações em Peça"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);                                    
            table.setHeaderRows(1);

            listAuditoriaPojo = (ArrayList<AuditoriaPojo>) auditoriaDao.listar();
            
            for(int i = 0; i < listAuditoriaPojo.size(); i++){
                AuditoriaPojo auditoriaPojo = (AuditoriaPojo) listAuditoriaPojo.get(i);             
                String descricao = auditoriaPojo.getA_DESCRICAO().replaceAll(" rt ", "\n")+"\n"
                        + "Identificador da Auditoria: "+String.valueOf(auditoriaPojo.getA_ID())
                        +"\nIdentificador da Peça: "+String.valueOf(auditoriaPojo.getP_ID());                               
                table.addCell(descricao);                
            }                        
           
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.getMessage();
        }        
    }
    
    
        
    public void gerarPdfAuditoriaPecas_entrada(OutputStream out) throws IOException, DocumentException, SQLException{        
        try {             
            Document document = new Document(PageSize.LETTER, 80, 80, 75, 75);
            PdfWriter.getInstance(document, out);                                    
            document.open();
            
            String[] kyeWords = new String[3];            

            kyeWords[0] = "Peça";
            kyeWords[1] = "Auditoria";
            kyeWords[2] = "Oficina";
            
            addMetaData("Auditoria Peça", "Pagina para geração de arquivos de auditoria de peça", kyeWords, "Davi Oliveira", document);            
                        
            Paragraph paragraph = new Paragraph();
            Font font = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);                                    
            
            paragraph.add(new Phrase("Auditoria de Peças", fontHeader));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));                                               
            document.add(paragraph);
                        
            // Tabela com 1 coluna*/            
            PdfPTable table = new PdfPTable(1);
            table.setTotalWidth(500);
            table.setLockedWidth(true);
                        
            
            PdfPCell c1 = new PdfPCell(new Phrase("Relatório Descritivo de Entrada de Peça"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);                                    
            table.setHeaderRows(1);

            listAuditoriaPojo = (ArrayList<AuditoriaPojo>) auditoriaDao.listar_entrada();
            
            for(int i = 0; i < listAuditoriaPojo.size(); i++){
                AuditoriaPojo auditoriaPojo = (AuditoriaPojo) listAuditoriaPojo.get(i);             
                String descricao = auditoriaPojo.getA_DESCRICAO().replaceAll(" rt ", "\n")+"\n"
                        + "Identificador da Auditoria: "+String.valueOf(auditoriaPojo.getA_ID())
                        +"\nIdentificador da Peça: "+String.valueOf(auditoriaPojo.getP_ID());                               
                table.addCell(descricao);                
            }                        
           
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.getMessage();
        }        
    }
   
    
     public void gerarPdfAuditoriaPecas_saida(OutputStream out) throws IOException, DocumentException, SQLException{        
        try {             
            Document document = new Document(PageSize.LETTER, 80, 80, 75, 75);
            PdfWriter.getInstance(document, out);                                    
            document.open();
            
            String[] kyeWords = new String[3];            

            kyeWords[0] = "Peça";
            kyeWords[1] = "Auditoria";
            kyeWords[2] = "Oficina";
            
            addMetaData("Auditoria Peça", "Pagina para geração de arquivos de auditoria de peça", kyeWords, "Davi Oliveira", document);            
                        
            Paragraph paragraph = new Paragraph();
            Font font = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);                                    
            
            paragraph.add(new Phrase("Auditoria de Peças", fontHeader));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));                                               
            document.add(paragraph);
                        
            // Tabela com 1 coluna*/            
            PdfPTable table = new PdfPTable(1);
            table.setTotalWidth(500);
            table.setLockedWidth(true);
                        
            
            PdfPCell c1 = new PdfPCell(new Phrase("Relatório Descritivo de Saída de Peça"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);                                    
            table.setHeaderRows(1);

            listAuditoriaPojo = (ArrayList<AuditoriaPojo>) auditoriaDao.listar_saida();
            
            for(int i = 0; i < listAuditoriaPojo.size(); i++){
                AuditoriaPojo auditoriaPojo = (AuditoriaPojo) listAuditoriaPojo.get(i);             
                String descricao = auditoriaPojo.getA_DESCRICAO().replaceAll(" rt ", "\n")+"\n"
                        + "Identificador da Auditoria: "+String.valueOf(auditoriaPojo.getA_ID())
                        +"\nIdentificador da Peça: "+String.valueOf(auditoriaPojo.getP_ID());                               
                table.addCell(descricao);                
            }                        
           
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.getMessage();
        }        
    }
   
    
     public void gerarPdfAuditoriaPecas_entrada_saida(OutputStream out) throws IOException, DocumentException, SQLException{        
        try {             
            Document document = new Document(PageSize.LETTER, 80, 80, 75, 75);
            PdfWriter.getInstance(document, out);                                    
            document.open();
            
            String[] kyeWords = new String[3];            

            kyeWords[0] = "Peça";
            kyeWords[1] = "Auditoria";
            kyeWords[2] = "Oficina";
            
            addMetaData("Auditoria Peça", "Pagina para geração de arquivos de auditoria de peça", kyeWords, "Davi Oliveira", document);            
                        
            Paragraph paragraph = new Paragraph();
            Font font = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);                                    
            
            paragraph.add(new Phrase("Auditoria de Peças", fontHeader));
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));                                               
            document.add(paragraph);
                        
            // Tabela com 1 coluna*/            
            PdfPTable table = new PdfPTable(1);
            table.setTotalWidth(500);
            table.setLockedWidth(true);
                        
            
            PdfPCell c1 = new PdfPCell(new Phrase("Relatório Descritivo de Entrada e Saída de Peça"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);                                    
            table.setHeaderRows(1);

            listAuditoriaPojo = (ArrayList<AuditoriaPojo>) auditoriaDao.listar_entrada_saida();
            
            for(int i = 0; i < listAuditoriaPojo.size(); i++){
                AuditoriaPojo auditoriaPojo = (AuditoriaPojo) listAuditoriaPojo.get(i);             
                String descricao = auditoriaPojo.getA_DESCRICAO().replaceAll(" rt ", "\n")+"\n"
                        + "Identificador da Auditoria: "+String.valueOf(auditoriaPojo.getA_ID())
                        +"\nIdentificador da Peça: "+String.valueOf(auditoriaPojo.getP_ID());                               
                table.addCell(descricao);                
            }                        
           
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.getMessage();
        }        
    }
   
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DocumentException{                        
        
        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();        
        
        try{
            if(request.getParameter("id").equals("Pecas")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{        
                    gerarPdfAuditoriaPecas(out);        
                }
             }
            
             if(request.getParameter("id").equals("Entrada")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{        
                    gerarPdfAuditoriaPecas_entrada(out);        
                }
             }
             
              if(request.getParameter("id").equals("Saida")){
                if(usuario == null){
                    response.sendRedirect("index.jsp");
                }else{        
                    gerarPdfAuditoriaPecas_saida(out);        
                }
             }
            
            if(request.getParameter("id").equals("Entrada_Saida")){
              if(usuario == null){
                  response.sendRedirect("index.jsp");
              }else{        
                  gerarPdfAuditoriaPecas_entrada_saida(out);        
              }
           }
            
            
        } catch (DocumentException e) {
            e.getMessage();
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
            Logger.getLogger(AuditoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AuditoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AuditoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AuditoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
