/**
 * Class to model the Enigma Machine.
 * This is the combination of all the different Enigma Machine components. It can encode a character based on the
 * settings specified in the different components attached to this object.
 */
public class EnigmaMachine {
    Plugboard plugboard;
    BasicRotor[] rotors = new BasicRotor[3];
    Reflector reflector;

    /**
     * Class constructor, creates a plugboard.
     */
    public EnigmaMachine(){
        plugboard = new Plugboard();
    }

    /**
     * Adds a plug to the plugboard using the specified ends.
     * @param end1 The first end of the plug to be added
     * @param end2 The second end of the plug to be added
     * @return True if the plug was added successfully, false if not
     */
    public boolean addPlug(char end1, char end2){
        return plugboard.addPlug(end1, end2);
    }

    /**
     * Removes all plugs from the plugboard.
     */
    public void clearPlugboard(){
        plugboard.clear();
    }

    /**
     * Adds a rotor to the Enigma Machine.
     * @param rotor The rotor to be added.
     * @param slot The slot (0-2) which the rotor should be added to.
     */
    public void addRotor(BasicRotor rotor, int slot){
        rotors[slot] = rotor;
    }

    /**
     * Getter for the rotor in a given slot of the machine.
     * @param slot The slot to get the rotor from
     * @return The rotor object in the specified slot
     */
    public BasicRotor getRotor(int slot) {
        return rotors[slot];
    }

    /**
     * Adds a given reflector to the machine.
     * @param reflector The reflector to be added
     */
    public void addReflector(Reflector reflector){
        this.reflector = reflector;
    }

    /**
     * Getter for the reflector in this machine.
     * @return The reflector in this machine.
     */
    public Reflector getReflector(){
        return reflector;
    }

    /**
     * Setter for the position of a specified rotor.
     * @param slot The slot where the rotor which should be changed is located.
     * @param position The position to change the specified rotor to.
     * @throws Exception Thrown if the given position is invalid.
     */
    public void setPosition(int slot, int position) throws Exception{
        rotors[slot].setPosition(position);
    }

    /**
     * Encode a letter using all the components of this Enigma Machine.
     * Runs the given letter through this machine. including the plugboard, all the rotors and the reflector. Once the
     * letter has been encoded, the first rotor is rotated one position.
     * @param original The letter to be encoded.
     * @return The resulting letter after encoding
     * @throws Exception Thrown if any part of the enigma machine encounters a problem.
     */
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
        if (rotors[0] instanceof TurnoverRotor) {
            ((TurnoverRotor)rotors[0]).rotate();
        }else{
            rotors[0].rotate();
        }
        return newChar;
    }

}
