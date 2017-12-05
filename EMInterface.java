import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles user interaction with the enigma machine by allowing them to change settings
 * through a command line interface.
 */
public class EMInterface {
    BufferedReader reader;
    EnigmaMachine enigmaMachine;
    Reflector reflector;

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
                    run = false;
                    break;
            }
        }
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
     * and position of that rotor.
     */
    public void rotors(){
        clearScreen();
        int i;
        System.out.println("---Rotor Settings---");
        System.out.print("Rotor to adjust (0 - 2): ");
        i = Integer.parseInt(userInput());
        System.out.print("Set type (I, II, III, IV or V): ");
        String type = userInput();
        try {
            enigmaMachine.addRotor(new BasicRotor(type), i);
        }catch (Exception e){
            System.err.println(e);
        }
        System.out.print("Set position (0-25): ");
        try {
            enigmaMachine.rotors[i].setPosition(Integer.parseInt(userInput()));
        }catch (Exception e){
            System.err.println(e);
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
}
