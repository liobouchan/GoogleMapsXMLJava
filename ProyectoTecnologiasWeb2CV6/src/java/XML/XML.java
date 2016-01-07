/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.io.FileWriter;  
import java.io.IOException;  
import org.jdom2.Attribute;  
import org.jdom2.Document;  
import org.jdom2.Element;  
import org.jdom2.output.Format;  
import org.jdom2.output.XMLOutputter; 
/**
 *
 * @author lio
 */
public class XML {    
    public void crearXML() {  
  
        try {  

            Element root = new Element("root");  
            Document document = new Document(root);  
            Element elemento = new Element("elemento");  

            elemento.setAttribute(new Attribute("atributo", "valor"));  
            elemento.addContent(new Element("Propiedad").setText("valor de la propiedad"));  
            elemento.addContent(new Element("Propiedad2").setText("valor de la propiedad2"));  
  
            document.getRootElement().addContent(elemento);  
  
            XMLOutputter xmlOutput = new XMLOutputter();  
  
            // passsed System.out to see document content on console  
            xmlOutput.output(document, System.out);  
  
            // passed fileWriter to write content in specified file  
            xmlOutput.setFormat(Format.getPrettyFormat());  
            xmlOutput.output(document, new FileWriter(  
              "/home/lio/Code/GoogleMapsXMLJava/ProyectoTecnologiasWeb2CV6/web/XMLs/generatedXml.xml"));  
  
        } catch (IOException io) {  
            System.out.println(io.getMessage());  
        }
    }  
}  