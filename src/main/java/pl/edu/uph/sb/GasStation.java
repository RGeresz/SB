package pl.edu.uph.sb;

import pl.edu.uph.sb.exceptions.BelowZeroException;
import pl.edu.uph.sb.exceptions.DistributorFailureException;

import java.io.*;

public class GasStation implements Serializable {

    private static GasStation instance = null;

    private Station[] stations;

    private GasStation() {
        stations = loadFromFile();
    }

    public static synchronized GasStation getInstance() {
        if (instance == null) {
            instance = new GasStation();
        }
        return instance;
    }

    public Station getStation(int number) {
        return stations[number];
    }

    public static synchronized void saveToFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("gasStation.txt", "UTF-8");
            writer.println(getInstance().getStation(0).toString());
            writer.println(getInstance().getStation(1).toString());
            writer.println(getInstance().getStation(2).toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    public static synchronized Station[] loadFromFile() {
        Station stations[] = new Station[3];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("gasStation.txt"));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (i < 2) {
                    stations[i] = new StationGasAndDiesel(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
                } else {
                    stations[i] = new StationLPG(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                }
                i++;
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return stations;
    }
}
