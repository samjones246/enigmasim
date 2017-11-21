import javafx.application.Application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class EMInterface {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    EnigmaMachine enigmaMachine = new EnigmaMachine();
    Reflector r;
    public void menu() throws Exception{
        boolean run = true;
        br = new BufferedReader(new InputStreamReader(System.in));
        enigmaMachine = new EnigmaMachine();
        r = new Reflector();
        r.initialise("ReflectorI");
        enigmaMachine.addReflector(r);
        enigmaMachine.addRotor(new BasicRotor("I"), 0);
        enigmaMachine.addRotor(new BasicRotor("I"), 1);
        enigmaMachine.addRotor(new BasicRotor("I"), 2);
        while(run){
            clearScreen();
            System.out.println("---Enigma Machine---");
            System.out.println("1 - Plugboard Settings");
            System.out.println("2 - Rotor Settings");
            System.out.println("3 - Reflector Type");
            System.out.println("4 - Start");
            System.out.println("5 - Quit");
            System.out.print("> ");
            String input = br.readLine();
            switch (input){
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
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void plugboard() throws Exception{
        clearScreen();
        System.out.println("---Plugboard Settings---");
        System.out.println("1 - Add Plug");
        System.out.println("2 - Clear Plugboard");
        System.out.println("3 - Back");
        String input = br.readLine();
        switch (input){
            case "1":
                char end1, end2;
                System.out.print("End 1: ");
                end1 = br.readLine().charAt(0);
                System.out.print("End 2: ");
                end2 = br.readLine().charAt(0);
                enigmaMachine.addPlug(end1, end2);
                break;
            case "2":
                enigmaMachine.clearPlugboard();
        }

    }
    public void rotors() throws Exception{
        clearScreen();
        int i;
        System.out.println("---Rotor Settings---");
        System.out.print("Rotor to adjust (0 - 2): ");
        i = Integer.parseInt(br.readLine());
        System.out.print("Set type (I, II, III, IV or V): ");
        String type = br.readLine();
        enigmaMachine.addRotor(new BasicRotor(type), i);
        System.out.print("Set position (0-25): ");
        enigmaMachine.rotors[i].setPosition(Integer.parseInt(br.readLine()));
    }
    public void reflector() throws Exception{
        clearScreen();
        System.out.println("---Reflector Type---");
        System.out.println("Set reflector type (ReflectorI or ReflectorII): ");
        enigmaMachine.reflector.initialise(br.readLine());
    }
    public void start() throws Exception{
        System.out.println("Enter string to pass to Enigma Machine: ");
        char[] chars = br.readLine().toCharArray();
        System.out.println("Encoded/Decoded message: ");
        for(int i=0;i<chars.length;i++){
            System.out.print(enigmaMachine.encodeLetter(chars[i]));
        }
        System.out.println("\nPress enter to continue");
        br.readLine();
    }

}
