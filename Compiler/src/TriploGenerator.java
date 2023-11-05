import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TriploGenerator {

    public static void main(String[] args) {
        String codigo = "if (ISC_001 == 434 && ISC_002 != 4554) then\n" +
                "ABIERTO\n" +
                "ISC_002 = 414 + 123.4884;\n" +
                "CERRADO";

        List<String> triplo = generarTriplo(codigo);
        for (String instruccion : triplo) {
            System.out.println(instruccion);
        }
    }

    public static List<String> generarTriplo(String codigo) {
        List<String> triplo = new ArrayList<>();
        String[] lineas = codigo.split("\n");
        String condicionActual = null;

        for (String linea : lineas) {
            if (linea.startsWith("if")) {
                condicionActual = obtenerCondicion(linea);
            } else if (linea.startsWith("ABIERTO")) {
                triplo.add("ETIQUETA ABIERTO");
            } else if (linea.startsWith("CERRADO")) {
                triplo.add("ETIQUETA CERRADO");
            } else if (linea.contains("=")) {
                String asignacion = obtenerAsignacion(linea);
                if (condicionActual != null) {
                    triplo.add(condicionActual + " THEN " + asignacion);
                    condicionActual = null;
                } else {
                    triplo.add(asignacion);
                }
            }
        }

        return triplo;
    }

    private static String obtenerCondicion(String linea) {
        // Analizar la condición y devolverla en el formato deseado
        Pattern pattern = Pattern.compile("if \\((.*?)\\)");
        Matcher matcher = pattern.matcher(linea);
        if (matcher.find()) {
            return "CONDICION " + matcher.group(1);
        }
        return "";
    }

    private static String obtenerAsignacion(String linea) {
        // Analizar la asignación y devolverla en el formato deseado
        String[] partes = linea.split("=");
        if (partes.length == 2) {
            String variable = partes[0].trim();
            String expresion = partes[1].trim();
            return variable + " = osaa " + expresion;
        }
        return "";
    }
}
