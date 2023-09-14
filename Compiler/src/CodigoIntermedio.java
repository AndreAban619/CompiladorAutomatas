

import utils.Cuadruplo;
import utils.Simbolo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CodigoIntermedio {
 String[] guardasion  = new String[2000];
         String[] guardasion2  = new String[2000];
         String[] guardasion3  = new String[2000];
         int linea=1;
 int i =0;
    private class Expresion {
        public String id;
        public String expresion;

        private Expresion(String id, String expresion) {
            this.id = id;
            this.expresion = expresion;
          
        }
    }
  
    private class tmpValor {
        public String nombre;
        public double valor;

        private tmpValor(String nombre, double valor) {
            this.nombre = nombre;
            this.valor = valor;
           
        }
    }

    ArrayList<String> listaSimbolos;
    ArrayList<Expresion> listaExpresiones;
    ArrayList<Cuadruplo> listaCuadruplos;

    public CodigoIntermedio(ArrayList<String> listaSimbolos) {
        this.listaSimbolos = listaSimbolos;
        obtenerExpresiones();
        generarCuadruplos();
         
    }

    private void obtenerExpresiones() {
        System.out.printf("%15s %15s %15s %15s\n", "Operador", "Dato objeto", "Dato objeto2", "Dato Fuente");
        listaExpresiones = new ArrayList<>();
        for (String simbolo : listaSimbolos) {
            String expresion = simbolo;
            //System.out.println("expri= "+ expresion);
            // triplos: Expresión de 3 elementos
            if (expresion.split("\\s+").length >= 3) { //analiza y manda solo las expresiones, elimina los corchetes
                listaExpresiones.add(new Expresion(simbolo, expresion));
                //System.out.println("simbolo: "+ expresion);
                //System.out.println("expresion: "+ simbolo);
            }
        }
    }

    private void generarCuadruplos() {
        for (Expresion ex : listaExpresiones) {
            cabeceraImpresion(ex);
            listaCuadruplos = new ArrayList<>();
            ArrayList<String> elementos = new ArrayList<>(Arrays.asList(ex.expresion.split("\\s+")));
            int contadorTmp = 1;
            for (int i = 0; i < elementos.size(); i++) {
                if (elementos.get(i).equals("-")||elementos.get(i).equals("+")||elementos.get(i).equals("!=")||elementos.get(i).equals(">")||elementos.get(i).equals("<") ||elementos.get(i).equals("%") ||elementos.get(i).equals("/") || elementos.get(i).equals("*")|| elementos.get(i).equals("=")|| elementos.get(i).equals(">=")||
                       // elementos.get(i).equals("==")|| elementos.get(i).equals("<=")) {
                        elementos.get(i).equals("==")|| elementos.get(i).equals("<=")|| elementos.get(i).equals("&&")|| elementos.get(i).equals("||")) {
                    generarCuadruplo(elementos, i, contadorTmp);
                    contadorTmp++;
                    i = -1;
                } else if (!elementos.contains("/") && !elementos.contains("*")  && (elementos.get(i).equals("+") || elementos.get(i).equals("-"))) {
                    generarCuadruplo(elementos, i, contadorTmp);
                    
                    contadorTmp++;
                    i = -1;
                } else if (elementos.size() == 1) {
                    listaCuadruplos.add(new Cuadruplo(
                            ex.id + " :=",  // Operador
                            elementos.get(i),       // Operador1
                            "",            // Operador2
                            ""              // Resultado
                    ));
                }

            }
            cuerpoImpresion();
        }
        

    }

    private String generarTmp() {
        return new Random().nextFloat() >= 0.5 ? "PIMI" : "MARI";
    }

    private void generarCuadruplo(ArrayList<String> elementos, int i, int contador) {
        String tmp = generarTmp() + contador;
        listaCuadruplos.add(new Cuadruplo(
                elementos.get(i),       // Operador
                elementos.get(i - 1),   // Operador1
                elementos.get(i + 1),   // Operador2
                tmp                     // Resultado
        ));
        elementos.set(i, tmp);
        elementos.remove(i + 1);
        elementos.remove(i - 1);
 System.out.println("--------------------------------------------------------------------------");
    }
  
    private void cabeceraImpresion(Expresion ex){
     
      System.out.println(ex.id);
     
    
    }

    private void cuerpoImpresion() {
        for (Cuadruplo cuadruplo : listaCuadruplos) {
            System.out.format("%15s %15s %15s %15s \n",cuadruplo.getOperador() , cuadruplo.getOperador1(), cuadruplo.getOperador2(), cuadruplo.getResultado());
            String operador =cuadruplo.getOperador();
           //falta lineas y true false
            if(operador.equals("+")||operador.equals("-")||operador.equals("/")||operador.equals("*")||operador.equals("%"))
            {
             guardasion[i]="        "+cuadruplo.getResultado()+"        "+cuadruplo.getOperador2()+"        "+"=";
            
             guardasion2[i]="        "+cuadruplo.getOperador1()+"        "+cuadruplo.getResultado()+"        "+cuadruplo.getOperador();
           
            }else
            {
                if(operador.equals(">=")||operador.equals("<=")||operador.equals(">")||operador.equals("<")||operador.equals("!=")||operador.equals("=="))
                {
                     guardasion[i]="        "+cuadruplo.getResultado()+"        "+cuadruplo.getOperador2()+"        "+"=";
            
                     guardasion2[i]="        "+cuadruplo.getOperador1()+"        "+cuadruplo.getResultado()+"        "+cuadruplo.getOperador();
                   
                }else
                {
                    if(operador.equals("&&")||operador.equals("||"))
                    {
                    
                        guardasion[i]="        "+"TRUE"+"\n"+"        "+"False";
                        
                      
                       
                    }else
                        if(operador.equals("="))
                        {
                           guardasion[i]="        "+cuadruplo.getResultado()+"        "+cuadruplo.getOperador2()+"        "+cuadruplo.getOperador();
                         
                           guardasion2[i]="        "+cuadruplo.getOperador1()+"        "+cuadruplo.getResultado()+"        "+cuadruplo.getOperador();
                      
                        }else
                     guardasion[i]="        "+cuadruplo.getResultado()+"        "+cuadruplo.getOperador2()+"        "+"=";
                       
                    guardasion2[i]="        "+cuadruplo.getOperador1()+"        "+cuadruplo.getResultado()+"        "+"=";         
                
                }
                   
            
            }
            
            //if(operador.equals("<=")||operador.equals(">=")||operador.equals("<")||operador.equals(">"))
               // if(operador.equals("&&"))
               // {
                  //if(operador.equals("<=")||operador.equals(">=")||operador.equals("<")||operador.equals(">"))
                  //{
                      
                   //  guardasion[i]="        "+cuadruplo.getResultado()+"        "+cuadruplo.getOperador2()+"        "+cuadruplo.getOperador();
                    // guardasion2[i]="        "+cuadruplo.getOperador1()+"        "+cuadruplo.getResultado()+"        "+cuadruplo.getOperador(); 
                  //}
                
             
            //}
      // guardasion[i]="        "+cuadruplo.getOperador()+"        "+cuadruplo.getOperador1()+"        "+cuadruplo.getOperador2()+"        "+cuadruplo.getResultado();
           
         //   guardasion[i]="        "+cuadruplo.getOperador()+"        "+cuadruplo.getOperador1()+"        "+cuadruplo.getResultado();
        //System.out.println("123456"+ guardasion[i]);

        i++;
        }
        System.out.println("--------------------------------------------------------------------------");
            try {
            String ruta = "C:\\Users\\Andre aban\\Documents\\Tablatriplos.txt"; 
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
           //  bw.write("     "+"Operador"+"   "+"Dato objeto"+"   "+"Dato objeto2"+"   "+"Dato Fuente");
              bw.write("Linea"+"     "+"Dato Objeto"+"   "+"Dato Fuente"+"     "+"Operador");
             bw.write("\n");
             for (int j=0; j<i;j++){
                 int in=linea;
                 bw.write(in);            
            bw.write(guardasion[j]);
              linea++;
                bw.write("\n");
             bw.write("--------------------------------------------------------------------------");
            bw.write("\n");
                   in=linea;
               bw.write(in);
             bw.write(guardasion2[j]);
                  linea++;
                  
             bw.write("\n");
             bw.write("--------------------------------------------------------------------------");
            bw.write("\n");
                       }
           
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


