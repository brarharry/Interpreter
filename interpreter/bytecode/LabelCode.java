package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LabelCode extends ByteCode{
    String label;
    @Override
    public void init(ArrayList<String> args) {
        this.label = args.get(0);
    }

    @Override
    public void executeProgram(VirtualMachine vm) {

    }

    @Override
    public String toString() {
        return "LABEL "+label;
    }

    public String getLabel() {
        return label;
    }
}
