/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crearxml;
import java.io.IOException;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author lio
 */
public class CrearXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SAXBuilder saxBuilder = new SAXBuilder();
        
        // TODO code application logic here
        Element root = new Element("NuevoXML");
        Element elemento = new Element("Elemento");
        
        elemento.setAttribute("atributo", "valor");
        elemento.setText("textoAgregado");
        root.addContent(elemento);
        
        Document doc = new Document(root);
        
        try {
            XMLOutputter serializer = new XMLOutputter();
            serializer.output(doc, System.out);
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
    
}
