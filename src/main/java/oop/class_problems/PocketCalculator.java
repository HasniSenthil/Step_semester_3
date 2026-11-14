
package oop.class_problems;

import java.util.*;

public class PocketCalculator {

    // Check operator precedence
    static int precedence(char ch) {

        if (ch == '+' || ch == '-') {
            return 1;
        }

        if (ch == '*' || ch == '/') {
            return 2;
        }

        return 0;
    }

    // Convert infix to postfix
    static String toPostfix(String infix) {

        Stack<Character> stack = new Stack<>();
        String postfix = "";

        for (int i = 0; i < infix.length(); i++) {

            char ch = infix.charAt(i);

            // If operand, add to postfix
            if (Character.isDigit(ch)) {
                postfix += ch;
            }

            // If opening bracket, push
            else if (ch == '(') {
                stack.push(ch);
            }

            // If closing bracket, pop until '('
            else if (ch == ')') {

                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix += stack.pop();
                }

                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }

            // If operator
            else {

                while (!stack.isEmpty()
                        && stack.peek() != '('
                        && precedence(stack.peek()) >= precedence(ch)) {

                    postfix += stack.pop();
                }

                stack.push(ch);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        return postfix;
    }

    // Evaluate postfix expression
    static int evaluate(String postfix) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {

            char ch = postfix.charAt(i);

            // If operand, push
            if (Character.isDigit(ch)) {

                stack.push(ch - '0');
            }

            // If operator, perform calculation
            else {

                int b = stack.pop();
                int a = stack.pop();

                switch (ch) {

                    case '+':
                        stack.push(a + b);
                        break;

                    case '-':
                        stack.push(a - b);
                        break;

                    case '*':
                        stack.push(a * b);
                        break;

                    case '/':
                        if (b == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        stack.push(a / b);
                        break;
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            String infix = sc.nextLine().trim();

            if (infix.isEmpty()) {
                continue;
            }

            String postfix = toPostfix(infix);

            int result = evaluate(postfix);

            System.out.println(postfix + " = " + result);
        }

        sc.close();
    }
}
