/* OK, So not really a VM, but my own take on this
 * complicated thing. Basically a challenge for myself
 * to check if i can implement something simmilar to
 * assembly/bytecode without doing much reaserch on this topic.
 *
 * It would be cool to write some sort of parser or AST generator
 * so i clould actually turn code into this intermediate representation
 * but im kinda lazy, so for now if you want to program in this
 * assembly-ish language, you need to write Java code - yay !
 */
public class Main {

    private static String VERSION = "8.06.21", AUTHOR = "alixhan_basha";
    public static void main( String args[] ){
        if( args.length == 1 && args[0].equals("-v") ) Utils.VERBOSE = true;

        System.out.println( "+--------------------------------------------+" );
        System.out.println( "| PRASM v" + VERSION + "   |   Author: " + AUTHOR + " |" );
        System.out.println( "+--------------------------------------------+\n" );
        // PRASM -> Primitive ASM
        Instruction program[] = { // this is where the programming logic for this assembly-is language goes
            new Instruction.Move( Memory.MemoryLocation.RAX, "Hello World" ), // Move RAX, "Hello World"
            new Instruction.Read( Memory.MemoryLocation.RAX ) , // Read RAX

            new Instruction.Move( Memory.MemoryLocation.RA1, 123 ),
            new Instruction.Read( Memory.MemoryLocation.RA1 ),

            new Instruction.Move( Memory.MemoryLocation.RA1, "Alixhan Basha" ),
            new Instruction.Read( Memory.MemoryLocation.RA1 ),

            new Instruction.Add( Memory.MemoryLocation.RA2, "test", " more test" ),
            new Instruction.Read( Memory.MemoryLocation.RA2 ),

            new Instruction.Add( Memory.MemoryLocation.RA3, "integer", 5563 ),
            new Instruction.Read( Memory.MemoryLocation.RA3 ),

            new Instruction.Add( Memory.MemoryLocation.RA3, "double", 123.4 ),
            new Instruction.Read( Memory.MemoryLocation.RA3 ),

            new Instruction.Add( Memory.MemoryLocation.RA4, 10, 10 ),
            new Instruction.Read( Memory.MemoryLocation.RA4 ),

            new Instruction.Add( Memory.MemoryLocation.RA5, 10, 10.5 ), // currently only works with (int+int) or (double+double) or ( string + int||double or int||double + string )
            new Instruction.Read( Memory.MemoryLocation.RA5 ),

            new Instruction.Add( Memory.MemoryLocation.RA6, 5.2, 10.99 ),
            new Instruction.Read( Memory.MemoryLocation.RA6 ),

            new Instruction.Add( Memory.MemoryLocation.RA7, 5563, "string" ),
            new Instruction.Read( Memory.MemoryLocation.RA7 ),
        };

        for( Instruction i : program ) Interpreter.exec( i ); // Execute the instructions one-by-one
        // System.out.println("\n" + Memory.getDescriptionOf( Memory.MemoryLocation.RAX ) + "\n");
        System.out.println( "\n" );
        Memory.displayMemory();
        // System.out.println( "\n" );
        // Memory.printDescription();
    }
}
