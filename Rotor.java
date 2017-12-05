/**
 * Created by sj2g17 on 16/11/17.
 */
public abstract class Rotor {
    String name;
    int position = 0, ROTORSIZE = 26;
    int[] mapping;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) throws Exception{
        if (position<ROTORSIZE){
            this.position = position;
        }else{
            throw new Exception("Invalid Rotor Position!");
        }
    }

    public abstract void initialise(String name) throws Exception;
    public abstract int substitute(int original) throws Exception;
}
