
import jflex.exceptions.SilentExit;

public class ExecuteJFlex {

    public static void main(String omega[]) {
        String lexerFile = System.getProperty("user.dir") + "/src/Lexer.flex";        
        try {
            jflex.Main.generate(new String[]{lexerFile});
        } catch (SilentExit ex) {
            System.out.println("Error al compilar/generar el archivo flex: " + ex);
        }
    }
}