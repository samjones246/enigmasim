/**
 * Class to model the plugboard component of the Enigma Machine
 * A plugboard acts as a container for all the plugs used to substitute characters. There can be up to 13 plugs, none
 * of which can overlap.
 */
public class Plugboard {
    private Plug[] plugs = new Plug[13];
    /**
     * Attempt to add a new plug to the Plugboard.
     * Will not add if the new plug clashes with an existing one.
     */
    boolean addPlug(char end1, char end2) {
        Plug newPlug = new Plug(end1, end2);
        for (Plug onBoard : plugs) {
            if (onBoard != null) {
                if (onBoard.clashesWith(newPlug)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < plugs.length; i++) {
            if (plugs[i] == null) {
                plugs[i] = newPlug;
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the number of plugs by counting non-null objects in plugs array.
     * @return The number of plugs on the plugboard;
     */
    int getNumPlugs(){
        int numPlugs = 0;
        for(Plug plug : plugs){
            if (plug != null) {
                numPlugs++;
            }
        }
        return numPlugs;
    }

    /**
     * Clears all plugs from the plugboard.
     */
    void clear(){
        plugs = new Plug[13];
    }

    /**
     * Substitute a given character if it matches an end of a plug on the plugboard
     * @param original The character to be substituted.
     * @return The substituted character.
     */
    char substitute(char original){
        char output = original;
        for(Plug plug: plugs){
            if(plug !=null && plug.hasEnd(original)) {
                output = plug.encode(original);
            }
        }
        return output;
    }

    /**
     * Getter for the array of plugs attached to this plugboard
     * @return The plugs attached to this plugboard as an array of Plugs.
     */
    public Plug[] getPlugs() {
        return plugs;
    }
}
