// This is a tester class used for debugging purposes only
public class Tester {
    public static void main(String[] args){
        System.out.println("----Plug method test----");
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
        System.out.println("");
    }
}
