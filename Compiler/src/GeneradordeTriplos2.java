import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneradordeTriplos2 {
    public static void main(String[] args) {
        // Expresión de entrada
        String expresionEntrada = "a > 10 && a < 20";

        // Genera la tabla de tríplos para la expresión de entrada
        String tablaTriplos = generarTablaTriplos(expresionEntrada);

        // Imprime la tabla de tríplos
        System.out.println("Tabla de Tríplos:\n" + tablaTriplos);
    }

    public static String generarTablaTriplos(String expresionEntrada) {
        // Inicializa un StringBuilder para almacenar la tabla de tríplos
        StringBuilder tablaTriplos = new StringBuilder();

        // Inicializa un contador para el número de línea en los tríplos
        int contadorLinea = 1;
        int contadortemporal=1;
        // Expresión regular para encontrar el patrón "4if4"
        Pattern patronIf = Pattern.compile("4if4\\s*\\((.*?)\\)\\s*4then4");

        // Variable que almacena el último temporal utilizado
        String ultimoTemporal = "temp" + (contadortemporal);

        // Agrega una cabecera a la tabla de tríplos
        tablaTriplos.append("Linea\tDato Objeto\tDato Fuente\tOperador\n");

        // Procesa la expresión de entrada
        String[] lineasDeEntrada = expresionEntrada.split("&&|\\|\\|");

        for (String linea : lineasDeEntrada) {
            // Elimina espacios en blanco
            linea = linea.trim();

            // Procesa cada línea de entrada de manera independiente
            if (linea.contains("&&") || linea.contains("||")) {
                String[] subexpresiones = linea.split("&&|\\|\\|");
                String expresionAntes = subexpresiones[0].trim();
                String expresionDespues = subexpresiones[1].trim();
                 
                // Agrega el operador && o || como operador en un nuevo tríplo
                String operador = (linea.contains("&&")) ? "&&" : "||";
             
            } else {
                // Modificamos esta sección para manejar los nuevos operadores
                String[] partes = linea.split("<|>|<=|>=|==|!=");
                if (partes.length == 2) {
                    String expresionIzquierda = partes[0].trim();
                    String operador = linea.substring(expresionIzquierda.length(), linea.length() - partes[1].length()).trim();
                    String expresionDerecha = partes[1].trim();

                    // Genera el tríplo para asignar la variable a un nuevo temporal
                    
                    tablaTriplos.append(contadorLinea + "\t" + ultimoTemporal + "\t" + expresionIzquierda + "\t=\n");
                    contadorLinea++;

                    // Genera el tríplo para la comparación entre el temporal y la expresión 
                    tablaTriplos.append(contadorLinea + "\t" + ultimoTemporal+ "\t" +  expresionDerecha  + "\t" + operador + "\n");
                    contadorLinea++;

                    // Almacena el resultado de la comparación en un nuevo temporal
                    String temporalResultado = "temp" + (contadorLinea);
                    tablaTriplos.append(contadorLinea + "\t" + "TR1" + "\t" + "TRUE" + "\t" + "LINEPAIR" + "\n");
                    contadorLinea++;
                    tablaTriplos.append(contadorLinea + "\t" + "TR1" + "\t" + "FALSE" + "\t" + "LINEPAIR" + "\n");
                    contadorLinea++;
                   
                }
            }
        }

        // Código posterior
        // Aquí puedes agregar más procesamiento si es necesario

        return tablaTriplos.toString();
    }
    // Resto del código
    // ...
}
