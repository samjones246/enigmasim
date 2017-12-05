/**
 * Class to model turnover rotors, which will rotate the rotor after them when they reach a certain position
 */
public class TurnoverRotor extends BasicRotor {
    int turnoverPosition;
    BasicRotor nextRotor;
    /**
     * Class constructor, sets mapping and turnover position according to type.
     * @param type The type of the rotor (I-V).
     * @throws Exception Thrown if the given type is invalid.
     */
    public TurnoverRotor(String type) throws Exception {
        super(type);
        switch (type){
            case "I":
                turnoverPosition = 24;
                break;
            case "II":
                turnoverPosition = 12;
                break;
            case "III":
                turnoverPosition = 3;
                break;
            case "IV":
                turnoverPosition = 17;
                break;
            case "V":
                turnoverPosition = 7;
                break;
        }
    }

    /**
     * Class constructor which sets mapping, turnover position and the reference for the next rotor.
     * @param type The type of the rotor (I-V).
     * @param nextRotor The reference to the next rotor in the EnigmaMachine.
     * @throws Exception Thrown if the given type is invalid.
     */
    public TurnoverRotor(String type, BasicRotor nextRotor) throws Exception{
        this(type);
        this.nextRotor = nextRotor;
    }

    /**
     * Rotates the rotor by one position, and rotates the next rotor if this one has reached its turnover position.
     * Will not try to rotate the next rotor if nextRotor is null.
     */
    @Override
    public void rotate(){
        int tempPos = position;
        tempPos++;
        if (tempPos == ROTORSIZE){
            tempPos = 0;
        }
        position = tempPos;
        if(position==turnoverPosition){
            if(nextRotor instanceof TurnoverRotor) {
                ((TurnoverRotor) nextRotor).rotate();
            }else{
                nextRotor.rotate();
            }
        }
    }

    /**
     * Specifies the rotor which comes after in the EnigmaMachine.
     * @param nextRotor The reference to the next rotor in the EnigmaMachine.
     */
    public void setNextRotor(BasicRotor nextRotor) {
        this.nextRotor = nextRotor;
    }
}
