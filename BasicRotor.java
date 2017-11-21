/**
 * Created by sj2g17 on 16/11/17.
 */
public class BasicRotor extends Rotor {
    public BasicRotor(String type) throws Exception{
        this.name = type;
        switch (name){
            case "I":
                mapping = new int[] { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
                break;
            case "II":
                mapping = new int[] { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
                break;
            case "III":
                mapping = new int[] { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
                break;
            case "IV":
                mapping = new int[] {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 };
                break;
            case "V":
                mapping = new int[] { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };
                break;
            default:
                throw new Exception("Rotor type must be in {'I', 'II', 'III', 'IV', 'V'}");
        }
    }

    @Override
    public void initialise(String name) throws Exception {

    }

    @Override
    public int substitute(int original) {
        int altered = original - position;
        if(altered < 0){
            altered = ROTORSIZE + altered;
        }
        altered = mapping[altered] + position;
        if(altered>=ROTORSIZE){
            altered = altered-ROTORSIZE;
        }
        return (altered);
    }
    public int substituteBack(int original){
        int[] inverseMapping = new int[ROTORSIZE];
        for(int i=0;i<ROTORSIZE;i++){
            inverseMapping[mapping[i]] = i;
        }
        int altered = original - position;
        if(altered < 0){
            altered = ROTORSIZE + altered;
        }
        altered = inverseMapping[altered] + position;
        if(altered>=ROTORSIZE){
            altered = altered-ROTORSIZE;
        }
        return (altered);
    }

    public void rotate(){
        int tempPos = position;
        tempPos++;
        if (tempPos == ROTORSIZE){
            tempPos = 0;
        }
        position = tempPos;
    }
}
