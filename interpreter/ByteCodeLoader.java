
package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.virtualmachine.Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
        String line;
        String[] items;
        //String byteCodeName;// ByteCode name from .x.cod file
        String className;//class name from the codeTable
        Class classBluePrint;
        Program program = new Program();
        ByteCode bc;
        ArrayList<String> args;
        try {
        while(this.byteSource.ready()) {//read until there are no more lines to read
            //reading line by line
            line = this.byteSource.readLine();
            //splitting each line into an array
            items = line.split("\\s+");
            //byteCodeName = items[0];
            //getting the first element in the array, which is ByteCode and then getting its value from HashMap in CodeTable
            className = CodeTable.getClassName(items[0]);
            //loading the class blueprint from interpreter.bytecode
            classBluePrint = Class.forName("interpreter.bytecode."+className);
            //creating the new instance of the bytecode
            bc = (ByteCode) classBluePrint.getDeclaredConstructor().newInstance();
            //grabbing the rest of the args
            args = new ArrayList<>();
            if(items.length > 1) {
                for (int i=1; i< items.length; i++){
                    args.add(items[i]);
                }
            }
            //passing args to bytecode init function
            bc.init(args);
            //add bytecode to the program
            program.addCode(bc);
        }
        }catch(IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            System.exit(255);
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            System.out.println(ex);
        }

        program.resolveAddress();
        return program;
    }
}
