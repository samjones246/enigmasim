import java.io.*;
import java.util.ArrayList;

/**
 * Handles user interaction with the enigma machine by allowing them to change settings
 * through a command line interface.
 */
public class EMInterface {
    BufferedReader reader;
    EnigmaMachine enigmaMachine;
    Reflector reflector;
    int[] startPositions = new int[3];

    /**
     * Class constructor which instantiates EnigmaMachine components and then starts the menu
     */
    EMInterface(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        enigmaMachine = new EnigmaMachine();
        reflector = new Reflector();
        try {
            reflector.initialise("ReflectorI");
            enigmaMachine.addReflector(reflector);
            enigmaMachine.addRotor(new BasicRotor("I"), 0);
            enigmaMachine.addRotor(new BasicRotor("I"), 1);
            enigmaMachine.addRotor(new BasicRotor("I"), 2);
        }catch (Exception e){
            System.err.println(e);
        }
        try {
            loadSettings();
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i=0;i<2;i++) {
            if (enigmaMachine.rotors[i] instanceof TurnoverRotor) {
                ((TurnoverRotor) enigmaMachine.rotors[i]).setNextRotor(enigmaMachine.rotors[i + 1]);
            }
        }
        menu();
    }

    /**
     * Main menu of the interface.
     * Shows the user their options and then calls the relevant method based on user input. This menu will always
     * show when the user leaves a submenu until the user decides to quit.
     */
    public void menu(){
        boolean run = true;
        while(run) {
            clearScreen();
            System.out.println("---Enigma Machine---");
            System.out.println("1 - Plugboard Settings");
            System.out.println("2 - Rotor Settings");
            System.out.println("3 - Reflector Type");
            System.out.println("4 - Start");
            System.out.println("5 - Quit");
            System.out.println("6 - Bombe Challenges");
            System.out.print("> ");
            String input = userInput();
            switch (input) {
                case "1":
                    plugboard();
                    break;
                case "2":
                    rotors();
                    break;
                case "3":
                    reflector();
                    break;
                case "4":
                    start();
                    break;
                case "5":
                    quit();
                    run = false;
                    break;
                case "6":
                    bombeTests();
                    break;
            }
        }
    }

