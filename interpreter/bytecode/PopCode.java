package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class PopCode extends ByteCode{

    private int n;
    @Override
    public void init(ArrayList<String> args) {
        this.n = Integer.parseInt(args.get(0));
    }
    //----------------NOT SURE--------------
    @Override
    public void executeProgram(VirtualMachine vm) {
        int temp=0;
        //System.out.println("Frame size is here" + vm.returnFrameSize());
        if (vm.returnFrameSize() < n) {n = vm.returnFrameSize();}
        while (temp < this.n){
            vm.pop();
            temp++;
        }
    }

    @Override
    public String toString() {
        return "POP "+n;
    }
}
