import java.util.Stack;

public class GeneradordeTriplos {
    public static String EstructuraTriplos(String expresion) {
        // Inicializa un StringBuilder para almacenar la tabla de tríplos
        StringBuilder tablaTriplos = new StringBuilder();

        // Crea una pila para los operadores
        Stack<Character> pilaOperadores = new Stack<>();

        // Inicializa un contador para generar nombres de temporales
        int contadorTemporales = 1;

        // Inicializa un contador para el número de línea en los tríplos
        int contadorLinea = 1;

        // Variable que almacena el último temporal utilizado
        String ultimoTemporal = "";

        // Almacena la variable a la izquierda del operador de asignación
        String variableIzquierda = "";

        // Agrega una cabecera a la tabla de tríplos
        tablaTriplos.append("Linea\tDato Objeto\tDato Fuente\tOperador\n");

        // Divide la expresión en tokens usando espacios como separadores
        String[] tokens = expresion.split(" ");

        // Recorre los tokens de la expresión
        for (String token : tokens) {
            if (!token.isEmpty()) {
                if (token.equals("=")) {
                    // Si el token es '=', es el operador de asignación
                    // Asigna la variable a la izquierda del operador
                    variableIzquierda = ultimoTemporal;
                    ultimoTemporal = "";
                } else if (esOperador(token)) {
                    // Si el token es un operador, maneja la prioridad de operadores y genera tríplos
                    char operador = token.charAt(0);
                    pilaOperadores.push(operador);
                } else {
                    // Si el token es una variable
                    if (ultimoTemporal.isEmpty()) {
                        // Si no hay un último temporal, se crea uno nuevo
                        tablaTriplos.append(contadorLinea + "\ttemp" + contadorTemporales + "\t" + token + "\t=\n");
                        ultimoTemporal = "temp" + contadorTemporales;
                        // Incrementa el contador de temporales
                        contadorTemporales++;
                    } else {
                        char operador = pilaOperadores.pop();
                        // Si ya se asignó un temporal previamente, se utiliza el mismo temporal
                        tablaTriplos.append(contadorLinea + "\t" + token + "\t" + ultimoTemporal + "\t" + operador + "\n");
                    }
                    contadorLinea++;
                }
            }
        }

        // Al final, asigna el resultado final a un temporal llamado "resultado"
        tablaTriplos.append(contadorLinea + "\tresultado\t" + ultimoTemporal + "\t=\n");

        // Si hay una variable a la izquierda del operador de asignación, agrégala al resultado
        if (!variableIzquierda.isEmpty()) {
            tablaTriplos.insert(0, "0\t" + variableIzquierda + "\t\t=\n");
        }

        return tablaTriplos.toString();
    }

    public static void main(String[] args) {
        // Define la expresión de entrada
        String expresionInfija = "ISC_321 = ISC_123 + ISC_546";

        // Llama a la función EstructuraTriplos para generar la tabla de tríplos
        String tablaTriplos = EstructuraTriplos(expresionInfija);

        // Imprime la tabla de tríplos
        System.out.println("Triplos:\n" + tablaTriplos);
    }

    // Función para obtener la precedencia de un operador
    private static int obtenerPrecedencia(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    // Función para verificar si un token es un operador
    private static boolean esOperador(String token) {
        return "+-*/^".contains(token);
    }
}
