package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode{
    private String label;
    @Override
    public void init(ArrayList<String> args) {
        this.label = args.get(0);
    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        if (vm.pop() == 0) {
            vm.jumpTo(Integer.parseInt(label));
        }
    }

    @Override
    public String toString() {
        return "FALSEBRANCH "+label;
    }

    public void setLabel(int index) {
        this.label = Integer.toString(index);
    }

    public String getLabel() {
        return label;
    }
}
