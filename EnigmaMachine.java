import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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

    public boolean addPlug(char end1, char end2){
        return plugboard.addPlug(end1, end2);
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

    public void setPosition(int slot, int position) throws Exception{
        rotors[slot].setPosition(position);
    }

    public char encodeLetter(char original) throws Exception{
        char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char newChar = Character.toUpperCase(original);
        int numrep = 0;
        newChar = plugboard.substitute(newChar);
        for(int i=0;i<alphabet.length;i++){
            if(alphabet[i] == newChar) {
                numrep = i;
            }
        }
        for(int i=0;i<rotors.length;i++){
            numrep=rotors[i].substitute(numrep);
        }
        numrep = reflector.substitute(numrep);
        for(int i =2;i>-1;i--){
            numrep = rotors[i].substituteBack(numrep);
        }
        newChar = alphabet[numrep];
        newChar = plugboard.substitute(newChar);
        rotors[0].rotate();
        return newChar;
    }

}
