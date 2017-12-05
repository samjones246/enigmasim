public class Main {
    public static void main(String[] args) throws Exception{
        EMInterface emInterface = new EMInterface();
        emInterface.menu();
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
