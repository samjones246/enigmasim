/**
 * Rotor used in the Enigma Machine.
 * This is the base class for different types of rotors.
 */
public abstract class Rotor {
    String name;
    int position = 0, ROTORSIZE = 26;
    int[] mapping;

    /**
     * Getter for the rotor position
     * @return The position of this rotor.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Setter for the rotor position.
     * @param position The position which this rotor should be set to.
     * @throws Exception Thrown if the specified position is not from 0-25.
     */
    public void setPosition(int position) throws Exception{
        if (position<ROTORSIZE){
            this.position = position;
        }else{
            throw new Exception("Invalid Rotor Position!");
        }
    }

    /**
     * Used to initialise a rotor with a mapping based on the specified type.
     * @param name The type of the rotor.
     * @throws Exception Thrown if there is an error in setting the mapping
     */
    public abstract void initialise(String name) throws Exception;

    /**
     * Uses this rotor to substitute an integer representation of a character for another.
     * @param original The character to be substituted, in integer form.
     * @return The substituted character as an integer
     * @throws Exception Thrown if there is a problem substituting the integer
     */
    public abstract int substitute(int original) throws Exception;
}
