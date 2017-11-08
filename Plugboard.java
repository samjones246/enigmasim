public class Plugboard {
    private Plug[] plugs = new Plug[13];
    // Attempt to add a new plug to the Plugboard. Will not add if there is no room on the board or if the new plug
    // clashes with an existing one.
    public boolean addPlug(char end1, char end2) {
        Plug newPlug = new Plug(end1, end2);
        for (Plug onBoard : plugs) {
            if (onBoard.clashesWith(newPlug)) {
                return false;
            }
        }
        Integer indexToInsert = null;
        for (int i = 0; i < plugs.length; i++) {
            if (plugs[i] == null && indexToInsert == null) {
                indexToInsert = i;
            }
        }
        if (!(indexToInsert == null)){
            plugs[indexToInsert] = newPlug;
            return true;
        }else{
            return false;
        }
    }
}
