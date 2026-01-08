package cursojava.wiki;

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
        modeloUI.addAttribute("doc", doc);
        doc.setTitulo("Portada");
        doc.setContenido("Bienvenido a mi wiki personal.");
        return "pagina";
    }

    @GetMapping("/{nombre}")
    public String mostrarPagina(@PathVariable String nombre, Model modeloUI) {
        doc = Documento.cargar(nombre);
        modeloUI.addAttribute("doc", doc);
        return "pagina";
    }

    @GetMapping("/editar/{nombre}")
    public String editarPagina(@PathVariable String nombre, Model modeloUI) {
        doc = Documento.cargar(nombre);
        modeloUI.addAttribute("doc", doc);
        doc.setTitulo(nombre);
        doc.setContenido(doc.getContenido());
        return "editar";
    }

    @PostMapping("/editar/{nombre}")
    public String guardarPagina(@PathVariable String nombre, Documento documento, Model modeloUI) {
        modeloUI.addAttribute("doc", documento);
        documento.guardar();
        return "pagina";
    }

}
