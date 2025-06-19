package me.tr.trDatabase.query.params.functions.math.operations;

import me.tr.trDatabase.query.params.functions.math.Math;

public abstract class Operation extends Math {
    private int leftNumber;
    private int rightNumber;
    private char symbol;


    protected Operation(char symbol) {
        this.symbol = symbol;
    }

    public int leftNumber() {
        return leftNumber;
    }

    public Operation leftNumber(int leftNumber) {
        this.leftNumber = leftNumber;
        return this;
    }

    public int rightNumber() {
        return rightNumber;
    }

    public Operation rightNumber(int rightNumber) {
        this.rightNumber = rightNumber;
        return this;
    }

    public char symbol() {
        return symbol;
    }


    public static Operation add(int leftNumber, int rightNumber) {
        return new Addition().leftNumber(leftNumber).rightNumber(rightNumber);
    }

    public static Operation sub(int leftNumber, int rightNumber) {
        return new Subtraction().leftNumber(leftNumber).rightNumber(rightNumber);
    }

    public static Operation mol(int leftNumber, int rightNumber) {
        return new Moltiplication().leftNumber(leftNumber).rightNumber(rightNumber);
    }

    public static Operation div(int leftNumber, int rightNumber) {
        return new Division().leftNumber(leftNumber).rightNumber(rightNumber);
    }

    @Override
    public String toSql() {
        return leftNumber() + " " + symbol() + " " + leftNumber();
    }
}
