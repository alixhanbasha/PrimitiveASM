public class Memory {

    enum MemoryLocation {

        RAX( "rax", 0 , "The result stored from ADD, WRITE and SUB is here"),
        RA1( "ra1", 1 , "Not_Available"),
        RA2( "ra2", 2 , "Not_Available"),
        RA3( "ra3", 3 , "Not_Available"),
        RA4( "ra4", 4 , "Not_Available"),
        RA5( "ra5", 5 , "Not_Available"),
        RA6( "ra6", 6 , "Not_Available"),
        RA7( "ra7", 7 , "Not_Available"),
        RA8( "ra8", 8 , "Not_Available"),
        RA9( "ra9", 9 , "Not_Available");

        public String name, description;
        public int location;

        MemoryLocation( String name, int location, String desc ){
            this.name = name;
            this.location = location;
            this.description = desc;
        }
    }

    public static Object ProgramMemory[] = new Object[10];


    public static void insertInto( MemoryLocation ml, Object obj ){
        // TODO -> What if a non-existent MemoryLocation is sent ?
        Utils.vprint( "Insert into: " + ml.name + " @ index " + ml.location + " value ['" + obj.toString() + "']\n", "\t" );
        ProgramMemory[ml.location] = obj;
    }

    public static void deleteFrom( MemoryLocation ml, Object obj ){
        Utils.vprint( "\tDelete from: " + ml.name + " @ index " + ml.location + " value ['" + obj.toString() + "']\n", "\t" );
        Utils.vprint( "deleteFrom :: Not_Implemented", "" );
    }

    public static Object fetchFrom( MemoryLocation ml ){
        if( ProgramMemory[ml.location] != null )
            return new String( "" + ProgramMemory[ml.location].toString() );
        return new String( "__________" );
    }

    public static void displayMemory() {
        System.out.println( "Internal Memory {" );

        for( MemoryLocation ml : MemoryLocation.values() ){
            System.out.println( "    " + ml.location + " " + ml.name + "\t" + fetchFrom( ml ) );
        }

        System.out.println( "} " );
    }

    public static String getDescriptionOf( MemoryLocation ml ){ return ml.name + "  :  " + ml.description; }
    public static void printDescription(){
        System.out.println( "Register Descriptions {" );
        for( MemoryLocation ml : MemoryLocation.values() )
            System.out.println( "    " + getDescriptionOf(ml) );
        System.out.println( "}" );
    }
}
