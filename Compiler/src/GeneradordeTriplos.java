import java.util.Stack;

public class GeneradordeTriplos {
    public static String EstructuraTriplos(String[] lineasDeEntrada) {
        // Inicializa un StringBuilder para almacenar la tabla de tríplos
        StringBuilder tablaTriplos = new StringBuilder();

        // Inicializa un contador para generar nombres de temporales
        int contadorTemporales = 1;

        // Inicializa un contador para el número de línea en los tríplos
        int contadorLinea = 1;

        // Variable que almacena el último temporal utilizado
        String ultimoTemporal = "";

        // Agrega una cabecera a la tabla de tríplos
        tablaTriplos.append("Linea\tDato Objeto\tDato Fuente\tOperador\n");

        // Procesa cada línea de entrada de manera independiente
        for (String linea : lineasDeEntrada) {
            // Inicializa una pila para operadores para cada línea de entrada
            Stack<Character> pilaOperadores = new Stack<>();

            // Divide la línea en dos partes usando el "=" como separador
            String[] partes = linea.split("=");
            if (partes.length == 2) {
                String antesDelIgual = partes[0].trim();
                String despuesDelIgual = partes[1].trim();

                // Procesa la parte antes del "="
                String[] tokens = antesDelIgual.split(" ");
                // Procesa la parte después del "="
                tokens = despuesDelIgual.split(" ");
                //antes del igual
                for (int i = 0; i < tokens.length; i++) {
                    String token = tokens[i];
                    if (!token.isEmpty()) {
                        if (esOperador(token)) {
                            char operador = token.charAt(0);
                            pilaOperadores.push(operador);
                        } else {
                            if (ultimoTemporal.isEmpty()) {
                                tablaTriplos.append(contadorLinea + "\ttemp" + contadorTemporales + "\t" + token + "\t=\n");
                                ultimoTemporal = "temp" + contadorTemporales;
                                contadorTemporales++;
                            } else {
                                if (!pilaOperadores.isEmpty()) {
                                    char operador = pilaOperadores.pop();
                                    // Corrección: Añade el operador "%" a los operadores existentes
                                    if (operador == '+' || operador == '-' || operador == '*' || operador == '/' || operador == '^' || operador == '%') {
                                        tablaTriplos.append(contadorLinea + "\t" + ultimoTemporal + "\t" + token + "\t" + operador + "\n");
                                    } else {
                                        tablaTriplos.append(contadorLinea + "\t" + ultimoTemporal + "\t" + token + "\t=\n");
                                    }
                                } else {
                                    tablaTriplos.append(contadorLinea + "\t" + ultimoTemporal + "\t" + token + "\t=\n");
                                }
                            }
                            contadorLinea++;
                        }
                    }
                }

                // Corrección: Agrega las variables antes del "=" al final de la expresión
                if (!antesDelIgual.isEmpty()) {
                    tablaTriplos.append(contadorLinea + "\t" + antesDelIgual + "\t" + ultimoTemporal  + "\t=\n");
                    contadorLinea++;
                }
            }
        }

        return tablaTriplos.toString();
    }

    public static void main(String[] lineasDeEntrada) {
  

        // Llama a la función EstructuraTriplos para generar la tabla de tríplos
        String tablaTriplos = EstructuraTriplos(lineasDeEntrada);

        // Imprime la tabla de tríplos
        System.out.println("Triplos:\n" + tablaTriplos);
    }

    private static boolean esOperador(String token) {
        return "+-*/^%".contains(token);
    }
}
