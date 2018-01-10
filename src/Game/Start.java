package Game;

public class Start {

    /**
     * Game.Start program.
     */
    public void init() {
        SettingsReader sr = new SettingsReader("./src/Settings.txt");
        sr.readFile();
        GameFlow gf = new GameFlow(sr.getSize(), sr.getFirstPlayer(), sr.getColourOne(), sr.getColourTwo());
        gf.run();
    }
}
