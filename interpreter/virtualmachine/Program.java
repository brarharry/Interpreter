package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.HashMap;

import interpreter.bytecode.*;


public class Program {

    private ArrayList<ByteCode> program;
    private HashMap<String, Integer> labelMap;

    public Program() {
        program = new ArrayList<>();
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }
//-------------NOT SURE ----------------
    public void addCode(ByteCode bc){
        this.program.add(bc);
    }
    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter
     * HINT: make note what type of data-structure ByteCodes are stored in.
     */
    public void resolveAddress() {
        labelMap = new HashMap<>();
        for (int i=0; i < program.size(); i++) {
            ByteCode temp = this.getCode(i);
            if (temp instanceof LabelCode && !labelMap.containsKey(((LabelCode) temp).getLabel())) {
                labelMap.put(((LabelCode) temp).getLabel(),i);
            }
        }
        for (int i=0; i < program.size(); i++) {
            ByteCode temp = this.getCode(i);

            if (temp instanceof GotoCode && labelMap.containsKey(((GotoCode) temp).getLabel())){
                ((GotoCode) temp).setLabel(labelMap.get(((GotoCode) temp).getLabel()));
            }
            else if (temp instanceof CallCode && labelMap.containsKey(((CallCode) temp).getLabel())) {
                String[] func = ((CallCode) temp).getLabel().split("<");
                ((CallCode) temp).setBaseId(func[0]);
                ((CallCode) temp).setLabelValue(labelMap.get(((CallCode) temp).getLabel()));
            }
            else if (temp instanceof FalseBranchCode && labelMap.containsKey(((FalseBranchCode) temp).getLabel())){
                ((FalseBranchCode) temp).setLabel(labelMap.get(((FalseBranchCode) temp).getLabel()));
            }
            else if (temp instanceof ReturnCode && !((ReturnCode) temp).getLabel().equals("")) {
                if (labelMap.containsKey(((ReturnCode) temp).getLabel())){
                    String[] func = ((ReturnCode) temp).getLabel().split("<");
                    ((ReturnCode) temp).setBaseId(func[0]);
                    ((ReturnCode) temp).setLabelValue(labelMap.get(((ReturnCode) temp).getLabel()));
                }
            }
        }

    }




}
