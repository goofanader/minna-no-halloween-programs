/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animehalloweenprograms.animetrivia.boardcreation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

   public static void main(String[] args) {
      int numBoards = 2, numRounds = 3, numFinals = 3;
      String filename = "";
      boolean flag = false;
      Scanner inputScanner = new Scanner(System.in);

      categoryList = new ArrayList<Category>();

      System.out.println("===Adding Categories===");
      do {
         System.out.println("Specify the file to read. Type 'q' to end.");
         filename = inputScanner.nextLine();

         if (filename.equals("q")) {
            flag = true;
         } else {
            //build category database
            buildCategoryList(filename);
         }

      } while (!flag);

      System.out.println("===All Categories Added===\n");
      flag = false;

      do {
         flag = true;
         System.out.println("Specify how you want to build the boards: ");
         System.out.println("\t'1': One Board\n\t'2': One Round");
         System.out.println("\t'3': 3 Rounds and Finals");

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
            default:
               System.out.println("Key incorrect. Please try again.");
               flag = false;
         }
      } while (!flag);

      inputScanner.close();
   }

   private static void buildCategoryList(String filename) {
      try {
         Scanner fileScanner = new Scanner(new File(filename));

         if (fileScanner.hasNextLine()) fileScanner.nextLine();

         while (fileScanner.hasNextLine()) {
            Category newCategory = new Category();
            String[] newQuestions = new String[TOTAL_QUESTIONS];
            String[] newAnswers = new String[TOTAL_QUESTIONS];
            String line = fileScanner.nextLine();

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            lineScanner.next();
            newCategory.setDifficulty(lineScanner.nextInt()); //edit this so it's int

            //next is title of category, 100, first q, first answer
            //description,200,2nd q, 2nd answer
            //,300,3rd q, 3rd ans
            //etc
            lineScanner.close(); //incorrect???

            line = fileScanner.nextLine();
            lineScanner = new Scanner(line);
            newCategory.setTitle(lineScanner.next());
            lineScanner.next();
            lineScanner.useDelimiter("\"");

            newQuestions[0] = lineScanner.next();
            lineScanner.useDelimiter(",");
            newAnswers[0] = lineScanner.next();
         }
      } catch (FileNotFoundException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
