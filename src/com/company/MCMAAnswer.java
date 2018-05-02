package com.company;
import java.util.Scanner;

class MCMAAnswer extends MCAnswer {
    public MCMAAnswer (String input, double value)
    {
        super (input, value);
    }

    public MCMAAnswer (Scanner scan)
    {
        super (scan.nextLine(), scan.nextDouble());
    }
}
