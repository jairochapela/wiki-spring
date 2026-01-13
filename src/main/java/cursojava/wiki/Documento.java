package cursojava.wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Documento {


    private String titulo;
    private String contenido;



    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public void guardar() {
        try {
            File file = new File("./data", titulo + ".txt");
            PrintWriter output = new PrintWriter(new FileWriter(file));
            output.println(contenido);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Documento cargar(String titulo) {
        Documento doc = new Documento();
        doc.setTitulo(titulo);
        try {
            File file = new File("./data", titulo + ".txt");
            BufferedReader input = new BufferedReader(new java.io.FileReader(file));
            while (input.ready()) {
                String linea = input.readLine();
                doc.setContenido((doc.getContenido() == null ? "" : doc.getContenido() + "\n") + linea);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            doc.setContenido("Esta página está vacía. ¡Edítala para añadir contenido!"); 
        }
        return doc;
    }

}
