package Game;

import javafx.scene.paint.Color;
import java.io.*;

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
            if (!file.exists())
                file = writeInitFile(file);
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
     * writes the default version of the file if it doesn't exist.
     * @param file the file to write
     */
    private File writeInitFile(File file) throws Exception {
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        pw.println("DEFAULT");
        pw.println("black");
        pw.println("white");
        pw.println("8");
        pw.println();
        pw.println("SETTINGS");
        pw.flush();
        return file;
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
