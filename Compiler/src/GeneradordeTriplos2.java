       ////////
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
              
                  // Procesa cada línea de entrada de manera independiente
            if ( expresionIf.contains("||")) {
                System.out.println("entro or");
        
            } else {
                  //System.out.println("entro and");
                // Modificamos esta sección para manejar los nuevos operadores
                  int cont=0;
                String[] partes = lineai.split("<|>|<=|>=|==|!=");
                if (partes.length == 2) {
                    if(cont==0){
                        empez=empez-2;
                    }
                    String expresionIzquierda = partes[0].trim();
                    String opeif2 = lineai.substring(expresionIzquierda.length(), lineai.length() - partes[1].length()).trim();
                    String expresionDerecha = partes[1].trim();

                    // Genera el tríplo para asignar la variable a un nuevo temporal
                    
                    tablaTriplos.append(contadorLinea + "\ttemp" + contadorTemporales + "\t" + expresionIzquierda + "\t=\n");
                    contadorLinea++;

                    // Genera el tríplo para la comparación entre el temporal y la expresión 
                    tablaTriplos.append(contadorLinea  + "\ttemp" + contadorTemporales+ "\t" +  expresionDerecha  + "\t" + opeif2 + "\n");
                    contadorLinea++;
                    tablaTriplos.append(contadorLinea + "\t" + "TR1" + "\t" + "TRUE" + "\t" + empez + "\n");
                    contadorLinea++;
                    tablaTriplos.append(contadorLinea + "\t" + "TR1" + "\t" + "FALSE" + "\t" + finif  + "\n");
                    contadorLinea++;
                    cont++;
                  
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

import java.util.Stack;
import java.util.regex.Matcher;

