package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class WriteCode extends ByteCode{
    int top;
    @Override
    public void init(ArrayList<String> args) {

    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        top = vm.peek();
    }

    @Override
    public String toString() {
        return "WRITE ";
    }
}
