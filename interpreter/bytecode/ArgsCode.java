package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    private int offset;

    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(0));
    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        vm.newFrame(offset);
    }

    @Override
    public String toString() {
        return "ARGS "+offset;
    }
}
