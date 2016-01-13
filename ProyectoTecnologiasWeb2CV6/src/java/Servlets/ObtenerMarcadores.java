/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author lio
 */
public class ObtenerMarcadores extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ObtenerMarcadores</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ObtenerMarcadores at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter out = response.getWriter();
        //processRequest(request, response);
        try{
          SAXBuilder saxBuilder = new SAXBuilder();
          ServletContext servletContext = request.getServletContext();
          File archivoXML = new File(servletContext.getRealPath("/XMLs/Marcadores.xml"));
          Document document = saxBuilder.build(archivoXML);
          Element nodoRaiz = document.getRootElement();
          out.println("El nodo Raiz es : " + nodoRaiz);
          List<Element> listaDeMarcadores = nodoRaiz.getChildren();
          out.println("esto es la lista de Marcadores " + listaDeMarcadores);
          
          for (int i = 0; i < listaDeMarcadores.size(); i++) {
              out.println("Marcador Número " + i);
              Element marcador = listaDeMarcadores.get(i);
              out.println("Marcador " + marcador.getName());
              Attribute atributo =  marcador.getAttribute("ID");
              out.println("ID del marcador " + atributo.getValue());
              
            out.println("Nombre : " + marcador.getChild("nombre").getText());
            out.println("Altitud : "+ marcador.getChild("altitud").getText());
            out.println("Longitud : "+ marcador.getChild("longitud").getText());
            out.println("Comentarios : "+ marcador.getChild("comentario").getText());	
          }
          
        }
        catch (JDOMException ex) {
            Logger.getLogger(ObtenerMarcadores.class.getName()).log(Level.SEVERE, null, ex);
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