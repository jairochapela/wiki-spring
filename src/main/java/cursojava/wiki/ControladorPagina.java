package cursojava.wiki;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorPagina {
    
    // Enlazamos con el modelo Documento
    Documento doc = new Documento();

    @GetMapping("/")
    public String mostrarPortada(Model modeloUI) {
        return "redirect:/Portada";
    }

    @GetMapping("/index")
    public String mostrarIndice(Model modeloUI) {
        File carpetaDatos = new File("./data");
        //File[] ficherosTxt = carpetaDatos.listFiles();
        String[] nombresFicheros = carpetaDatos.list();
        modeloUI.addAttribute("ficheros", nombresFicheros);
        return "index";
    }

    /**
     * Mostrar la página solicitada.
     */
    @GetMapping("/{nombre}")
    public String mostrarPagina(@PathVariable String nombre, Model modeloUI) {
        doc = Documento.cargar(nombre);
        doc.setContenido(ProcesadorMarcado.procesar(doc.getContenido()));
        modeloUI.addAttribute("doc", doc);
        return "pagina";
    }

    /**
     * Cargar el formulario de edición de página con los datos de la página.
     */
    @GetMapping("/editar/{nombre}")
    public String editarPagina(@PathVariable String nombre, Model modeloUI) {
        doc = Documento.cargar(nombre);
        modeloUI.addAttribute("doc", doc);
        doc.setTitulo(nombre);
        doc.setContenido(doc.getContenido());
        return "editar";
    }

    /**
     * Este método será invocado cuando se guarden los datos en el
     * formulario de edición de página.
     */
    @PostMapping("/editar/{nombre}")
    public String guardarPagina(@PathVariable String nombre, Documento documento, Model modeloUI) {
        modeloUI.addAttribute("doc", documento);
        documento.guardar();
        return "redirect:/" + nombre;
    }

}
