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
import javax.xml.parsers.DocumentBuilderFactory;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;


/**
 *
 * @author lio
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        //Para obtener datos acerca de la aplicacion web
        ServletContext context = request.getServletContext();
        //El SAXBuilder es necesario para poder parsear el archivo
        SAXBuilder saxBuilder = new SAXBuilder();
        //Obtenemos la ruta relativa del archivo XML
        File archivoXML = new File(context.getRealPath("/XMLs/Usuarios/usuarios.xml"));
        
        try{
            //Se crea un documento a traves del archivo
            Document document = (Document) saxBuilder.build(archivoXML);
            //Obteniendo nodoRaiz
            Element nodoRaiz = document.getRootElement();
            List list = nodoRaiz.getChildren("Usuario");

            for (int i = 0; i < list.size(); i++) {
                Element elemento = (Element) list.get(i);
                List listUsuario = elemento.getChildren("user");
                List listPassword = elemento.getChildren("pass");
                if( listUsuario != null && listUsuario.size() > 0 ){
                    Element elementoUsuario = (Element)listUsuario.get(0);
                    //System.out.println("elemeto" + elementoUsuario);
                    String usuarioXML = elementoUsuario.getText();
                    System.out.println("lalala" + usuarioXML);
                    if(usuario.equals(usuarioXML)){
                        System.out.println("Entro a la igualdad" + usuarioXML);
                    }
                }
            }
            
        } catch (JDOMException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
