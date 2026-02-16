package basicCalculator;

import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class Calculator {
    public static void main(String[] args) {
        String s = " 42 + 1 - 32 * 29 / 3 - 2";
        int val = calculate(s);
        System.out.println(val);
    }

    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] input = s.toCharArray();
        String currentNum = "";
        char opMode = '+';
        for (int i = 0; i < input.length; i++ ) {
            char ch = input[i];
            // Slurp Numbers
            if (Character.isDigit(ch)) {
                currentNum += ch;
            }
            // Have we finished reading a number?
            // Case 1:
            // Op A: we encountered an operator (+-*/)
            // Case 2: [currentNum has length]
            // We encountered a Space (either Op A or Op B) OR,
            // We encountered an EOS ( only opB)
            // number token ends when we encounter operator (Operand A)
            // or, when we encounter space or end of string given we are reading number
            // once we finish reading a number we always deferpush it or mul/div pop+eval/push it
            if (Set.of('+', '-', '*', '/').contains(ch)
                    || (!currentNum.isEmpty() && (ch == ' ' || i == input.length - 1))) {
                if (opMode == '+') {
                    stack.push(Integer.valueOf(currentNum));
                }
                if (opMode == '-') {
                    stack.push(-1 * Integer.valueOf(currentNum));
                }
                if (opMode == '*') {
                    stack.push(stack.pop() * Integer.valueOf(currentNum));
                }
                if (opMode == '/') {
                    stack.push(stack.pop() / Integer.valueOf(currentNum));
                }
                opMode = ch;
                currentNum = "";
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }


    /**
     * Push addition and subtractions in stack for deferred evaluation
     * Once we are in MUL / DIV mode eager execute them, by turning on Operation
     */
    private static int compute(String s) {
        char[] expr = s.toCharArray();
        Stack<String> stack = new Stack<>();
        String currentNum = "";
        char currentOp = '+';
        for (int i = 0; i < expr.length; i++) {
            char ch = expr[i];
            if (Character.isDigit(ch)) {
                currentNum += ch;
            }
            if (Set.of('+', '-', '*', '/').contains(ch) ||
                    (!currentNum.isEmpty() && (Character.isSpaceChar(ch) || i == (expr.length - 1)))) {

                if (Set.of('+', '-', '*', '/').contains(ch) ) System.out.println("Read Operator: " + ch);
                if (currentOp == '+') {
                    System.out.println("Read Operand : " + currentNum);
                    stack.push(currentNum);
                }
                if (currentOp == '-') {
                    System.out.println("Read Operand : " + currentNum);
                    stack.push("-" + currentNum);
                }
                if (currentOp == '*') {
                    System.out.println("Read Operand : " + currentNum);
                    String prevNum = stack.pop();
                    stack.push(prevNum + "*" + currentNum);
                }
                if (currentOp == '/') {
                    System.out.println("Read Operand : " + currentNum);
                    String prevNum = stack.pop();
                    stack.push(prevNum + "/" + currentNum);
                }
                currentOp = ch;
                currentNum = "";
            }
        }
        System.out.println(" Stack " + stack.stream().collect(Collectors.joining(", ")));
        return 0;
    }


    enum Operand {
        ADD("+"),
        SUB("-"),
        MUL("*"),
        DIV("/");

        private final String op;

        Operand(String op) {
            this.op = op;
        }

        Operand from(String str) {
            return switch (str) {
                case "+" -> ADD;
                case "-" -> SUB;
                case "*" -> MUL;
                case "/" -> DIV;
                default -> throw new IllegalArgumentException("unknown op " + str);
            };
        }
    }
}
