package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.Stack;

class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    /**
     * Used for dumping the current state of the runTimeStack .
     * It will print portions of the stack based on respective
     * frame markers .
     * Example [1 ,2 ,3] [4 ,5 ,6] [7 ,8]
     * Frame pointers would be 0 ,3 ,6
     */
    public void dump() {
        String base = "[";
        for (int i = 0; i < framePointer.size(); i++) {
            if (i > 0) {
                base = base + "] [";
            }
            int first = framePointer.get(i);
            int second = 0;
            if (i != framePointer.size() - 1) {
                second = framePointer.get(i + 1);
            } else {
                second = runTimeStack.size();
            }
            for (int j = first; j < second; j++) {
                if (j == second - 1) {
                    base = base + runTimeStack.get(j);
                } else {
                    base = base + runTimeStack.get(j) + " ,";
                }
            }
        }
        System.out.println(base + "]");
    }


    public int peek() {
        return this.runTimeStack.get(this.runTimeStack.size() - 1);
    }


    public int push(int i) {
        this.runTimeStack.add(i);
        return this.peek();
    }


    public int pop() {
        return this.runTimeStack.remove(this.runTimeStack.size() - 1);
    }


    public int store(int offset) {
        int top = runTimeStack.get(runTimeStack.size() - 1);
        runTimeStack.remove(runTimeStack.size() - 1);
        runTimeStack.set(framePointer.peek() + offset, top);
        return top;
    }


    public int load(int offset) {
        //missing current frame statement
        //runTimeStack.add(runTimeStack.size()-1,runTimeStack.get(offset));
        int currentFrame = framePointer.peek();
        int value = runTimeStack.get(currentFrame + offset);
        runTimeStack.add(value);
        return value;
    }


    public void newFrameAt(int offset) {
        framePointer.push(runTimeStack.size() - offset);
    }


    public void popFrame() {
        int currentFrameStartingPoint = framePointer.peek();
        for (int i = runTimeStack.size() - 1; i >= currentFrameStartingPoint; i--) {
            runTimeStack.remove(i);
        }
        framePointer.pop();
    }

    public ArrayList<Integer> getFrameElements() {
        int frameIndex = framePointer.peek();
        ArrayList<Integer> frameElements = new ArrayList<>();
        for (int i = frameIndex; i < runTimeStack.size(); i++) {
            frameElements.add(runTimeStack.get(i));
        }
        return frameElements;
    }

    public int frameSize() {
        return runTimeStack.size() - framePointer.peek();
    }

    public int size() {
        return runTimeStack.size();
    }


}
