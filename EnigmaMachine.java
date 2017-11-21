import java.util.HashMap;

/**
 * Created by sj2g17 on 17/11/17.
 */
public class EnigmaMachine {
    Plugboard plugboard;
    BasicRotor[] rotors = new BasicRotor[3];
    Reflector reflector;
    public EnigmaMachine(){
        plugboard = new Plugboard();
    }

    public void addPlug(char end1, char end2){
        plugboard.addPlug(end1, end2);
    }

    public void clearPlugboard(){
        plugboard.clear();
    }

    public void addRotor(BasicRotor rotor, int slot){
        rotors[slot] = rotor;
    }

    public BasicRotor getRotor(int slot) {
        return rotors[slot];
    }

    public void addReflector(Reflector reflector){
        this.reflector = reflector;
    }

    public Reflector getReflector(){
        return reflector;
    }

    public void setPosition(int slot, int position){
        rotors[slot].setPosition(position);
    }

    public char encodeLetter(char original){
	char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char newChar = original;
        int temp;
        newChar = plugboard.substitute(newChar);
	rotors[0].substitute(alphabet.indexOf(newChar);

    }
}
