package com.company;
import java.util.Scanner;

class MCSAAnswer extends MCAnswer {
    protected MCSAAnswer (String input, double value)
    {
        super (input, value);
    }

    public MCSAAnswer (Scanner scan)
    {
        super (scan.nextLine(), scan.nextDouble());
    }
}
