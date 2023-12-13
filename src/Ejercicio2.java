import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // Crear Nodo Raiz (Biblioteca)
            Element elementBiblioteca = doc.createElement("biblioteca");
            doc.appendChild(elementBiblioteca);

            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("=======Menú=======");
                System.out.println("1. Insertar un nuevo libro en la estructura DOM.");
                System.out.println("2. Visualizar el contenido de la biblioteca.");
                System.out.println("0. Salir.");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        insertarLibro(doc);
                        guardarLibro(doc);
                        break;
                    case 2:
                        visualizarLibro(doc);
                        break;
                    case 0:
                        System.out.println("Saliendo del programa.");
                        System.out.println("Hasta la próxima.");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                        break;
                }

            } while (opcion != 0);
            scanner.close();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static void guardarLibro(Document doc) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Source source = new DOMSource(doc);
            Result result = new StreamResult(new File("Biblioteca.xml"));
            transformer.transform(source, result);

            System.out.println("Cambios Guardados en el archivo.");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void insertarLibro(Document doc) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los datos del libro:");
        System.out.print("Indique el ISBN: ");
        String isbn = scanner.next();

        System.out.print("Indique el título del Libro: ");
        String titulo = scanner.next();

        System.out.print("Indique el autor del Libro: ");
        String autor = scanner.next();

        // Crear hijo ISBN
        Element elementIsbn = doc.createElement("ISBN");
        elementIsbn.appendChild(doc.createTextNode(isbn));

        // Crear hijo Titulo
        Element elementTitulo = doc.createElement("Titulo");
        elementTitulo.appendChild(doc.createTextNode(titulo));

        // Crear hijo Autor
        Element elementAutor = doc.createElement("Autor");
        elementAutor.appendChild(doc.createTextNode(autor));

        // Crear Nodo Libro y agregar los hijos
        Element elementLibro = doc.createElement("libro");
        elementLibro.appendChild(elementIsbn);
        elementLibro.appendChild(elementTitulo);
        elementLibro.appendChild(elementAutor);

        // Agregar el nuevo libro al nodo Biblioteca
        doc.getDocumentElement().appendChild(elementLibro);

        System.out.println("Libro insertado correctamente.");
    }

    private static void visualizarLibro(Document doc) {
        System.out.println("Contenido de la biblioteca:");
        System.out.println(doc.getDocumentElement().getNodeName());

        NodeList libros = doc.getElementsByTagName("libro");

        for (int i = 0; i < libros.getLength(); i++) {
            Element libro = (Element) libros.item(i);

            String isbn = libro.getElementsByTagName("ISBN").item(0).getTextContent();
            String titulo = libro.getElementsByTagName("Titulo").item(0).getTextContent();
            String autor = libro.getElementsByTagName("Autor").item(0).getTextContent();

            System.out.println("==============");
            System.out.println("ISBN: " + isbn);
            System.out.println("Título: " + titulo);
            System.out.println("Autor: " + autor);
            System.out.println("==============");
        }
    }
}
