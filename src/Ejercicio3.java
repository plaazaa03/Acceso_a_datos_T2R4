import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // Crear nodo raíz (Instituto)
            Element elementInstituto = doc.createElement("Instituto");
            doc.appendChild(elementInstituto);

            Scanner scanner = new Scanner(System.in);

            int opcion = 0;

            do {
                System.out.println("=======Menú=======");
                System.out.println("1. Crear fichero Alumnos.");
                System.out.println("2. Visualizar fichero Alumnos.");
                System.out.println("3. Añadir un nuevo Alumno.");
                System.out.println("0. Salir.");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        crearFicheroAlumno(doc);
                        break;
                    case 2:
                        visualizarAlumno(doc);
                        break;
                    case 3:
                        añadirAlumno(doc);
                        break;
                    case 0:
                        System.out.println("Saliendo del programa");
                        System.out.println("Hasta la próxima");
                        break;
                }

            } while (opcion != 0);
            scanner.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void crearFicheroAlumno(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Instituto.xml"));

            transformer.transform(source, result);

            System.out.println("Fichero Alumnos creado exitosamente.");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void visualizarAlumno(Document doc) {
        NodeList alumnos = doc.getElementsByTagName("Alumno");

        System.out.println("Contenido del fichero Alumnos:");

        for (int i = 0; i < alumnos.getLength(); i++) {
            Node alumnoNode = alumnos.item(i);

            if (alumnoNode.getNodeType() == Node.ELEMENT_NODE) {
                Element alumnoElement = (Element) alumnoNode;

                String nExp = alumnoElement.getAttribute("n_exp");
                String nombre = getTextContent(alumnoElement, "Nombre");
                String apellidos = getTextContent(alumnoElement, "Apellidos");
                String direccion = getTextContent(alumnoElement, "Direccion");
                String fnac = getTextContent(alumnoElement, "Fnac");

                System.out.println("============");
                System.out.println("Número de expediente: " + nExp);
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellidos: " + apellidos);
                System.out.println("Dirección: " + direccion);
                System.out.println("Fecha de Nacimiento: " + fnac);
                System.out.println("================");
            }
        }
    }

    private static String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            return node.getTextContent();
        }
        return "";
    }

    private static void añadirAlumno(Document doc) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los datos del nuevo Alumno:");
        System.out.print("Número de expediente: ");
        String nExp = scanner.next();

        System.out.print("Nombre: ");
        String nombre = scanner.next();

        System.out.print("Apellidos: ");
        String apellidos = scanner.next();

        System.out.print("Dirección: ");
        String direccion = scanner.next();

        System.out.print("Fecha de Nacimiento: ");
        String fnac = scanner.next();

        // Crear nodo Alumno
        Element elementAlumno = doc.createElement("Alumno");
        elementAlumno.setAttribute("n_exp", nExp);

        Element elementNombre = doc.createElement("Nombre");
        elementNombre.appendChild(doc.createTextNode(nombre));
        elementAlumno.appendChild(elementNombre);

        Element elementApellidos = doc.createElement("Apellidos");
        elementApellidos.appendChild(doc.createTextNode(apellidos));
        elementAlumno.appendChild(elementApellidos);

        Element elementDireccion = doc.createElement("Direccion");
        elementDireccion.appendChild(doc.createTextNode(direccion));
        elementAlumno.appendChild(elementDireccion);

        Element elementFnac = doc.createElement("Fnac");
        elementFnac.appendChild(doc.createTextNode(fnac));
        elementAlumno.appendChild(elementFnac);

        // Agregar el nuevo Alumno al nodo Instituto
        doc.getDocumentElement().appendChild(elementAlumno);

        System.out.println("Alumno añadido correctamente.");
    }
}
