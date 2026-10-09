package ru.iqwanoino.elicia;

import java.util.Stack;

public class MathHelper {

    public static double evaluateMathExpression(String expression) {
        try {
            return evaluate(expression);
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    private static double evaluate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        Stack<String> functions = new Stack<>();
        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                numbers.push(Double.parseDouble(num.toString()));
                continue;
            }
            if (c == '(') {
                operators.push(c);
                i++;
                continue;
            }
            if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    compute(numbers, operators);
                }
                if (!operators.isEmpty()) operators.pop();
                if (!functions.isEmpty()) {
                    String func = functions.pop();
                    numbers.push(applyFunction(func, numbers.pop()));
                }
                i++;
                continue;
            }
            if (Character.isLetter(c)) {
                StringBuilder func = new StringBuilder();
                while (i < expression.length() && Character.isLetter(expression.charAt(i))) {
                    func.append(expression.charAt(i));
                    i++;
                }
                String f = func.toString().toLowerCase();
                if (f.equals("pi")) {
                    numbers.push(Math.PI);
                } else if (f.equals("e")) {
                    numbers.push(Math.E);
                } else {
                    functions.push(f);
                }
                continue;
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                if (c == '-' && (i == 0 || expression.charAt(i-1) == '(')) {
                    StringBuilder num = new StringBuilder("-");
                    i++;
                    while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        num.append(expression.charAt(i));
                        i++;
                    }
                    numbers.push(Double.parseDouble(num.toString()));
                    continue;
                }
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    compute(numbers, operators);
                }
                operators.push(c);
                i++;
                continue;
            }
            i++;
        }
        while (!operators.isEmpty()) {
            compute(numbers, operators);
        }
        return numbers.pop();
    }

    private static int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        if (op == '^') return 3;
        return 0;
    }

    private static void compute(Stack<Double> numbers, Stack<Character> operators) {
        if (numbers.size() < 2 || operators.isEmpty()) return;
        double b = numbers.pop();
        double a = numbers.pop();
        char op = operators.pop();
        numbers.push(applyOperation(a, b, op));
    }

    private static double applyOperation(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return (b != 0) ? a / b : Double.NaN;
            case '^': return Math.pow(a, b);
            default: return 0;
        }
    }

    private static double applyFunction(String func, double value) {
        switch (func) {
            case "sin": return Math.sin(Math.toRadians(value));
            case "cos": return Math.cos(Math.toRadians(value));
            case "tan": return Math.tan(Math.toRadians(value));
            case "sqrt": return Math.sqrt(value);
            case "log": return Math.log10(value);
            case "ln": return Math.log(value);
            default: return value;
        }
    }

    public static String formatNumber(double num) {
        if (num == (long) num) return String.format("%d", (long) num);
        return String.format("%.6f", num);
    }
}