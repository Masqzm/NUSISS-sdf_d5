package game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

// To read game sim test (text) given
public class ReadTest {
    public String[] gridSize;
    public String[] gridStart;
    public List<String> gridData = new ArrayList<>();

    public void read(String inFile) throws FileNotFoundException, IOException {
        // Open input file for reading
        Reader reader = new FileReader(inFile);
        BufferedReader br = new BufferedReader(reader);
        
        String line;    // to store current line being read
        boolean readingData = false;    // to be toggled on when reading data (cell config)
        
        while((line = br.readLine()) != null)       // read line if it exists in input file
        {
            if(line.startsWith("#"))
                continue;   // skip comments

            // Process line
            //String toRemove = "\\p{Punct}"; // matches any char in: !”#$%&'()*+,-./:;<=>?@[\]^_`{|}~:
            //String processed = (line.toUpperCase() + "\n").replaceAll(toRemove, "");

            if(!readingData)
            {
                // Split line command (e.g. GRID) from value (5 5)
                String[] lineSplit = line.split(" ", 2);

                // First word of text is a command, to get rest of it as param/value 
                switch (lineSplit[0].toUpperCase()) {
                    case "GRID":
                        gridSize = lineSplit[1].split(" ");
                        break;

                    case "START":
                        gridStart = lineSplit[1].split(" ");
                        break;

                    case "DATA":
                        readingData = true;
                        break;
                
                    default:
                        break;
                }
            }
            else
                gridData.add(line);     // add data row (current line)
        }

        // Close file
        reader.close();
    }
}
