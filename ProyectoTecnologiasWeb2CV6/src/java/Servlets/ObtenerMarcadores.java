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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //processRequest(request, response);
        out.print("<html lang=\"en\">\n" +
"    <head>\n" +
"        <meta charset=\"utf-8\">\n" +
"        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"        <title>XML Google Maps</title>\n" +
"\n" +
"        <!-- Bootstrap core CSS -->\n" +
"        <link href=\"Bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
"        <!-- Custom styles for this template -->\n" +
"        <link href=\"CSS/inicioApp.css\" rel=\"stylesheet\">\n" +
"        <script src = \"http://maps.googleapis.com/maps/api/js\"></script>\n" +
"\n" +
"        <script>\n" +
"            function loadMap() {\n" +
"                var mapOptions = {\n" +
"                    center:new google.maps.LatLng(19.5046424, -99.14685839999999), zoom:12,\n" +
"                    mapTypeId:google.maps.MapTypeId.ROADMAP,\n" +
"                    zoom:18,\n" +
"                    panControl: true,\n" +
"                    zoomControl: true,\n" +
"                    scaleControl: true,\n" +
"                    mapTypeControl:true,\n" +
"                    streetViewControl:true,\n" +
"                    overviewMapControl:true,\n" +
"                    rotateControl:true\n" +
"                };\n" +
"\n" +
"                var map = new google.maps.Map(document.getElementById(\"sample\"),mapOptions);\n" +
"            }\n" +
"        </script>\n" +
"    </head>    <body onload = \"loadMap()\">\n" +
"      <nav class=\"navbar navbar-inverse navbar-fixed-top\">\n" +
"        <div class=\"container\">\n" +
"          <div class=\"navbar-header\">\n" +
"            <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\">              <span class=\"sr-only\">Toggle navigation</span>\n" +
"              <span class=\"icon-bar\"></span>\n" +
"              <span class=\"icon-bar\"></span>\n" +
"              <span class=\"icon-bar\"></span>\n" +
"            </button>\n" +
"            <a class=\"navbar-brand\" href=\"#\">XML Google Maps</a>\n" +
"          </div>\n" +
"          <div id=\"navbar\" class=\"collapse navbar-collapse\">\n" +
"            <ul class=\"nav navbar-nav\">\n" +
"              <li class=\"active\"><a href=\"#\">Home</a></li>\n" +
"              <li><a href=\"#about\"></a></li>\n" +
"              <li><a href=\"#contact\"></a></li>\n" +
"            </ul>\n" +
"          </div><!--/.nav-collapse -->\n" +
"        </div>\n" +
"      </nav>     <div class=\"container\">\n" +
"        <div class=\"row\">\n" +
"            <div class=\"col-md-6\" id=\"formulario2\">\n" +
"                <h2>Marcadores Guardados en XML</h2>");
        try{
            
          SAXBuilder saxBuilder = new SAXBuilder();
          ServletContext servletContext = request.getServletContext();
          File archivoXML = new File(servletContext.getRealPath("/XMLs/Marcadores.xml"));
          Document document = saxBuilder.build(archivoXML);
          Element nodoRaiz = document.getRootElement();
          //out.println("El nodo Raiz es : " + nodoRaiz);
          List<Element> listaDeMarcadores = nodoRaiz.getChildren();
          //out.println("esto es la lista de Marcadores " + listaDeMarcadores);
          
          for (int i = 0; i < listaDeMarcadores.size(); i++) {
            //out.println("Marcador Número " + i);
            out.println("<br>");
            Element marcador = listaDeMarcadores.get(i);
            //out.println("Marcador " + marcador.getName());
            out.println("<br>");
            out.println("<form>");
            Attribute atributo =  marcador.getAttribute("ID");
            out.println("ID del marcador " + atributo.getValue());
            out.println("<br>");
            out.println("Nombre : " + marcador.getChild("nombre").getText());
            out.println("<br>");
            out.println("Altitud : "+ marcador.getChild("latitud").getText());
            out.println("<br>");
            out.println("Longitud : "+ marcador.getChild("longitud").getText());
            out.println("<br>");
            out.println("Comentarios : "+ marcador.getChild("comentario").getText());
            out.println("<br>");
            out.print("<button type=\"submit\" class=\"btn btn-danger\">Eliminar Marcador</button>");
            out.println("</form>");
          }
        }
        catch (JDOMException ex) {
            Logger.getLogger(ObtenerMarcadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("<br>");
        out.println("<br>");
        out.println("<a class=\"btn btn-success\" href=\"InicioApp.html\" role=\"button\">Regresar</a>");
        out.println("</div>\n" +
"        </div>\n" +
"      </div><!-- /.container -->\n" +
"      <!-- Bootstrap core JavaScript\n" +
"      ================================================== -->\n" +
"      <!-- Placed at the end of the document so the pages load faster -->\n" +
"      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\n" +
"      <script src=\"Bootstrap/js/bootstrap.min.js\"></script>\n" +
"  </body>\n" +
"</html>");
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