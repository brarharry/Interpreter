package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class DumpCode extends ByteCode{
    String value;
    @Override
    public void init(ArrayList<String> args) {
        value = args.get(0);
    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        vm.isDumping("ON".equals(value));
    }
}
