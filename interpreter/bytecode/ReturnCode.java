package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class ReturnCode extends ByteCode{
    private String label="";
    private String baseId="";
    private int labelValue;
    private int temp;
    @Override
    public void init(ArrayList<String> args) {
        if (args.size() > 0) {
            label = args.get(0);
        }
    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        temp = vm.peek();
        vm.poppingFrame();
        vm.push(temp);
        vm.setProgramCounter(vm.popReturnAdressStack());
    }

    public String getLabel() {
        return label;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public void setLabelValue(int labelValue){
        this.labelValue = labelValue;
    }

    @Override
    public String toString() {
        String base;
        if (!label.equals("")){
            base = "RETURN "+label+"   EXIT "+baseId+" : "+temp;
        }
        else {
            base = "RETURN";
        }
        return base;
    }
}
