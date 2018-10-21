package boilermake2018;

import java.io.IOException;
import java.util.Scanner;

public class Boilermake2018 {

    public static void main(String[] args) throws IOException {
        GeneralTable gt = new GeneralTable();
        Scanner scan = new Scanner(System.in);
        String[] commands = {"help", "printTable", "importTable", "exportTable", "addCatagory", "addDatum", "removeCatagory", "removeDatum", "getRandomDataSCat", "exit"};

        gt.importTable("src\\resources\\test.csv");

        System.out.println("[General Table Text Frontend]\n\nNote: Refer to catagories, data, and commands by name, not index.\nNote: Use help for help.");
        for (;;) {
            //Accept command
            System.out.print("\nCommand: ");
            switch (scan.next()) {
                case "help":
                    //List commands
                    for (int i = 0; i < commands.length; i++) {
                        System.out.println(i + ") " + commands[i]);
                    }
                    break;
                case "printTable":
                    //Print table
                    for (int i = 0; i < gt.getCatagories().length; i++) {
                        System.out.println(i + ") " + gt.getCatagories()[i]);
                        for (int j = 0; j < gt.getData(i).length; j++) {
                            System.out.println("\t" + j + ") " + gt.getData(i)[j]);
                        }
                    }
                    break;
                case "importTable":
                    //Import table
                    System.out.print("Path: ");
                    gt.importTable(scan.next());
                    break;
                case "exportTable":
                    //Export table
                    System.out.print("Path: ");
                    gt.exportTable(scan.next());
                    break;
                case "addCatagory":
                    //Add catagory
                    System.out.print("New Catagory: ");
                    gt.addCatagory(scan.next());
                    break;
                case "addDatum":
                    //Add datum
                    System.out.print("New Catagory: ");
                    String newCatagory = scan.next();
                    System.out.print("New Datum: ");
                    gt.addDatum(newCatagory, scan.next());
                    break;
                case "removeCatagory":
                    //Remove catagory
                    System.out.print("Catagory: ");
                    gt.removeCatagory(scan.next());
                    break;
                case "removeDatum":
                    //Remove datum
                    System.out.print("Catagory: ");
                    String catagory = scan.next();
                    System.out.print("Datum: ");
                    gt.removeDatum(catagory, scan.next());
                case "getRandomDataSCat":
                    //Get random data from set catagory
                    System.out.print("Catagory: ");
                    String catagory1 = scan.next();
                    System.out.print("Number of data: ");
                    String[] randomData = gt.getRandomData(catagory1, Integer.parseInt(scan.next()));
                    for (int i = 0; i < randomData.length; i++) {
                        System.out.println(i + ") " + randomData[i]);
                    }
                    break;
                case "getRandomDataRCat":
                    //Get random from random catagory
                    System.out.print("Number of data: ");
                    String[] randomData1 = gt.getRandomData(Integer.parseInt(scan.next()));
                    for (int i = 0; i < randomData1.length; i++) {
                        System.out.println(i + ") " + randomData1[i]);
                    }
                    break;
                case "getRandomDataMCat":
                    //Get random from many random catagories
                    System.out.print("Number of data: ");
                    String[] randomData2 = gt.getRandomMixedData(Integer.parseInt(scan.next()));
                    for (int i = 0; i < randomData2.length; i++) {
                        System.out.println(i + ") " + randomData2[i]);
                    }
                    break;
                case "exit":
                    //Exit the program
                    System.exit(0);
                    break;
            }
        }
    }
}
