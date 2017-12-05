import java.io.*;

/**
 * This class is used for encoding/decoding text from a file.
 */
public class EnigmaFile {
    EnigmaMachine enigmaMachine;
    BufferedReader reader;
    PrintStream writer;

    /**
     * Class constructor, instantiates the objects needed for file operations.
     * @param enigmaMachine The EnigmaMachine instance which will be used to encode/decode the file.
     */
    EnigmaFile(EnigmaMachine enigmaMachine){
        this.enigmaMachine = enigmaMachine;
        try {
            reader = new BufferedReader(new FileReader("input.txt"));
        }catch (FileNotFoundException e){
            System.err.println("Could not find input file.");
        }
        try {
            writer = new PrintStream("output.txt");
        }catch (FileNotFoundException e){
            System.err.println("Could not find output file.");
        }
    }

    /**
     * Encodes the text from the file.
     * Gets a string of characters from the input file, runs them through the enigma machine and prints them to the
     * output file.
     */
    void encode(){
        char[] text=null;
        try {
            text = reader.readLine().toCharArray();
        }catch (IOException e){
            System.err.println(e);
        }
        for(char character : text){
            char toPrint;
            try {
                writer.print(enigmaMachine.encodeLetter(character));
            }catch (Exception e){
                System.err.println(e);
            }
        }
    }
}
