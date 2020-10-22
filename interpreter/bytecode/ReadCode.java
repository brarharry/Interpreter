package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode{
    Scanner input = new Scanner(System.in);
    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        int digit=0;
        System.out.println("Please input an integer : ");

        boolean isDigit = false;
        while (!isDigit) {
            String value = input.nextLine();
            try {
                digit = Integer.parseInt(value);
                isDigit = true;
            }
            catch (NumberFormatException ex) {
                System.out.println("NOT VALID! Please input a valid integer");
            }
        }
        vm.push(digit);
    }

    @Override
    public String toString() {
        return "READ ";
    }
}
