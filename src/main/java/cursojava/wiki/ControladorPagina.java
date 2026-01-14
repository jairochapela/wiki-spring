package cursojava.wiki;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorPagina {
    
    // Enlazamos con el modelo Documento
    Documento doc = new Documento();

    @GetMapping("/")
    public String mostrarPortada(Model modeloUI) {
        return "redirect:/pagina/Portada";
    }

    @GetMapping("/index")
    public String mostrarIndice(Model modeloUI, @RequestParam(required = false) String orden) {
        File carpetaDatos = new File("./data");
        File[] ficherosTxt = carpetaDatos.listFiles();
        List<EntradaIndice> entradas = new ArrayList<>();

        
        // Transformamos los objetos File en objetos EntradaIndice
        for (int i = 0; i < ficherosTxt.length; i++) {
            entradas.add(new EntradaIndice(
                ficherosTxt[i].getName().replaceFirst("\\.txt$", ""),
                ficherosTxt[i].getName(),
                new Date(ficherosTxt[i].lastModified())
            ));
        }

        // Si no se especifica orden, asumimos ordenar por nombre
        if (orden == null) {
            orden = "nombre";
        }

        switch (orden) {
            case "fecha":
                /*Collections.sort(entradas, new Comparator<EntradaIndice>() {
                    @Override
                    public int compare(EntradaIndice e1, EntradaIndice e2) {
                        return e2.getFechaModificacion().compareTo(e1.getFechaModificacion());
                    }
                }); */
                Collections.sort(entradas, (e1, e2) -> e2.getFechaModificacion().compareTo(e1.getFechaModificacion()));               
                break;

            case "nombre":
                /*Collections.sort(entradas, new Comparator<EntradaIndice>() {
                    @Override
                    public int compare(EntradaIndice e1, EntradaIndice e2) {
                        return e1.getNombre().compareToIgnoreCase(e2.getNombre());
                    }
                }); */
                Collections.sort(entradas, (e1, e2) -> e1.getNombre().compareToIgnoreCase(e2.getNombre()));
                break;
        }


        //String[] nombresFicheros = carpetaDatos.list();
        modeloUI.addAttribute("entradas", entradas);
        return "index";
    }

    /**
     * Mostrar la página solicitada.
     */
    @GetMapping("/pagina/{nombre}")
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
        return "redirect:/pagina/" + nombre;
    }

}
