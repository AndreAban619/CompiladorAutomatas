import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Optimizacion extends JFrame {

    private JTextArea areaTextoOriginal;
    private JTextArea areaTextoOptimizado;

    public Optimizacion(List<String> codigos) {
        // Configurar la interfaz gráfica
        setTitle("Optimización de Código");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaTextoOriginal = new JTextArea();
        areaTextoOptimizado = new JTextArea();
        JButton botonOptimizar = new JButton("Optimizar");

        botonOptimizar.addActionListener(e -> optimizarCodigo(codigos));

        // Configurar el diseño
        setLayout(new GridLayout(1, 3));
        add(new JScrollPane(areaTextoOriginal));
        add(new JScrollPane(areaTextoOptimizado));
        add(botonOptimizar);

        // Inicializar el texto original
        StringBuilder textoOriginal = new StringBuilder();
        for (String codigo : getCodigos(codigos)) {
            textoOriginal.append(codigo).append("\n");
        }
        areaTextoOriginal.setText(textoOriginal.toString());

        setVisible(true);
    }

    public String optimizarCodigo(List<String> codigoos) {
        List<String> codigos = getCodigos(codigoos);
        List<String> antes = new ArrayList<>();
        List<String> despues = new ArrayList<>();
        Map<String, String> sustituciones = new HashMap<>();
        procesarCodigos(codigos, antes, despues, sustituciones);

        // Realizar sustituciones en las expresiones antes del igual
        for (int i = 0; i < antes.size(); i++) {
            String expresionAntes = antes.get(i);
            for (Map.Entry<String, String> sustitucion : sustituciones.entrySet()) {
                expresionAntes = expresionAntes.replace(sustitucion.getKey(), sustitucion.getValue());
            }
            antes.set(i, expresionAntes);
        }

        // Obtener todo el texto del área de texto optimizado
        StringBuilder textoOptimizado = new StringBuilder();
        for (int i = 0; i < antes.size(); i++) {
            if (antes.get(i).contains("{") || antes.get(i).contains("}")) {
                textoOptimizado.append(antes.get(i)).append(" ").append(despues.get(i)).append("\n");
            } else {
                textoOptimizado.append(antes.get(i)).append(" = ").append(despues.get(i)).append("\n");
            }
            
        }
        
        // Imprimir todo el texto optimizado en la consola
        //System.out.println("Texto Optimizado:");
       // System.out.println(textoOptimizado.toString());
 
        // Actualizar el área de texto optimizado
        areaTextoOptimizado.setText(textoOptimizado.toString());
        return textoOptimizado.toString();
    }

    public void procesarCodigos(List<String> codigos, List<String> antes, List<String> despues, Map<String, String> sustituciones) {
        Set<String> resultadosUnicos = new HashSet<>();

        for (String codigo : codigos) {
            // Si la línea contiene '{' o '}', la agregamos tal como está
            if (codigo.contains("{") || codigo.contains("}")) {
                antes.add(codigo.trim());
                despues.add(" ");
                continue;
            }

            // Dividir la línea en partes antes y después del igual
            String[] partes = codigo.split("=");

            // Asegurarse de que haya al menos dos elementos en el array 'partes'
            if (partes.length >= 2) {
                // Eliminar espacios en blanco alrededor de las partes
                String parteAntes = partes[0].trim();
                String parteDespues = partes[1].trim();

                // Realizar sustituciones en la parte después del igual
                for (Map.Entry<String, String> sustitucion : sustituciones.entrySet()) {
                    parteDespues = parteDespues.replace(sustitucion.getKey(), sustitucion.getValue());
                }

                // Guardar las partes en las listas correspondientes
                antes.add(parteAntes);
                despues.add(parteDespues);

                // Verificar si la parte después del igual ya existe
                if (resultadosUnicos.contains(parteDespues)) {
                    // Guardar la expresión original antes de eliminar la línea
                    String expresionOriginalAntes = antes.get(antes.size() - 2);
                    // Eliminar la línea si la parte después del igual ya existe
                    antes.remove(antes.size() - 1);
                    despues.remove(despues.size() - 1);
                    // Imprimir las dos expresiones aritméticas que coincidieron
                    System.out.println("Expresión original: " + expresionOriginalAntes + " = " + parteDespues);
                    System.out.println("Expresión duplicada: " + parteAntes + " = " + parteDespues);
                    sustituciones.put(parteAntes, expresionOriginalAntes); // Sustituir con la variable antes del igual
                } else {
                    resultadosUnicos.add(parteDespues);
                }
            } else {
                // Manejar la situación en la que no hay suficientes elementos en el array 'partes'
                System.err.println("---SALTO---");
            }
        }
    }

    private List<String> getCodigos(List<String> codigo) {
        return codigo;
    }

    public static void main(String[] args) {
        // Puedes agregar código para probar la aplicación aquí si lo deseas
        // Ejemplo:
        // List<String> codigosDePrueba = Arrays.asList("a = 5", "b = a + 3", "c = b * 2", "{", "int x = 10;", "}", "d = c - 1");
        // Optimizacion optimizacion = new Optimizacion(codigosDePrueba);
    }
}
