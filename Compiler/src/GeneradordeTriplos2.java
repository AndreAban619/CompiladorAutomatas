        // Procesa cada línea de entrada de manera independiente
            if (expresionIf.contains("&&") ) {
              // Modificamos esta sección para manejar los nuevos operadores
                partes = lineai.split("<|>|<=|>=|==|!=");
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
                partes = lineai.split("<|>|<=|>=|==|!=");
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