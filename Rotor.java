/**
 * Created by sj2g17 on 16/11/17.
 */
public abstract class Rotor {
    String name;
    int position, ROTORSIZE = 26;
    int[] mapping;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public abstract void initialise(String name) throws Exception;
    public abstract int substitute(int original);
}
