public class Utils {
    public static boolean VERBOSE = false;

    public static void vprint( String s, String spaces ){
        if( VERBOSE )
            System.out.println( spaces + "[ i ]: " + s  );
    }
}
