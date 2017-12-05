/**
 * Class to model the reflector component of the Enigma Machine
 * The reflector is a type of Rotor so extends the Rotor class. It has two possible mappings and must be initialised
 * before use.
 */
public class Reflector extends Rotor {

    /**
     * Sets the mapping of the reflector based on the specified type.
     * @param type The reflector type.
     * @throws Exception Thrown if the reflector type is not ReflectorI or ReflectorII
     */
    @Override
    public void initialise(String type) throws Exception {
        this.name = type;
        switch (this.name){
            case "ReflectorI":
                mapping = new int[]{24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19};
                break;
            case "ReflectorII":
                mapping = new int[]{5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11};
                break;
            default:
                throw new Exception("Invalid rotor type!");
        }
    }

    /**
     * Uses this reflector to substitute an integer representation of a character
     * @param original The character to be substituted, in integer form.
     * @return The substituted character
     * @throws Exception Thrown if the integer given is not in the range 0-25
     */
    @Override
    public int substitute(int original) throws Exception{
        if(mapping == null){
            throw new Exception("Reflector has not yet been initialised!");
        }else {
            return mapping[original];
        }
    }
}
