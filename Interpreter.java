public class Interpreter {

    public static void exec( Instruction i ){
        Utils.vprint( "Executing instruction '" + i.getInstructionName() + "'", "\n" );
        i.ExecuteInstruction();
    }

}
