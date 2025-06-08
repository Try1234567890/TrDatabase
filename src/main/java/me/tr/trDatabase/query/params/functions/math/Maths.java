package me.tr.trDatabase.query.params.functions.math;

public class Maths {
    private static final Maths instance = new Maths();

    public static Maths instance() {
        return instance;
    }

    private Maths() {
    }

    public Math abs(int number) {
        return new Abs().number(number);
    }

    public Math ceil(int number) {
        return new Ceil().number(number);
    }

    public Math floor(int number) {
        return new Floor().number(number);
    }

    public Math mod(int number, int divisor) {
        return new Mod().divisor(divisor).number(number);
    }

    public Math power(int number, int power) {
        return new Power().power(power).number(number);
    }

    public Math rand() {
        return new Rand();
    }

    public Math round(int number) {
        return new Round().number(number);
    }

    public Math sqrt(int number) {
        return new Sqrt().number(number);
    }

}
