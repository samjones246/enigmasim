/**
 * Class to model and individual plug used by the Enigma Machine
 * Plugs have two ends which correspond to two different letters.
 */
public class Plug {
    private char end1, end2;

    /**
     * Class constructor, sets both ends of the plug.
     * @param end1
     * @param end2
     */
    Plug(char end1, char end2){
        this.end1 = end1;
        this.end2 = end2;
    }

    /**
     * Getter for End 1 of the plug
     * @return End 1 of this plug as a character.
     */
    char getEnd1() {
        return end1;
    }

    /**
     * Getter for End 2 of the plug
     * @return End 2 of this plug as a character.
     */
    char getEnd2() {
        return end2;
    }

    /**
     * Setter for End 1 of this plug
     * @param end1 The character which end 1 should be set to
     */
    public void setEnd1(char end1) {
        this.end1 = end1;
    }

    /**
     * Setter for End 2 of this plug
     * @param end2 The character which end 2 should be set to
     */
    public void setEnd2(char end2) {
        this.end2 = end2;
    }

    /**
     * Encode a given character by checking if it matches either end of this plug and swapping it with the letter
     * at the other end if it does. Otherwise return the original letter.
     * @param letterIn The letter to be checked against this plug.
     * @return The resulting letter after the orignal one has either been substituted or not.
     */
    char encode(char letterIn) {
        char letterOut;
        if (letterIn == end1) {
            letterOut = end2;
        } else if (letterIn == end2) {
            letterOut = end1;
        } else {
            letterOut = letterIn;
        }
        return letterOut;
    }

    /**
     * // Check to see if either end of this plug clashes with that of another plug.
     * @param plugin The plug to check this one against.
     * @return Boolean representation of whether this plug clashes with the specified plug.
     */
    boolean clashesWith(Plug plugin){
        return hasEnd(plugin.getEnd1()) || hasEnd(plugin.getEnd2());
    }

    /**
     * Check if either end of this plug equals a given character
     * @param end The character to check against the ends of this plug
     * @return True if this plug has the specified character as an end, False if not.
     */
    boolean hasEnd(char end){
        return end1 == end || end2 == end;
    }
}
