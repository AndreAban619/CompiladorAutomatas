import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneradordeTriplos {
     private static int finif = 0;
    private static int empez = 0;
    public static String EstructuraTriplos(String[] lineasDeEntrada) {
      
        // Inicializa un StringBuilder para almacenar la tabla de tríplos
        StringBuilder tablaTriplos = new StringBuilder();

        // Inicializa un contador para generar nombres de temporales
        int contadorTemporales = 1;

        // Inicializa un contador para el número de línea en los tríplos
        int contadorLinea = 1;
        // Expresión regular para encontrar el patrón "4if4"
        Pattern patronIf = Pattern.compile("4if4\\s*\\((.*?)\\)\\s*4then4");
        
        // Variable que almacena el último temporal utilizado
        String ultimoTemporal = "";
    
        // Agrega una cabecera a la tabla de tríplos
        tablaTriplos.append("Linea\tDato Objeto\tDato Fuente\tOperador\n");
          for (String linea : lineasDeEntrada) {
            
                               // Verifica si la línea contiene "}" y guarda el valor de contadorLinea
              
                 if (linea.contains("}")) {
                 finif = contadorLinea;
                System.out.println("Línea donde termina: " + empez );
            }
            
              if (linea.contains("{")) {
                empez = contadorLinea;
                System.out.println("Línea donde empieza: " + finif);
            }

            // Inicializa una pila para operadores para cada línea de entrada
            Stack<Character> pilaOperadores = new Stack<>();
                String expresionIf ="";
            Matcher matcherIf = patronIf.matcher(linea);
            if (matcherIf.find()) {
                // Obtiene la expresión dentro del paréntesis
                 expresionIf = matcherIf.group(1);
                //System.out.println("holaaa"+expresionIf);
            }
            //revisar la entrada del if
            String[] lineaif = expresionIf.split("&&|\\|\\|");
            for (String lineai : lineaif) {
                //borramos espacios en blanco
                lineai = lineai.trim();   
                  // Procesa cada línea de entrada de manera independiente
            if (lineai.contains("&&") || lineai.contains("||")) {
                String[] subexpresiones = lineai.split("&&|\\|\\|");
                String expresionAntes = subexpresiones[0].trim();
                String expresionDespues = subexpresiones[1].trim();
                 
                // Agrega el operador && o || como operador en un nuevo tríplo
                String opeif = (lineai.contains("&&")) ? "&&" : "||";
             
            } else {
                // Modificamos esta sección para manejar los nuevos operadores
                String[] partes = lineai.split("<|>|<=|>=|==|!=");
                if (partes.length == 2) {
                    
                    String expresionIzquierda = partes[0].trim();
                    String opeif2 = lineai.substring(expresionIzquierda.length(), lineai.length() - partes[1].length()).trim();
                    String expresionDerecha = partes[1].trim();

                    // Genera el tríplo para asignar la variable a un nuevo temporal
                    
                    contadorLinea++;

                    // Genera el tríplo para la comparación entre el temporal y la expresión 
                    contadorLinea++;
                    contadorLinea++;
                     contadorLinea++;
                   
                }
            }
            }
            
            /////////////
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
                               
                                ultimoTemporal = "temp" + contadorTemporales;
                                contadorTemporales++;
                                 contadorLinea++;
                            } else {
                                if (!pilaOperadores.isEmpty()) {
                                    char operador = pilaOperadores.pop();

                                    // Corrección: Añade el operador "%" a los operadores existentes
                                    if (operador == '+' || operador == '-' || operador == '*' || operador == '/'
                                            || operador == '^' || operador == '%') {
                                        
                                    } else {
                                        
                                    }
                                } else {
                                
                                }
                                contadorLinea++;
                            }
                        }
                    }
                }

                // Corrección: Agrega las variables antes del "=" al final de la expresión
                if (!antesDelIgual.isEmpty()) {
                    contadorLinea++;

                }
            }

          }
          contadorLinea = 1;
        // Procesa cada línea de entrada de manera independiente
        for (String linea : lineasDeEntrada) {
            
            
       
            // Inicializa una pila para operadores para cada línea de entrada
            Stack<Character> pilaOperadores = new Stack<>();
                String expresionIf ="";
            Matcher matcherIf = patronIf.matcher(linea);
            if (matcherIf.find()) {
                // Obtiene la expresión dentro del paréntesis
                 expresionIf = matcherIf.group(1);
                //System.out.println("holaaa"+expresionIf);
            }
            //revisar la entrada del if
            String[] lineaif = expresionIf.split("&&|\\|\\|");
            for (String lineai : lineaif) {
                //borramos espacios en blanco
                lineai = lineai.trim();

                // si es && deja el triplo como esta
          
                  // Procesa cada línea de entrada de manera independiente
            if (expresionIf.contains("&&") ) {
              // Modificamos esta sección para manejar los nuevos operadores
                String[] partes = lineai.split("<|>|<=|>=|==|!=");
                if (partes.length == 2) {
                    
                    String expresionIzquierda = partes[0].trim();
                    String opeif2 = lineai.substring(expresionIzquierda.length(), lineai.length() - partes[1].length()).trim();
                    String expresionDerecha = partes[1].trim();

                    // Genera el tríplo para asignar la variable a un nuevo temporal
                    
                    tablaTriplos.append(contadorLinea + "\ttemp" + contadorTemporales + "\t" + expresionIzquierda + "\t=\n");
                    contadorLinea++;
                   
                    // Genera el tríplo para la comparación entre el temporal y la expresión 
                    tablaTriplos.append(contadorLinea  + "\ttemp" + contadorTemporales+ "\t" +  expresionDerecha  + "\t" + opeif2 + "\n");
                    contadorLinea++;
                     int lineaand= contadorLinea +2;
                    tablaTriplos.append(contadorLinea + "\t" + "TR1" + "\t" + "TRUE" + "\t" + lineaand + "\n");
                    contadorLinea++;
                    tablaTriplos.append(contadorLinea + "\t" + "TR1" + "\t" + "FALSE" + "\t" + finif  + "\n");
                    contadorLinea++;
                   
                }
            }
            if (expresionIf.contains("||") ) {
                 System.out.println("holaaa"+expresionIf);
              // Modificamos esta sección para manejar los nuevos operadores
                String[] partes = lineai.split("<|>|<=|>=|==|!=");
                if (partes.length == 2) {
                    
                    String expresionIzquierda = partes[0].trim();
                    String opeif2 = lineai.substring(expresionIzquierda.length(), lineai.length() - partes[1].length()).trim();
                    String expresionDerecha = partes[1].trim();

                    // Genera el tríplo para asignar la variable a un nuevo temporal
                    
                    tablaTriplos.append(contadorLinea + "\ttemp" + contadorTemporales + "\t" + expresionIzquierda + "\t=\n");
                    contadorLinea++;

                    // Genera el tríplo para la comparación entre el temporal y la expresión 
                    tablaTriplos.append(contadorLinea  + "\ttemp" + contadorTemporales+ "\t" +  expresionDerecha  + "\t" + opeif2 + "\n");
                    contadorLinea++;
                    tablaTriplos.append(contadorLinea + "\t" + "TR1" + "\t" + "ORR" + "\t" + empez + "\n");
                    contadorLinea++;
                    tablaTriplos.append(contadorLinea + "\t" + "TR1" + "\t" + "FALSE" + "\t" + finif  + "\n");
                    contadorLinea++;
                   
                }
            }
            }
            
            /////////////
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
                                 contadorLinea++;
                            } else {
                                if (!pilaOperadores.isEmpty()) {
                                    char operador = pilaOperadores.pop();

                                    // Corrección: Añade el operador "%" a los operadores existentes
                                    if (operador == '+' || operador == '-' || operador == '*' || operador == '/'
                                            || operador == '^' || operador == '%') {
                                        tablaTriplos.append(contadorLinea + "\t" + ultimoTemporal + "\t" + token + "\t" + operador + "\n");
                                    } else {
                                        tablaTriplos.append(contadorLinea + "\t" + ultimoTemporal + "\t" + token + "\t=\n");
                                    }
                                } else {
                                    tablaTriplos.append(contadorLinea + "\t" + ultimoTemporal + "\t" + token + "\t=\n");
                                }
                                contadorLinea++;
                            }
                        }
                    }
                }

                // Corrección: Agrega las variables antes del "=" al final de la expresión
                if (!antesDelIgual.isEmpty()) {
                    tablaTriplos.append(contadorLinea + "\t" + antesDelIgual + "\t" + ultimoTemporal + "\t=\n");
                    contadorLinea++;

                }
            }

         
        }

        return tablaTriplos.toString();
    }

    public static void main(String[] lineasSinPrimerasTres) {
        // Llama a la función EstructuraTriplos para generar la tabla de tríplos
        String tablaTriplos = EstructuraTriplos(lineasSinPrimerasTres);

        // Imprime la tabla de tríplos
        System.out.println("Triplos:\n" + tablaTriplos);
    }

    private static boolean esOperador(String token) {
        return "+-*/^%".contains(token);
    }

}
