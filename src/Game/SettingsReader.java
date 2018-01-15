package Game;

import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SettingsReader {

    private String path;
    private int size;
    private Color colourOne;
    private Color colourTwo;


    /**
     * Creates the settingsreader
     * @param p - the path
     */
    public SettingsReader(String p) {
        path = p;
    }

    /**
     * Reads from the file
     */
    public void readFile() {
        String[] settings = new String[4];
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            br.readLine();
            for (int i = 0; i < 3; i++) {
                line = br.readLine();
                settings[i] = line;
            }
            br.readLine();
            br.readLine();
            int i = 0;
            while (i < settings.length && (line = br.readLine()) != null) {
                if (!line.equals(""))
                    settings[i] = line;
                i++;
            }
            parseSettings(settings);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert from string to setting cast
     * @param settings - list of strings
     */
    private void parseSettings(String[] settings) {
        //firstPlayer = settings[0];
        colourOne = Color.web(settings[0]);
        colourTwo = Color.web(settings[1]);
        size = Integer.parseInt(settings[2]);
    }

    /**
     * Gets the size of the board
     * @return size
     */
    public int getSize() {
        return size;
    }


    /**
     * Gets the first player colour
     * @return colourOne
     */
    public Color getColourOne() {
        return colourOne;
    }

    /**
     * Gets the second player colour
     * @return colourTwo
     */
    public Color getColourTwo() {
        return colourTwo;
    }
}
