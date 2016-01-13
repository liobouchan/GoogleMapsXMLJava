package Servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @author Yzo vl
 */
public class GestionMarcadores extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        String Nombre=request.getParameter("nombre");
        String Latitud=request.getParameter("latitud");
        String Longitud=request.getParameter("longitud");
        String Comentario=request.getParameter("comentario");
        
        try{
            ServletContext context=request.getServletContext();
            File xmlFile =new File(context.getRealPath("/XMLs/Marcadores.xml"));
            FileInputStream fis=new FileInputStream(xmlFile);
            SAXBuilder sb=new SAXBuilder();
            Document doc=sb.build(fis);
            Element marcadores=doc.getRootElement();
            fis.close();
            Element lugar=new Element("Lugar");
            lugar.setAttribute("ID",String.valueOf(marcadores.getChildren().size()+1));
            Element Enombre=new Element("nombre").setText(Nombre);
            lugar.addContent(Enombre);
            Element Ealtitud=new Element("latitud").setText(Latitud);
            lugar.addContent(Ealtitud);
            Element Elongitud=new Element("longitud").setText(Longitud);
            lugar.addContent(Elongitud);
            Element Ecomentario=new Element("comentario").setText(Comentario);
            lugar.addContent(Ecomentario);
            marcadores.addContent(lugar);
            doc.setContent(marcadores);
            FileWriter writer = new FileWriter(context.getRealPath("/XMLs/Marcadores.xml"));
            XMLOutputter xmlop=new XMLOutputter();
            xmlop.setFormat(Format.getPrettyFormat());
            xmlop.output(doc,writer);
            xmlop.output(doc, System.out);
            writer.close();
            response.sendRedirect("index.html");
            //add google maps markers dynamically
        } catch (JDOMException ex) {
            //Logger.getLogger(GestionMarcadores.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Excepcion JDom: " + ex.getMessage());
        }   
    }
}