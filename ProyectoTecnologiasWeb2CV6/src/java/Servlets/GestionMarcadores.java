package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        out.println("nombre: "+Nombre+" latitud: "+Latitud+" Longitud: "+Longitud+" Comentario: "+Comentario);
    }

}