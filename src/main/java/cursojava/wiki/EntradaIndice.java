package cursojava.wiki;

import java.util.Date;

/**
 * Clase para contener información de las diferentes
 * entradas del índice de páginas.
 */
public class EntradaIndice {
    
    /**
     * Ruta del fichero para acceder a él
     */
    private String ruta;

    /**
     * Nombre del fichero (con extensión)
     */
    private String nombre;

    /**
     * Fecha de la última modificación del fichero
     */
    private Date fechaModificacion;
    
    public EntradaIndice(String ruta, String nombre, Date fechaModificacion) {
        this.ruta = ruta;
        this.nombre = nombre;
        this.fechaModificacion = fechaModificacion;
    }

    public String getRuta() {
        return ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    
}
