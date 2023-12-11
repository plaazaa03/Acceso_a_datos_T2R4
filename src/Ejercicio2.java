import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Ejercicio2
{
    public static void main(String[] args) {
       try{
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           DocumentBuilder db = dbf.newDocumentBuilder();
           Document doc = db.newDocument();
           doc.setXmlVersion("1.0");

           Element elBiblioteca = doc.createElement("biblioteca");
           doc.app
       } catch (ParserConfigurationException e) {
           throw new RuntimeException(e);
       }
    }
}