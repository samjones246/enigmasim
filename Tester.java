// This is a tester class used for debugging purposes only
public class Tester {
    public static void main(String[] args){
    /*    System.out.println("----Plug method test----");
        System.out.println("Creating plugAB, ends A and B...");
        Plug plugAB = new Plug('A', 'B');
        System.out.println("Creating plugBC, ends B and C...");
        Plug plugBC = new Plug('B', 'C');
        System.out.println("Encoding 'A' using plugAB...");
        System.out.println("Result: " + plugAB.encode('A'));
        System.out.println("Encoding 'A' using plugBC...");
        System.out.println("Result: " + plugBC.encode('A'));
        System.out.println("Checking for clash between plugAB and plugBC...");
        System.out.println("Result: " + plugAB.clashesWith(plugBC));

        System.out.println("----Plugboard Test----");
        System.out.println("Creating Plugboard...");
        Plugboard plugboard = new Plugboard();
        System.out.println("Adding plug with ends 'T' and 'F' to plugboard...");
        System.out.println("Result: " + plugboard.addPlug('T', 'F'));

        System.out.println("Adding plug with ends 'Q' and 'P' to plugboard...");
        System.out.println("Result: " + plugboard.addPlug('Q', 'P'));

        System.out.println("Adding plug with ends 'A' and 'L' to plugboard...");
        System.out.println("Result: " + plugboard.addPlug('A', 'L'));

        System.out.println("Adding plug with ends 'T' and 'R' to plugboard...");
        System.out.println("Result: " + plugboard.addPlug('T', 'R'));
        System.out.println("Numplugs = " +plugboard.getNumPlugs());

        System.out.println("Encoding 'P' using plugboard...");
        System.out.println("Result: " + plugboard.substitute('P'));
        */
        System.out.println("----Reflector Test----");
        System.out.println("Creating Reflectors...");
        Reflector rI = new Reflector();
        Reflector rII = new Reflector();
        try {
            rI.initialise("ReflectorI");
            rII.initialise("ReflectorII");
        }catch(Exception e){
            System.err.println(e);
        }
        System.out.println("Substituting 3 with rI...");
        System.out.println("Expected Output: 7");
        System.out.print("Actual output: ");
        try {
            System.out.println(rI.substitute(3));
        }catch (Exception e){
            System.err.println(e);
        }
        System.out.println("Substituting 3 with rII...");
        System.out.println("Expected Output: 9");
        System.out.print("Actual output: ");
        try {
            System.out.println(rII.substitute(3));
        }catch (Exception e){
            System.err.println(e);
        }

    }
}
