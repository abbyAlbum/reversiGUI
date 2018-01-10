package Game;

public class main {

    /**
     * Game.main program.
     */
    public static void main(String[] args) {
        SettingsReader sr = new SettingsReader("./src/Settings.txt");
        sr.readFile();
        GameFlow gf = new GameFlow(sr.getSize(), sr.getFirstPlayer(), sr.getColourOne(), sr.getColourTwo());
        gf.run();
    }
}
