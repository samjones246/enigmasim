public class Plugboard {
    private Plug[] plugs = new Plug[13];
    // Attempt to add a new plug to the Plugboard. Will not add if the new plug clashes with an existing one.
    boolean addPlug(char end1, char end2) {
        //Create new plug and check for clashes with inserted plugs
        Plug newPlug = new Plug(end1, end2);
        for (Plug onBoard : plugs) {
            if (onBoard != null) {
                if (onBoard.clashesWith(newPlug)) {
                    return false;
                }
            }
        }
        // Insert the new plug into the plugs array
        for (int i = 0; i < plugs.length; i++) {
            if (plugs[i] == null) {
                plugs[i] = newPlug;
                return true;
            }
        }
        return false;
    }
    // Return number of plugs by counting non-null objects in plugs
    int getNumPlugs(){
        int numPlugs = 0;
        for(Plug plug : plugs){
            if (plug != null) {
                numPlugs++;
            }
        }
        return numPlugs;
    }
    void clear(){
        plugs = new Plug[13];
    }

    // Substitute a given character if it matches an end of a plug on the plugboard
    char substitute(char orignal){
        char output = orignal;
        for(Plug plug: plugs){
            if(plug !=null && plug.hasEnd(orignal)) {
                output = plug.encode(orignal);
            }
        }
        return output;
    }

}
