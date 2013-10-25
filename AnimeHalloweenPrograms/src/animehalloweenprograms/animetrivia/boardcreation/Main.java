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

         if (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
         }

         String temp = "";

         while (fileScanner.hasNextLine()) {
            Category newCategory = new Category();
            String[] newQuestions = new String[TOTAL_QUESTIONS];
            String[] newAnswers = new String[TOTAL_QUESTIONS];
            String line = fileScanner.nextLine();

            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            temp = lineScanner.next();
            newCategory.setDifficulty(lineScanner.nextInt()); //edit this so it's int

            //next is title of category, 100, first q, first answer
            //description,200,2nd q, 2nd answer
            //,300,3rd q, 3rd ans
            //etc
            lineScanner.close(); //incorrect???

            for (int i = 0; i < 5; i++) {
               line = fileScanner.nextLine();
               lineScanner = new Scanner(line);
               lineScanner.useDelimiter(",");

               if (i == 0) {
                  newCategory.setTitle(lineScanner.next());
               } else if (i == 1) {
                  lineScanner.useDelimiter("\"");
                  newCategory.setDescription(lineScanner.next());
                  lineScanner.useDelimiter(",");
                  temp = lineScanner.next();
               }

               //rewrite so delimiter is ALWAYS comma. Handle if it's quotes using
               //split() and checking first char of string.
               temp = lineScanner.next();

               lineScanner.useDelimiter("\"");
               temp = lineScanner.next();
               System.out.println("i=" + i + ", category=" + newCategory.getTitle());
               newQuestions[i] = lineScanner.next();

               lineScanner.useDelimiter(",");
               temp = lineScanner.next();
               newAnswers[i] = lineScanner.next();
               temp = "";
            }

            lineScanner.close();
            
         }
         fileScanner.close();
      } catch (FileNotFoundException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
