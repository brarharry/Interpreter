package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class GotoCode extends ByteCode{
    String label;
    private int labelValue;
    @Override
    public void init(ArrayList<String> args) {
        this.label = args.get(0);
    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        vm.jumpTo(labelValue);
    }

    @Override
    public String toString() {
        return "GOTO "+label;
    }

    public void setLabel(int index) {
        this.labelValue = index;
    }

    public String getLabel() {
        return label;
    }
}
