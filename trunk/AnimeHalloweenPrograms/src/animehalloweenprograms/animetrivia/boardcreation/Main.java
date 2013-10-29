/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animehalloweenprograms.animetrivia.boardcreation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pdouglas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static final int TOTAL_CATEGORIES = 5;
    public static final int TOTAL_QUESTIONS = 5;
    private static ArrayList<Category> categoryList;
    private static Category[][][] prelimRounds;
    private static Category[][] finals;
    private static int numBoards, numRounds, numFinals;

    public static void main(String[] args) {
        //int numBoards = 2, numRounds = 3, numFinals = 3;
        String directoryName = "";
        boolean flag = false;
        Scanner inputScanner = new Scanner(System.in);
        numBoards = 2;
        numRounds = 3;
        numFinals = 3;

        categoryList = new ArrayList<Category>();
        prelimRounds = new Category[numRounds][numBoards][TOTAL_CATEGORIES];
        finals = new Category[numFinals][TOTAL_CATEGORIES];

        System.out.println("===Adding Categories===");
        System.out.println("Specify the directory with the trivia .csv files.");
        directoryName = inputScanner.nextLine();

        //build category database
        File dir = new File(directoryName);
        if (dir.isDirectory()) {
            for (File trivia : dir.listFiles()) {
                buildCategoryList(trivia);
            }
        }

        System.out.println("===All Categories Added===\n");
        flag = false;

        do {
            flag = true;
            System.out.println("Specify how you want to build the boards: ");
            System.out.println("\t'1': One Board\n\t'2': One Round");
            System.out.println("\t'3': 3 Rounds and Finals");
            System.out.println("\t'4': pre-built rounds");

            //get input
            char choice = inputScanner.next().charAt(0);

            switch (choice) {
                case '1':
                    numBoards = 1;
                    numRounds = 1;
                    numFinals = 0;
                    break;
                case '2':
                    numRounds = 1;
                    numFinals = 0;
                    break;
                case '3':
                    break;
                case '4':
                    System.out.println("Specify the file with the pre-made boards.");
                    String file = inputScanner.next();
                    makeBoards(file);
                    break;
                default:
                    System.out.println("Key incorrect. Please try again.");
                    flag = false;
            }
        } while (!flag);

        inputScanner.close();
    }

    private static void buildCategoryList(File filename) {
        try {
            Scanner fileScanner = new Scanner(filename);

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            String temp = "";

            while (fileScanner.hasNextLine()) {
                Category newCategory = new Category();
                String[] newQuestions = new String[TOTAL_QUESTIONS];
                String[] newAnswers = new String[TOTAL_QUESTIONS];
                String[] newSeries = new String[TOTAL_QUESTIONS];
                String line = fileScanner.nextLine();
                boolean needsToFlip = false;

                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                temp = lineScanner.next();
                newCategory.setDifficulty(lineScanner.nextInt()); //edit this so it's int
                if (lineScanner.hasNext()) {
                    needsToFlip = true;
                }
                //next is title of category, 100, first q, first answer
                //description,200,2nd q, 2nd answer
                //,300,3rd q, 3rd ans
                //etc
                lineScanner.close(); //incorrect???

                for (int i = 0; i < 5; i++) {
                    String text = "";
                    line = fileScanner.nextLine();
                    boolean ignoreCommas = false;

                    lineScanner = new Scanner(line);
                    lineScanner.useDelimiter(",");

                    if (i == 0) {
                        String delimitedText = lineScanner.next();
                        boolean flag = false;

                        if (delimitedText.contains("\"")) {
                            while (!flag) {
                                String newPart = "," + lineScanner.next();
                                if (newPart.contains("\"")) {
                                    flag = true;
                                }
                                delimitedText += newPart;

                                if (flag) {
                                    delimitedText = delimitedText.split("\"")[1];
                                }
                            }
                        }

                        newCategory.setTitle(delimitedText);
                    } else if (i == 1) {
                        String delimitedText = lineScanner.next();
                        boolean flag = false;

                        if (delimitedText.contains("\"")) {
                            while (!flag) {
                                String newPart = "," + lineScanner.next();
                                if (newPart.contains("\"")) {
                                    flag = true;
                                }
                                delimitedText += newPart;

                                if (flag) {
                                    delimitedText = delimitedText.split("\"")[1];
                                }
                            }
                        }
                        newCategory.setDescription(delimitedText);
                    } else {
                        //temp = lineScanner.next();
                    }

                    //rewrite so delimiter is ALWAYS comma. Handle if it's quotes using
                    //split() and checking first char of string.
                    temp = lineScanner.next();

                    System.out.println("i=" + i + ", category=" + newCategory.getTitle());
                    String delimitedText = "";

                    if (needsToFlip) {
                        delimitedText = lineScanner.next();
                        boolean flag = false;

                        if (delimitedText.contains("\"")) {
                            while (!flag) {
                                String newPart = "," + lineScanner.next();
                                if (newPart.contains("\"")) {
                                    flag = true;
                                }
                                delimitedText += newPart;

                                if (flag) {
                                    delimitedText = delimitedText.split("\"")[1];
                                }
                            }
                        }
                        newQuestions[i] = delimitedText;

                        if (delimitedText.contains("(")) {
                            newSeries[i] = newQuestions[i].split("\\(")[1];
                            newSeries[i] = newSeries[i].substring(0,
                                    newSeries[i].length() - 1);
                        } else {
                            newSeries[i] = newQuestions[i];
                        }

                        //add the answer
                        delimitedText = lineScanner.next();
                        flag = false;
                        if (delimitedText.contains("\"")) {
                            while (!flag) {
                                String newPart = "," + lineScanner.next();
                                if (newPart.contains("\"")) {
                                    flag = true;
                                }
                                delimitedText += newPart;

                                if (flag) {
                                    delimitedText = delimitedText.split("\"")[1];
                                }
                            }
                        }
                        newAnswers[i] = delimitedText;
                    } else {
                        delimitedText = lineScanner.next();
                        boolean flag = false;

                        if (delimitedText.contains("\"")) {
                            while (!flag) {
                                String newPart = "," + lineScanner.next();
                                if (newPart.contains("\"")) {
                                    flag = true;
                                }
                                delimitedText += newPart;

                                if (flag) {
                                    delimitedText = delimitedText.split("\"")[1];
                                }
                            }
                        }
                        newQuestions[i] = delimitedText;

                        //add the answer
                        delimitedText = lineScanner.next();
                        flag = false;
                        if (delimitedText.contains("\"")) {
                            while (!flag) {
                                String newPart = "," + lineScanner.next();
                                if (newPart.contains("\"")) {
                                    flag = true;
                                }
                                delimitedText += newPart;

                                if (flag) {
                                    delimitedText = delimitedText.split("\"")[1];
                                }
                            }
                        }

                        newAnswers[i] = delimitedText;
                        if (delimitedText.contains("(")) {
                            newSeries[i] = newAnswers[i].split("\\(")[1];
                            newSeries[i] = newSeries[i].substring(0,
                                    newSeries[i].length() - 1);
                        } else {
                            newSeries[i] = newAnswers[i];
                        }
                    }
                }

                newCategory.setAnswers(newAnswers);
                newCategory.setQuestions(newQuestions);
                newCategory.setSeries(newSeries);

                newCategory = changeAmpersands(newCategory);

                categoryList.add(newCategory);

                lineScanner.close();

            }
            fileScanner.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void makeBoards(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            String temp = fileScanner.nextLine();
            int round = 0, board = 0, catNum = 0;

            for (int i = 0; i < 5; i++) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                for (int j = 0; j < 9; j++) {
                    lineScanner.next();
                    String catTitle = lineScanner.next();
                    Category tempCat = new Category();
                    tempCat.setTitle(catTitle);

                    for (Category cat : categoryList) {
                        if (cat.equals(tempCat) && j < 6) {
                            prelimRounds[round][board++][i] = cat;
                            //categoryList.remove(cat);

                            if (board % numBoards == 0) {

                                board = 0;
                                round++;

                                if (round % numRounds == 0 && round != 0) {
                                    round = board = 0;
                                }
                            }

                            break;
                        } else if (cat.equals(tempCat)) {
                            finals[board++][i] = cat;
                            //categoryList.remove(cat);


                            if (board % numFinals == 0 && board != 0) {
                                board = 0;
                            }
                            break;
                        }

                    }
                }
            }

            //printBoards();

            //create XML for prelim rounds
            for (int i = 0; i < numRounds; i++) {
                for (int j = 0; j < numBoards; j++) {
                    createXMLPrelim("R" + Integer.toString(i + 1)
                            + "B" + Integer.toString(j + 1), i, j);
                }
            }

            //create XML for final rounds
            for (int i = 0; i < numFinals; i++) {
                createXMLFinal("RFB" + Integer.toString(i + 1), i);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void printBoards() {
        
        /*for (int i = 0; i < 5; i++) {
            int round = 0, board = 0;
            for (int j = 0; j < 9; j++) {
                if (j < 6) {
                    System.out.print(prelimRounds[round][board++][i] + "\t");

                    if (board % numBoards == 0) {
                        board = 0;
                        round++;

                        if (round % numRounds == 0) {
                            round = board = 0;
                        }
                    }
                } else {
                    System.out.print(finals[board++][i] + "\t");
                }
            }

            System.out.println();
        }*/
    }

    public static void createXMLPrelim(String filename, int round, int board) {
        FileWriter xml = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            String year = dateFormat.format(Calendar.getInstance().getTime());

            //make the file format correct
            (new File("files/animeTrivia/" + year + "/boards/"
                    + filename + ".triviaqm/media")).mkdirs();
            xml = new FileWriter("files/animeTrivia/" + year + "/boards/"
                    + filename + ".triviaqm/trivia.qinfo");

            BufferedWriter out = new BufferedWriter(xml);
            //use out.write(<string>) to write to file

            //insert the heading stuff for the .xml file
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            out.write("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\""
                    + " \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n");
            out.write("<plist version=\"1.0\">\n");

            //begin tags... ugh I hate xml -__-
            out.write("<dict>\n");
            out.write("\t<key>boards</key>\n");
            out.write("\t<array>\n");
            out.write("\t\t<dict>\n");
            out.write("\t\t\t<key>categories</key>\n");
            out.write("\t\t\t<array>\n");

            //begin for loop for questions
            for (int i = 0; i < TOTAL_CATEGORIES; i++) {
                out.write("\t\t\t\t<dict>\n");
                out.write("\t\t\t\t\t<key>questions</key>\n");
                out.write("\t\t\t\t\t<array>\n");

                for (int j = 0; j < TOTAL_QUESTIONS; j++) {
                    out.write("\t\t\t\t\t\t<dict>\n");
                    out.write("\t\t\t\t\t\t\t<key>answer</key>\n");
                    out.write("\t\t\t\t\t\t\t<string>"
                            + prelimRounds[round][board][i].getAnswers()[j] + "</string>\n");
                    out.write("\t\t\t\t\t\t\t<key>question</key>\n");
                    out.write("\t\t\t\t\t\t\t<string>"
                            + prelimRounds[round][board][i].getQuestions()[j] + "</string>\n");
                    out.write("\t\t\t\t\t\t\t<key>questionType</key>\n");
                    out.write("\t\t\t\t\t\t\t<integer>0</integer>\n");
                    out.write("\t\t\t\t\t\t\t<key>slowReveal</key>\n");
                    out.write("\t\t\t\t\t\t\t<false/>\n");
                    out.write("\t\t\t\t\t\t\t<key>used</key>\n");
                    out.write("\t\t\t\t\t\t\t<false/>\n");
                    out.write("\t\t\t\t\t\t</dict>\n");
                }

                out.write("\t\t\t\t\t</array>\n");
                out.write("\t\t\t\t\t<key>title</key>\n");
                out.write("\t\t\t\t\t<string>"
                        + prelimRounds[round][board][i].getTitle() + "</string>\n");
                out.write("\t\t\t\t</dict>\n");
            }

            out.write("\t\t\t</array>\n");
            out.write("\t\t\t<key>title</key>\n");
            out.write("\t\t\t<string>" + filename + "</string>\n");
            out.write("\t\t</dict>\n");
            out.write("\t</array>\n");
            out.write("</dict>\n");
            out.write("</plist>\n");

            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                xml.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



    }

    public static void createXMLFinal(String filename, int board) {
        FileWriter xml = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy");
            String year = dateFormat.format(Calendar.getInstance().getTime());

            //make the file format correct
            (new File("files/animeTrivia/" + year + "/boards/"
                    + filename + ".triviaqm/media")).mkdirs();
            xml = new FileWriter("files/animeTrivia/" + year + "/boards/"
                    + filename + ".triviaqm/trivia.qinfo");

            BufferedWriter out = new BufferedWriter(xml);
            //use out.write(<string>) to write to file

            //insert the heading stuff for the .xml file
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            out.write("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\""
                    + " \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n");
            out.write("<plist version=\"1.0\">\n");

            //begin tags... ugh I hate xml -__-
            out.write("<dict>\n");
            out.write("\t<key>boards</key>\n");
            out.write("\t<array>\n");
            out.write("\t\t<dict>\n");
            out.write("\t\t\t<key>categories</key>\n");
            out.write("\t\t\t<array>\n");

            //begin for loop for questions
            for (int i = 0; i < TOTAL_CATEGORIES; i++) {
                out.write("\t\t\t\t<dict>\n");
                out.write("\t\t\t\t\t<key>questions</key>\n");
                out.write("\t\t\t\t\t<array>\n");

                for (int j = 0; j < TOTAL_QUESTIONS; j++) {
                    out.write("\t\t\t\t\t\t<dict>\n");
                    out.write("\t\t\t\t\t\t\t<key>answer</key>\n");
                    out.write("\t\t\t\t\t\t\t<string>"
                            + finals[board][i].getAnswers()[j] + "</string>\n");
                    out.write("\t\t\t\t\t\t\t<key>question</key>\n");
                    out.write("\t\t\t\t\t\t\t<string>"
                            + finals[board][i].getQuestions()[j] + "</string>\n");
                    out.write("\t\t\t\t\t\t\t<key>questionType</key>\n");
                    out.write("\t\t\t\t\t\t\t<integer>0</integer>\n");
                    out.write("\t\t\t\t\t\t\t<key>slowReveal</key>\n");
                    out.write("\t\t\t\t\t\t\t<false/>\n");
                    out.write("\t\t\t\t\t\t\t<key>used</key>\n");
                    out.write("\t\t\t\t\t\t\t<false/>\n");
                    out.write("\t\t\t\t\t\t</dict>\n");
                }

                out.write("\t\t\t\t\t</array>\n");
                out.write("\t\t\t\t\t<key>title</key>\n");
                out.write("\t\t\t\t\t<string>"
                        + finals[board][i].getTitle() + "</string>\n");
                out.write("\t\t\t\t</dict>\n");
            }

            out.write("\t\t\t</array>\n");
            out.write("\t\t\t<key>title</key>\n");
            out.write("\t\t\t<string>" + filename + "</string>\n");
            out.write("\t\t</dict>\n");
            out.write("\t</array>\n");
            out.write("</dict>\n");
            out.write("</plist>\n");

            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                xml.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static Category changeAmpersands(Category cat) {
        cat.setTitle(addInAmps(cat.getTitle()));
        
        String[] tempQuestions = cat.getQuestions();
        String[] tempAnswers = cat.getAnswers();
        
        for (int i = 0; i < TOTAL_QUESTIONS; i++) {
            tempQuestions[i] = addInAmps(tempQuestions[i]);
            tempAnswers[i] = addInAmps(tempAnswers[i]);
        }
        
        cat.setAnswers(tempAnswers);
        cat.setQuestions(tempQuestions);
        
        return cat;
    }
    
    private static String addInAmps(String title) {
        if (title.contains("&")) {
            String[] temp = title.split("&");
            title = "";
            for (int i = 0; i < temp.length; i++) {
                title += temp[i];
                
                if (i != temp.length - 1)
                    title += "&amp;";
            }
        }
        
        return title;
    }
}
