package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode{
    private String operator;
    private int result;

    @Override
    public void init(ArrayList<String> args) {
        this.operator = args.get(0);
    }

    @Override
    public void executeProgram(VirtualMachine vm) {
        int secondValue = vm.pop();
        int firstValue = vm.pop();
        //int result =0;
        switch(operator){
            case "+" :
                result = firstValue+secondValue;
                break;
            case "-" :
                result = firstValue - secondValue;
                break;
            case "*" :
                result = firstValue * secondValue;
                break;
            case "/" :
                result = firstValue / secondValue;
                break;
            case "==" :
                if(firstValue == secondValue) {
                    result = 1;
                }
                else { result = 0; }
                break;
            case "!=" :
                if (firstValue != secondValue){
                    result = 0;
                }
                else { result = 1; }
                break;
            case "<=" :
                if(firstValue <= secondValue) {
                    result = 1;
                }
                else { result = 0; }
                break;
            case ">=" :
                if(firstValue >= secondValue) {
                    result = 1;
                }
                else { result = 0; }
                break;
            case "<" :
                if(firstValue < secondValue) {
                    result = 1;
                }
                else { result = 0; }
                break;
            case ">" :
                if(firstValue > secondValue) {
                    result = 1;
                }
                else { result = 0; }
                break;
            case "|" :
                if( firstValue == 1 || secondValue == 1){
                    result = 1;
                }
                else { result = 0; }
                break;
            case "&" :
                if( firstValue == 1 && secondValue == 1){
                    result = 1;
                }
                else { result = 0; }
                break;
        }
        vm.push(result);
    }

    @Override
    public String toString() {
        return "BOP "+operator;
    }
}
