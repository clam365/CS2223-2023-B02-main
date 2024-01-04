package edu.wpi.cs2223.algorithms.week1;

import edu.wpi.cs2223.algorithms.shared.FixedCapacityArrayStack;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of Dijkstra's two stack algorithm for Expression Evaluation.
 */
public class DijkstrasCalculator {
    FixedCapacityArrayStack<Character> operators;
    FixedCapacityArrayStack<Integer> operands;

    static final Set<Character> SUPPORTED_OPERATORS = new HashSet<>(){{
        add('+');
        add('-');
        add('*');
        add('/');
        add('r');
    }};

    public DijkstrasCalculator() {
        this.operators = new FixedCapacityArrayStack<>(100);
        this.operands = new FixedCapacityArrayStack<>(100);
    }

    boolean isSupportedOperator(Character character) {
        return SUPPORTED_OPERATORS.contains(character);
    }

    void performOperation() {
        Character operator = operators.pop();

        // special handle square root since it only takes one operand
        if (operator.equals('r')) {
            int operand = operands.pop();

            // perform square root and push result back onto stack
            int result = (int) Math.sqrt(operand);
            operands.push(result);
        } else {
            int operandTwo = operands.pop();
            int operandOne = operands.pop();
            operands.push(performTwoOperandOperation(operator, operandOne, operandTwo));
        }
    }

    int performTwoOperandOperation(Character operator, int operandOne, int operandTwo) {
        switch (operator) {
            case '+':
                return operandOne + operandTwo;
            case '-':
                return operandOne - operandTwo;
            case '*':
                return operandOne * operandTwo;
            case '/':
                return operandOne / operandTwo;
            default:
                throw new UnsupportedOperationException(operator + " not yet supported");
        }
    }

    public int evaluate(String input) {
        // support multi-character/digit integers
        String inProgressNumber = "";

        for (Character character: input.toCharArray()){
            if (Character.isDigit(character)) {
                inProgressNumber += character;
                continue;
            }

            // close out in progress number if we had a sequence of integer characters end
            if (!inProgressNumber.isEmpty()) {
                operands.push(Integer.valueOf(inProgressNumber));
                inProgressNumber = "";
            }

            if (isSupportedOperator(character)){
                operators.push(character);
                continue;
            }

            // closing parens denote that we need to perform an operation
            if (character.equals(')')){
                performOperation();
            }
        }

        // at the end, we should have a single value left on the stack - the result;
        return operands.pop();
    }
}
