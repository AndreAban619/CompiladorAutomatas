import java.util.Stack;

public class GeneradordeTriplos {
    public static String EstructuraTriplos(String expression) {
        StringBuilder rpn = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();
        int tempCount = 1; // Contador para generar nombres de temporales numéricos
        int letterTempCount = 1; // Contador para generar nombres de temporales de letras
        int lineCount = 1; // Contador para el número de línea en los tríplos

        rpn.append("Linea\tDato Objeto\tDato Fuente\tOperador\n");

        for (char token : expression.toCharArray()) {
            if (Character.isLetterOrDigit(token)) {
                if (Character.isDigit(token)) {
                    // Cuando el token es un dígito, se genera un tríplo para asignar el valor a un temporal numérico.
                    rpn.append(lineCount + "\ttemp" + tempCount + "\t" + token + "\t=\n");
                    tempCount++;
                } else {
                    // Cuando el token es una letra, se genera un tríplo para asignar el valor a un temporal de letras.
                    rpn.append(lineCount + "\ttemp" + letterTempCount + "\t" + token + "\t=\n");
                    letterTempCount++;
                }
                lineCount++;
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && hasHigherPrecedence(operatorStack.peek(), token)) {
                    // Cuando se encuentra un operador, se verifica la precedencia con los operadores en la pila
                    // y se generan tríplos de operación.
                    char operator = operatorStack.pop();
                    rpn.append(lineCount + "\ttemp" + tempCount + "\ttemp" + (tempCount - 2) + "\t" + operator + "\n");
                    tempCount++;
                    lineCount++;
                }
                operatorStack.push(token); // Se agrega el operador a la pila.
            } else if (token == '(') {
                operatorStack.push(token); // Si es un paréntesis de apertura, se agrega a la pila.
            } else if (token == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    // Cuando se encuentra un paréntesis de cierre, se generan tríplos de operación
                    // hasta que se empareje con el paréntesis de apertura.
                    char operator = operatorStack.pop();
                    rpn.append(lineCount + "\ttemp" + tempCount + "\ttemp" + (tempCount - 2) + "\t" + operator + "\n");
                    tempCount++;
                    lineCount++;
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // Se quita el paréntesis de apertura de la pila.
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            // Al final del análisis de la expresión, se generan tríplos para los operadores restantes en la pila.
            char operator = operatorStack.pop();
            rpn.append(lineCount + "\ttemp" + tempCount + "\ttemp" + (tempCount - 2) + "\t" + operator + "\n");
            tempCount++;
            lineCount++;
        }

        return rpn.toString();
    }

    private static boolean isOperator(char c) {
        // Verifica si un carácter es un operador matemático.
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int getPrecedence(char c) {
        // Obtiene la precedencia de un operador.
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

    private static boolean hasHigherPrecedence(char op1, char op2) {
        // Compara la precedencia de dos operadores.
        return getPrecedence(op1) >= getPrecedence(op2);
    }

    public static void main(String infixExpression) {
        //String infixExpression = "x + a - 5";
        //String rpnExpression = infixToRPNWithTriples(infixExpression);
        EstructuraTriplos(infixExpression);
        //.out.println("Triplos:\n" + rpnExpression);
    }
}
