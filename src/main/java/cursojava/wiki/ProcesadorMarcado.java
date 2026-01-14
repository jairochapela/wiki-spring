package cursojava.wiki;

public class ProcesadorMarcado {

    /**
     * Procesa el texto de entrada y devuelve el texto marcado.
     * Las marcas son:
     * - Encabezados: líneas que comienzan con '#', '##', '###' se convierten en encabezados HTML <h1>, <h2>, <h3>.
     * - Negritas: texto entre '**' se convierte en <strong>.
     * - Cursivas: texto entre '*' se convierte en <em>.
     * - Enlaces: texto entre '[[' y ']]' se convierte en enlaces HTML <a href="/...">...</a>.
     * @param texto El texto a procesar
     * @return El texto procesado con las marcas correspondientes
     */
    public static String procesar(String texto) {

        // Negritas y cursivas
        texto = texto.replaceAll("\\*\\*(.+)\\*\\*", "<strong>$1</strong>");
        texto = texto.replaceAll("\\*(.+)\\*", "<em>$1</em>");

        // Enlaces a páginas [[pagina]] -> <a href="/pagina">pagina</a>
        texto = texto.replaceAll("\\[\\[(.+)\\]\\]", "<a href=\"/pagina/$1\">$1</a>");

        // Separamos el texto original en líneas, dividiendo por \n
        String[] lineas = texto.split("\n");
        // Creamos una variable para ir almacenando cada linea una vez sea procesada
        String resultado = "";

        // Línea a línea, hacemos las sustituciones pertinentes
        for (String linea: lineas) {
            linea = linea.replaceAll("^###(.+)$", "<h3>$1</h3>");
            linea = linea.replaceAll("^##(.+)$", "<h2>$1</h2>");
            linea = linea.replaceAll("^#(.+)$", "<h1>$1</h1>");
            resultado += linea + "\n";
        }

        return resultado;
    }

}