    /**
     * The submenu for choosing which bombe challenge to demonstrate
     */
    private void bombeTests() {
        Bombe bombe = new Bombe();
        clearScreen();
        System.out.println("---Bombe Challenges---");
        System.out.println("1 - Challenge 1 : Plug Ends");
        System.out.println("2 - Challenge 2 : Rotor Positions");
        System.out.println("3 - Challenge 3 : Rotor Types");
        System.out.print("> ");
        String input = userInput();
        try {
            System.out.println("Possible Solutions: ");
            switch (input) {
                case "1":
                    bombe.test1();
                    break;
                case "2":
                    bombe.test2();
                    break;
                case "3":
                    bombe.test3();
                    break;
            }
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
        System.out.println("Press Enter to continue");
        userInput();
    }

    /**
     * Method to clear the console screen.
     * This is used when moving between menus to make the output less messy.
     */
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * The submenu for adjusting plugboard settings.
     * Gives the user the option to add plugs or clear the plugboard.
     */
    public void plugboard() {
        clearScreen();
        System.out.println("---Plugboard Settings---");
        System.out.println("1 - Add Plug");
        System.out.println("2 - Clear Plugboard");
        System.out.println("3 - Back");
        String input = userInput();
        switch (input) {
            case "1":
                char end1, end2;
                System.out.print("End 1: ");
                end1 = userInput().charAt(0);
                System.out.print("End 2: ");
                end2 = userInput().charAt(0);
                enigmaMachine.addPlug(end1, end2);
                break;
            case "2":
                enigmaMachine.clearPlugboard();
        }
    }

    /**
     * The submenu for adjusting rotor settings
     * Allows user to choose which of the three rotors they would like to adjust and then lets them set the type
     * and position of that rotor. Also allows the user to specify if the rotor is a turnover rotor, and updates the
     * nextRotor property of any turnover rotors after changes have been made.
     */
    public void rotors(){
        clearScreen();
        int slot;
        System.out.println("---Rotor Settings---");
        System.out.print("Rotor to adjust (0 - 2): ");
        slot = Integer.parseInt(userInput());
        System.out.print("Set type (I, II, III, IV or V): ");
        String type = userInput();
        System.out.print("Is this a Turnover Rotor? (Y/N): ");
        String turnOver = userInput();
        try {
            if (turnOver.toUpperCase().equals("Y")) {
                enigmaMachine.addRotor(new TurnoverRotor(type), slot);
            } else {
                enigmaMachine.addRotor(new BasicRotor(type), slot);
            }
        }catch (Exception e){
            System.err.println(e);
        }
        System.out.print("Set position (0-25): ");
        try {
            startPositions[slot] = Integer.parseInt(userInput());
            enigmaMachine.rotors[slot].setPosition(startPositions[slot]);
        }catch (Exception e){
            System.err.println(e);
        }
        for(int i=0;i<2;i++) {
            if (enigmaMachine.rotors[i] instanceof TurnoverRotor) {
                ((TurnoverRotor) enigmaMachine.rotors[i]).setNextRotor(enigmaMachine.rotors[i + 1]);
            }
        }
    }

    /**
     * The submenu for adjusting reflector settings.
     * This just lets the user decide the type of the reflector.
     */
    public void reflector(){
        clearScreen();
        System.out.println("---Reflector Type---");
        System.out.println("Set reflector type (ReflectorI or ReflectorII): ");
        try {
            enigmaMachine.reflector.initialise(userInput());
        }catch (Exception e){
            System.err.println(e);
        }
    }

    /**
     * The submenu for starting the encoding/decoding process.
     * This runs a string of characters through the EnigmaMachine which the user has to configured. All the components
     * are given default settings when they are initialised so this will still work if the user has not changed
     * any settings.
     */
    public void start(){
        char[] chars;
        System.out.println("---Start---");
        System.out.println("1 - Enter string manually");
        System.out.println("2 - Use string from file");
        System.out.println("3 - Back");
        String input = userInput();
        switch (input){
            case "1":
                System.out.println("Enter string to pass to Enigma Machine: ");
                chars = userInput().toCharArray();
                System.out.println("Encoded/Decoded message: ");
                for(int i=0;i<chars.length;i++){
                    try {
                        System.out.print(enigmaMachine.encodeLetter(chars[i]));
                    }catch (Exception e){
                        System.err.println(e);
                    }
                }
                System.out.println("\nPress enter to continue");
                userInput();
                break;
            case "2":
                EnigmaFile enigmaFile = new EnigmaFile(enigmaMachine);
                enigmaFile.encode();
                System.out.println("Output saved to output.txt");
                System.out.println("Press enter to continue");
                userInput();
                break;
            default:
                break;
        }
        for(int i = 0;i<3;i++){
            try {
                enigmaMachine.rotors[i].setPosition(startPositions[i]);
            }catch (Exception e){
                System.err.println(e);
            }
        }
    }
    String userInput(){
        String input=null;
        try {
            input=reader.readLine();
        }catch (IOException e){
            System.err.println(e);
        }
        return input;
    }
    void quit(){
        try {
            PrintStream writer = new PrintStream("settings.txt");
            for(BasicRotor rotor:enigmaMachine.rotors){
                writer.println(rotor.name + "," + rotor.position + "," + (rotor instanceof TurnoverRotor));
            }
            writer.println(reflector.name);
            for (Plug plug : enigmaMachine.plugboard.getPlugs()){
                if(plug!=null) {
                    writer.println(plug.getEnd1() + plug.getEnd2());
                }
            }
        }catch (FileNotFoundException e){
            System.err.println("Settings file not found.");
        }
    }
    void loadSettings() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader("settings.txt"));
        reader.mark(1024);
        for (int i=0;i<3;i++){
            String[] line = reader.readLine().split(",");
            if(line[2].equals("true")){
                enigmaMachine.addRotor(new TurnoverRotor(line[0]), i);
            }else{
                enigmaMachine.addRotor(new BasicRotor(line[0]), i);
            }
            enigmaMachine.getRotor(i).setPosition(Integer.parseInt(line[1]));
        }
        enigmaMachine.getReflector().initialise(reader.readLine());
        String line;
        while((line=reader.readLine())!=null){
            char[] ends = line.toCharArray();
            enigmaMachine.addPlug(ends[0], ends[1]);
        }
    }
}
