public class Plug {
    private char end1, end2;
    Plug(char end1, char end2){
        this.end1 = end1;
        this.end2 = end2;
    }

    public char getEnd1() {
        return end1;
    }

    public char getEnd2() {
        return end2;
    }

    public void setEnd1(char end1) {
        this.end1 = end1;
    }

    public void setEnd2(char end2) {
        this.end2 = end2;
    }

    // Encode a given character by checking if it matches either end of this plug and swapping it with the letter
    // at the other end if it does. Otherwise return the original letter.
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

    // Check to see if either end of this plug clashes with that of another plug
    boolean clashesWith(Plug plugin){
        char[] pluginEnds = {plugin.getEnd1(), plugin.getEnd2()};
        for(char end : pluginEnds){
            if(end == end1 || end == end2){
                return true;
            }
        }
        return false;
    }
}
