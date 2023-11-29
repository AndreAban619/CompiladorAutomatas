
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Optimizacion extends JFrame {

    private JTextArea areaTextoOriginal;
    private JTextArea areaTextoOptimizado;

    public Optimizacion() {
        // Configurar la interfaz gráfica
        setTitle("Optimización de Código");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaTextoOriginal = new JTextArea();
        areaTextoOptimizado = new JTextArea();
        JButton botonOptimizar = new JButton("Optimizar");

        botonOptimizar.addActionListener(e -> optimizarCodigo());

        // Configurar el diseño
        setLayout(new GridLayout(1, 3));
        add(new JScrollPane(areaTextoOriginal));
        add(new JScrollPane(areaTextoOptimizado));
        add(botonOptimizar);

        // Inicializar el texto original
        StringBuilder textoOriginal = new StringBuilder();
        for (String codigo : getCodigos()) {
            textoOriginal.append(codigo).append("\n");
        }
        areaTextoOriginal.setText(textoOriginal.toString());

        setVisible(true);
    }

    public void optimizarCodigo() {
        List<String> codigos = getCodigos();
        List<String> antes = new ArrayList<>();
        List<String> despues = new ArrayList<>();
        Map<String, String> sustituciones = new HashMap<>();
        procesarCodigos(codigos, antes, despues, sustituciones);

        // Realizar sustituciones en las expresiones antes del igual
        for (int i = 0; i < antes.size(); i++) {
            String expresionAntes = antes.get(i);
            for (Map.Entry<String, String> sustitucion : sustituciones.entrySet()) {
                expresionAntes = expresionAntes.replaceAll("\\b" + sustitucion.getKey() + "\\b", sustitucion.getValue());
            }
            antes.set(i, expresionAntes);
        }

        // Actualizar el área de texto optimizado
        StringBuilder textoOptimizado = new StringBuilder();
        for (int i = 0; i < antes.size(); i++) {
            textoOptimizado.append(antes.get(i)).append(" = ").append(despues.get(i)).append("\n");
        }
        areaTextoOptimizado.setText(textoOptimizado.toString());
    }

    private List<String> getCodigos() {
        return List.of(
            "ISC_002 = 414 + 123.4884",
            "ISC_001 = ISC_001 * ISC_009",
            "ISC_009555  = ISC_009",
            "ISC_001 = ISC_002 - ISC_006",
            "ISC_003 = ISC_002 - ISC_006",
            "ISC_009 = ISC_003 + ISC_007",
            "ISC_009 = ISC_002 / ISC_009",
            "ISC_009 = \"ISC_002\" / ISC_009"
        );
    }

    public void procesarCodigos(List<String> codigos, List<String> antes, List<String> despues, Map<String, String> sustituciones) {
        Set<String> resultadosUnicos = new HashSet<>();

        for (String codigo : codigos) {
            // Dividir la línea en partes antes y después del igual
            String[] partes = codigo.split("=");

            // Eliminar espacios en blanco alrededor de las partes
            String parteAntes = partes[0].trim();
            String parteDespues = partes[1].trim();

            // Realizar sustituciones en la parte después del igual
            for (Map.Entry<String, String> sustitucion : sustituciones.entrySet()) {
                parteDespues = parteDespues.replaceAll("\\b" + sustitucion.getKey() + "\\b", sustitucion.getValue());
            }

            // Guardar las partes en las listas correspondientes
            antes.add(parteAntes);
            despues.add(parteDespues);

            // Verificar si la parte después del igual ya existe
            if (!resultadosUnicos.contains(parteDespues)) {
                resultadosUnicos.add(parteDespues);
            } else {
                // Eliminar la línea si la parte después del igual ya existe
                antes.remove(antes.size() - 1);
                despues.remove(despues.size() - 1);
            }

            // Realizar sustituciones en la parte antes del igual
            for (Map.Entry<String, String> sustitucion : sustituciones.entrySet()) {
                parteAntes = parteAntes.replaceAll("\\b" + sustitucion.getKey() + "\\b", sustitucion.getValue());
            }
            sustituciones.put(parteAntes, parteAntes); // Sustituir con la variable antes del igual
        }
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(Optimizacion::new);
    }
}
