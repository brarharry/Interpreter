package interpreter.virtualmachine;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;
import interpreter.bytecode.HaltCode;

import java.util.ArrayList;
import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        dumping;

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram(){
        programCounter = 0;
         runTimeStack = new RunTimeStack ();
         returnAddress = new Stack < Integer >();
         isRunning = true ;
         dumping = true;

         while ( isRunning ){
             ByteCode code = program.getCode ( programCounter );
             code.executeProgram ( this );
             dumpHere(code);
             programCounter ++;
             }
    }
    public void dumpHere(ByteCode code){
        if(dumping && !(code instanceof DumpCode) && !(code instanceof HaltCode)){
        System.out.println(code);
        runTimeStack.dump();}
    }

    public void isDumping(boolean dumping) {
        this.dumping = dumping;
    }

    public void push(int n) {
        runTimeStack.push(n);
    }

    public void halt() {
        isRunning = false;
    }

    public int pop() {
        return runTimeStack.pop();
    }

    public void save(){ returnAddress.push(programCounter);}

    public void jumpTo(int index) { programCounter = index; }

    public int store(int offset){ return runTimeStack.store(offset); }

    public int load(int offset) { return runTimeStack.load(offset); }

    public int peek() { return runTimeStack.peek(); }

    public void newFrame(int offset) { runTimeStack.newFrameAt(offset); }

    public void poppingFrame(){ runTimeStack.popFrame(); }

    public int popReturnAdressStack(){ return returnAddress.pop(); }

    public void setProgramCounter(int index){ this.programCounter = index; }

    public ArrayList<Integer> returnFrame(){ return runTimeStack.getFrameElements(); }

    public int runTimeStackSize() {
        return runTimeStack.size();
    }

   public int returnFrameSize() { return runTimeStack.frameSize(); }
    //implement the framsize method in pop

}
