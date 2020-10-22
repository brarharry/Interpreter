package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class StoreCode extends ByteCode{
    private int offset;
    private String id;
    private int stored;
    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) {
            id = args.get(1);
        }
    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        stored = vm.store(offset);
    }

    @Override
    public String toString() {
        String base = "STORE "+offset;
        if (id != null) { base += " "+id+"\t"+id+"="+stored;}
        return base;
    }
}
