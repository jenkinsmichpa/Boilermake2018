package boilermake2018;

import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class GeneralTable {

    private ArrayList<String> dictionary;
    private ArrayList<ArrayList<String>> data;
    Random ran;

    //Constructors
    public GeneralTable() {
        dictionary = new ArrayList<>();
        data = new ArrayList<>();
        ran = new Random();
    }

    public GeneralTable(String filePath) throws FileNotFoundException {
        this();
        importTable(filePath);
    }

    //Import and export
    public String cleanString(String str) {
        return str.replaceAll("[^\\x00-\\x7F]", "").replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "").replaceAll("\\p{C}", "").trim();
    }

    public boolean importTable(String filePath) throws FileNotFoundException {
        Scanner csvScanner = new Scanner(new File(filePath));
        while (csvScanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(csvScanner.nextLine());
            lineScanner.useDelimiter(",");
            if (lineScanner.hasNext()) {
                String newCatagory = cleanString(lineScanner.next());
                addCatagory(newCatagory);
                while (lineScanner.hasNext()) {
                    addDatum(newCatagory, cleanString(lineScanner.next()));
                }
            }
            lineScanner.close();
        }
        csvScanner.close();
        return true;
    }

    public boolean exportTable(String filePath) throws IOException {
        FileWriter csvWriter = new FileWriter(new File(filePath));
        for (String catagory : dictionary) {
            csvWriter.append(catagory);
            for (String datum : getData(catagory)) {
                csvWriter.append("," + datum);
            }
            csvWriter.append("\n");
        }
        csvWriter.close();
        return true;
    }

    //Find
    public int findCatagory(String catagory) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).equals(catagory)) {
                return i;
            }
        }
        return -1;
    }

    public int findDatum(int catagory, String datum) {
        String[] dataSet = getData(catagory);
        for (int i = 0; i < dataSet.length; i++) {
            if (dataSet[i].equals(datum)) {
                return i;
            }
        }
        return -1;
    }

    public int findDatum(String catagory, String datum) {
        return findDatum(findCatagory(catagory), datum);
    }

    //Add
    public boolean addCatagory(String newCatagory) {
        if (findCatagory(newCatagory) == -1) {
            dictionary.add(newCatagory);
            data.add(new ArrayList());
            return true;
        }
        return false;
    }

    public boolean addDatum(int catagory, String newDatum) {
        data.get(catagory).add(newDatum);
        return true;
    }

    public boolean addDatum(String catagory, String newDatum) {
        return addDatum(findCatagory(catagory), newDatum);
    }

    //Get
    public String[] getCatagories() {
        return dictionary.toArray(new String[0]);
    }

    public String getCatagory(int catagory) {
        return dictionary.get(catagory);
    }

    public String[] getData(int catagory) {
        ArrayList<String> dataSet = data.get(catagory);
        String[] dataSetString = new String[dataSet.size()];
        for (int i = 0; i < dataSet.size(); i++) {
            dataSetString[i] = dataSet.get(i);
        }
        return dataSetString;
    }

    public String[] getData(String catagory) {
        return getData(findCatagory(catagory));
    }

    public String getDatum(int catagory, int datum) {
        return (String) data.get(catagory).get(datum);
    }

    public String getDatum(String catagory, int datum) {
        return getDatum(findCatagory(catagory), datum);
    }

    //Remove
    public boolean removeCatagory(int catagory) {
        dictionary.remove(catagory);
        data.remove(catagory);
        return true;
    }

    public boolean removeCatagory(String catagory) {
        return removeCatagory(findCatagory(catagory));
    }

    public boolean removeDatum(int catagory, int datum) {
        data.get(catagory).remove(datum);
        return true;
    }

    public boolean removeDatum(int catagory, String datum) {
        return removeDatum(catagory, findDatum(catagory, datum));
    }

    public boolean removeDatum(String catagory, int datum) {
        return removeDatum(findCatagory(catagory), datum);
    }

    public boolean removeDatum(String catagory, String datum) {
        return removeDatum(findCatagory(catagory), findDatum(catagory, datum));
    }

    //Get random
    public String getRandomDatum(int catagory) {
        ArrayList<String> dataSet = data.get(catagory);
        return dataSet.get(ran.nextInt(dataSet.size()));
    }

    public String getRandomDatum(String catagory) {
        return getRandomDatum(findCatagory(catagory));
    }

    public String getRandomDatum() {
        return getRandomDatum(ran.nextInt(dictionary.size()));
    }

    public String[] getRandomData(int catagory, int num) {
        String[] randomData = new String[num];
        for (int i = 0; i < randomData.length;) {
            String newRandomDatum = getRandomDatum(catagory);
            boolean repeat = false;
            for (String randomDatum : randomData) {
                if (randomDatum != null && randomDatum.equals(newRandomDatum)) {
                    repeat = true;
                }
            }
            if (!repeat) {
                randomData[i] = newRandomDatum;
                i++;
            }
        }
        return randomData;
    }

    public String[] getRandomData(String catagory, int num) {
        return getRandomData(findCatagory(catagory), num);
    }

    public String[] getRandomData(int num) {
        return getRandomData(ran.nextInt(dictionary.size()), num);
    }

    public String[] getRandomMixedData(int num) {
        String[] randomData = new String[num];
        for (int i = 0; i < randomData.length;) {
            String newRandomDatum = getRandomDatum(ran.nextInt(dictionary.size()));
            boolean repeat = false;
            for (String randomDatum : randomData) {
                if (randomDatum != null && randomDatum.equals(newRandomDatum)) {
                    repeat = true;
                }
            }
            if (!repeat) {
                randomData[i] = newRandomDatum;
                i++;
            }
        }
        return randomData;
    }
}
