
import java.util.Stack;

/**
 * Utility class to handle different notations (Infix, Postfix) and perform evaluations.
 */
public class Notation {

    /**
     * Converts an infix expression to a postfix expression.
     * 
     * @param infix The infix expression string.
     * @return The postfix representation of the provided infix expression.
     * @throws InvalidNotationFormatException If the infix expression is invalid.
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (char ch : infix.toCharArray()) {
            if (ch == ' ') continue;  // Skip spaces
            if (Character.isDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (stack.isEmpty() || stack.pop() != '(') {
                    throw new InvalidNotationFormatException("Mismatched parentheses");
                }
            } else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                throw new InvalidNotationFormatException("Mismatched parentheses");
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    /**
     * Converts a postfix expression to an infix expression.
     * 
     * @param postfix The postfix expression string.
     * @return The infix representation of the provided postfix expression.
     * @throws InvalidNotationFormatException If the postfix expression is invalid.
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
        Stack<String> stack = new Stack<>();

        for (char ch : postfix.toCharArray()) {
            if (Character.isDigit(ch)) {
                stack.push(String.valueOf(ch));
            } else {
                if (stack.size() < 2) {
                    throw new InvalidNotationFormatException("Invalid postfix expression");
                }
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                String expression = "(" + operand1 + ch + operand2 + ")";
                stack.push(expression);
            }
        }

        if (stack.size() != 1) {
            throw new InvalidNotationFormatException("Invalid postfix expression");
        }

        return stack.pop();
    }

    /**
     * Evaluates a postfix expression and returns its result.
     * 
     * @param postfix The postfix expression string.
     * @return The result of evaluating the provided postfix expression.
     * @throws InvalidNotationFormatException If the postfix expression is invalid.
     */
    public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException {
        Stack<Double> stack = new Stack<>();

        for (char ch : postfix.toCharArray()) {
            if (Character.isDigit(ch)) {
                stack.push((double) (ch - '0'));
            } else {
                if (stack.size() < 2) {
                    throw new InvalidNotationFormatException("Invalid postfix expression");
                }
                double operand2 = stack.pop();
                double operand1 = stack.pop();

                switch (ch) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        if (operand2 == 0) throw new ArithmeticException("Division by zero");
                        stack.push(operand1 / operand2);
                        break;
                    default:
                        throw new InvalidNotationFormatException("Invalid operator: " + ch);
                }
            }
        }

        if (stack.size() != 1) {
            throw new InvalidNotationFormatException("Invalid postfix expression");
        }

        return stack.pop();
    }

    /**
     * Determines the precedence level of an operator.
     * 
     * @param operator The operator character.
     * @return The precedence level of the operator. Higher number means higher precedence.
     */
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
}
