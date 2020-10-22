package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class LoadCode extends ByteCode{
    private int offset;
    private String id;
    //private int loaded;
    @Override
    public void init(ArrayList<String> args) {
        offset = Integer.parseInt(args.get(0));
        if (args.size() > 1) {
            id = args.get(1);
        }
    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        vm.load(offset);
    }

    @Override
    public String toString() {
        String base = "LOAD "+ offset;
        if(id != null) {base = "LOAD "+ offset + " "+id+ " <load "+id+">";}
        return base;
    }
}
