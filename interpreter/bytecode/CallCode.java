package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class CallCode extends ByteCode{
    private String label;
    private int labelValue;
    private String baseId;
    private ArrayList<Integer> frameValues;
    @Override
    public void init(ArrayList<String> args) {
        this.label = args.get(0);
    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        frameValues = vm.returnFrame();
        vm.save();
        vm.jumpTo(labelValue);

    }

    @Override
    public String toString() {
        String base = "CALL "+label+" \t"+baseId+"(";
        for (int i=0; i<frameValues.size(); i++){
            if (i == frameValues.size()-1){
                base = base + frameValues.get(i);
            }
            else {
                base = base + frameValues.get(i) + ",";
            }
        }
        return base+")";
    }

    public void setLabelValue(int label) {
        this.labelValue = label;
    }

    public String getLabel(){
        return label;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }
}
