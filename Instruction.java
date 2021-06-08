/* QUESTION
 *     Is it better to create a "Operant" class that wraps Object, but has specific
 *     functions to determine the type of the object ?
 *
 *  TODO
 *     - Allow the arguments of the instruction to be MemoryLocation, if so then take the value from the location and finish the operation
 *     - Definetly have to create a "Calculator" class that handles numbers and strings, in order to not repeat the code for simmilar instructions
 * */
abstract class Instruction {

    public abstract String getInstructionName();
    public abstract int getArgNumber();
    public abstract Memory.MemoryLocation getMemoryLocation();
    public abstract int ExecuteInstruction();

    public static class Move extends Instruction {
        protected final String _INSTRUCTION_NAME_ = "Move";
        protected final int _REQUIRED_ARGS_ = 2;
        protected Memory.MemoryLocation _LOCATION_;

        private Object writable = null;

        public Move( Memory.MemoryLocation location, Object writable ){
            this._LOCATION_ = location;
            this.writable = writable;
        }

        @Override public String getInstructionName() { return this._INSTRUCTION_NAME_; }
        @Override public Memory.MemoryLocation getMemoryLocation(){ return this._LOCATION_; }
        @Override public int getArgNumber() { return this._REQUIRED_ARGS_; }

        @Override public int ExecuteInstruction(){
            Memory.insertInto( this._LOCATION_ , this.writable );
            return 0; // success
        }
    }

    public static class Read extends Instruction {
        protected String _INSTRUCTION_NAME_ = "Read";
        protected int _REQUIRED_ARGS_ = 1;
        protected Memory.MemoryLocation _LOCATION_;

        public Read( Memory.MemoryLocation location ){
            this._LOCATION_ = location;
        }

        @Override public String getInstructionName() { return this._INSTRUCTION_NAME_; }
        @Override public Memory.MemoryLocation getMemoryLocation(){ return this._LOCATION_; }
        @Override public int getArgNumber() { return this._REQUIRED_ARGS_; }

        @Override public int ExecuteInstruction(){
            Object o = Memory.fetchFrom( this._LOCATION_ );
            System.out.println( o.toString() );
            return 0;
        }

    }

    public static class Add extends Instruction {
        protected String _INSTRUCTION_NAME_ = "Add";
        protected int _REQUIRED_ARGS_ = 3;
        protected Memory.MemoryLocation _LOCATION_;

        private Object operant1, operant2;

        public Add( Memory.MemoryLocation location, Object o1 , Object o2 ){
            this._LOCATION_ = location;
            this.operant1 = o1;
            this.operant2 = o2;
        }

        @Override public String getInstructionName() { return this._INSTRUCTION_NAME_; }
        @Override public Memory.MemoryLocation getMemoryLocation(){ return this._LOCATION_; }
        @Override public int getArgNumber() { return this._REQUIRED_ARGS_; }

        @Override public int ExecuteInstruction(){
            // Add 2 numbers, 2 strings or 1 number and 1 string, then put them in the specified register
            if( this.operant1 instanceof Integer && this.operant2 instanceof Integer ){
                int n1 = Integer.parseInt( operant1.toString() );
                int n2 = Integer.parseInt( operant2.toString() );
                Memory.insertInto( this._LOCATION_, n1+n2 );
            }
            else if (
                     ( this.operant1 instanceof Double && this.operant2 instanceof Double )  ||
                     ( this.operant1 instanceof Double && this.operant2 instanceof Integer ) ||
                     ( this.operant1 instanceof Integer && this.operant2 instanceof Double ) ){
                // OK, so if the first or the second operand is a Double, and we are trying to add an integer to it,
                // OR both of the operands are actually Double, then convert all the operands to double,
                // and then save the result to the specified MemoryLocation !
                // Is there an easier way to do this ?? fml
                double n1 = Double.parseDouble( operant1.toString() );
                double n2 = Double.parseDouble( operant2.toString() );
                Memory.insertInto( this._LOCATION_, (double)(n1+n2) );
            }
            else if( this.operant1 instanceof String || this.operant2 instanceof String ){
                // below comment is cancer... ignore it ( allthough it can be usefull )
                // (this.operant1 instanceof String && (this.operant2 instanceof Integer || this.operant2 instanceof Double)) || (this.operant2 instanceof String && (this.operant1 instanceof Integer || this.operant1 instanceof Double))
                String res1 = operant1.toString();
                String res2 = operant2.toString();
                Memory.insertInto( this._LOCATION_, res1+res2 );
            }
            else throw new RuntimeException( "Add does not support these kind of operands !" );
            return 0;
        }
    }
    // class Subtract extends Instruction {}
    // class Jump extends Instruction {}
    // class Copy extends Instruction {}
}
