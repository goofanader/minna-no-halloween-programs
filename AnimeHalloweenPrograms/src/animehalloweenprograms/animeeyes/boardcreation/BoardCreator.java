/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animehalloweenprograms.animeeyes.boardcreation;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Phyllis
 */
public class BoardCreator {

    public static final int MAX_ROUND_IMAGES = 40;
    public static final int MAX_FINALS_IMAGES = 55;
    public static final int NUM_ROUNDS = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rando = new Random((new Date()).getTime());
        boolean hasEnteredInput = false;
        int maxDifficulty = 0, totalImages[] = null, maxImages[] = null;

        //Create a series list for each round and initialize
        ArrayList<ArrayList<String>> seriesList = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 4; i++) {
            seriesList.add(new ArrayList<String>());
        }

        Scanner inSC = new Scanner(System.in);

        //get the max difficulty (starting with 1)
        do {
            System.out.println("Enter in the max difficulty:");

            if (!inSC.hasNextInt()) {
                System.out.println("Please enter a number.");
            } else {
                maxDifficulty = inSC.nextInt();
                totalImages = new int[maxDifficulty];
                maxImages = new int[maxDifficulty];
                hasEnteredInput = true;
            }
        } while (!hasEnteredInput);

        hasEnteredInput = false;
        File dir = null;

        //get the directory name with the difficulties
        do {
            System.out.println("Please type in the directory where the images have"
                    + " been sorted by difficulty.");
            String directory = inSC.next();

            dir = new File(directory);
            if (!dir.isDirectory()) {
                System.out.println("That was not a valid directory, try again.");
            } else {
                hasEnteredInput = true;
            }
        } while (!hasEnteredInput);

        hasEnteredInput = false;
        ArrayList<Integer> percentDifficulties = new ArrayList<Integer>();

        //get the percent of image difficulties for preliminary rounds
        do {
            System.out.println("What percentage of image difficulties"
                    + " do you want in the initial rounds? Separate by comma.");
            String percentages = inSC.nextLine();

            Scanner lineSC = new Scanner(percentages);
            lineSC.useDelimiter(",");

            while (lineSC.hasNextInt()) {
                percentDifficulties.add(lineSC.nextInt());
            }

            if (percentDifficulties.size() == maxDifficulty) {
                hasEnteredInput = true;
            } else {
                System.out.println("Please list the same number of difficulties as max difficulty.");
            }
        } while (!hasEnteredInput);

        //Get the number of images per difficulty
        for (File difficultyFolder : dir.listFiles()) {
            if (difficultyFolder.isDirectory()) {
                String folderName = difficultyFolder.getName();
                int currDifficulty = Integer.parseInt(folderName) - 1;
                totalImages[currDifficulty] = difficultyFolder.list().length;
            }
        }

        //Calculate how many images per difficulty go into each round
        /*maxImages[0][0] = totalImages[0] / NUM_ROUNDS;
         maxImages[0][1] = totalImages[1] / NUM_ROUNDS;
         if (maxImages[0][1] + maxImages[0][0] > MAX_ROUND_IMAGES) {
         maxImages[0][1] = MAX_ROUND_IMAGES - maxImages[0][0];
         }

         if (maxImages[0][0] + maxImages[0][1] < MAX_ROUND_IMAGES) {
         maxImages[0][2] = totalImages[2] / NUM_ROUNDS;
         if (maxImages[0][2] + maxImages[0][1] + maxImages[0][0] > MAX_ROUND_IMAGES) {
         maxImages[0][2] = MAX_ROUND_IMAGES - maxImages[0][0] - maxImages[0][1];
         }
         } else {
         maxImages[0][2] = 0;
         }*/

        int totalImagesInRound = 0;
        for (int i = 0; i < maxDifficulty; i++) {
            maxImages[i] = (totalImages[i] * (/*100 - */percentDifficulties.get(i))) / 100
                    / NUM_ROUNDS;
            if (totalImagesInRound + maxImages[i] > MAX_ROUND_IMAGES) {
                maxImages[i] = 0;
            } else {
                totalImagesInRound += maxImages[i];
            }
        }

        if (totalImagesInRound < MAX_ROUND_IMAGES) {
            for (int i = 0; i < maxDifficulty; i++) {
                if (maxImages[i] * NUM_ROUNDS < totalImages[i]) {
                    maxImages[i] += MAX_ROUND_IMAGES - totalImagesInRound;
                    totalImagesInRound += MAX_ROUND_IMAGES - totalImagesInRound;

                    if (maxImages[i] * NUM_ROUNDS > totalImages[i]) {
                        int decrement = (maxImages[i] * NUM_ROUNDS) - totalImages[i];
                        totalImagesInRound -= decrement;
                        maxImages[i] -= decrement;
                    } else {
                        break;
                    }
                }
            }
        }

        //make the Round directories
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(Calendar.getInstance().getTime());

        for (int i = 1; i <= 4; i++) {
            if (i == 4) {
                (new File("files/animeEyes/" + year + "/Finals")).mkdirs();
            } else {
                (new File("files/animeEyes/" + year + "/Round " + i)).mkdirs();
            }
        }

        //Add the images into the rounds!
        //int currRound = 0;
        int currNumOfFiles = 0;

        for (File difficultyFolder : dir.listFiles()) {
            if (difficultyFolder.isDirectory()) {
                int currDifficulty = Integer.parseInt(difficultyFolder.getName());

                for (int currRound = 0; currRound < NUM_ROUNDS; currRound++) {
                    for (int i = 0; i < maxImages[currDifficulty - 1]; i++) {
                        int fileIndex = rando.nextInt(totalImages[currDifficulty - 1]);
                        File image = difficultyFolder.listFiles()[fileIndex];

                        String[] filename = image.getName().split("\\.");
                        if (filename[filename.length - 1].toLowerCase().equals("png")
                                || filename[filename.length - 1].toLowerCase().equals("jpg")
                                || filename[filename.length - 1].toLowerCase().equals("jpeg")
                                || filename[filename.length - 1].toLowerCase().equals("gif")) {
                            String series = filename[0].split(" - ")[0];

                            if (!seriesList.get(currRound).contains(series)) {
                                seriesList.get(currRound).add(series);

                                image.renameTo(new File("files/animeEyes/" + year + "/Round "
                                        + Integer.toString(currRound + 1) + "/" + image.getName()));
                                totalImages[currDifficulty - 1]--;
                            } else {
                                i--;
                            }
                        } else {
                            i--;
                        }
                    }
                }
            }
        }

        //build the board for Finals
        int totalFinalImages = 0;
        for (int i = 0; ((i < maxDifficulty) && (totalFinalImages < MAX_FINALS_IMAGES)); i++) {
            File difficultyFolder = dir.listFiles()[i];

            while (totalImages[i] > 0 && totalFinalImages < MAX_FINALS_IMAGES) {
                int fileIndex = rando.nextInt(totalImages[i]);
                File image = difficultyFolder.listFiles()[fileIndex];

                String[] filename = image.getName().split("\\.");
                if (filename[filename.length - 1].toLowerCase().equals("png")
                        || filename[filename.length - 1].toLowerCase().equals("jpg")
                        || filename[filename.length - 1].toLowerCase().equals("jpeg")
                        || filename[filename.length - 1].toLowerCase().equals("gif")) {
                    String series = filename[0].split(" - ")[0];

                    if (!seriesList.get(seriesList.size() - 1).contains(series)) {
                        seriesList.get(seriesList.size() - 1).add(series);

                        image.renameTo(new File("files/animeEyes/" + year + "/Finals/"
                                + image.getName()));
                        totalImages[i]--;
                        totalFinalImages++;
                    }
                }
            }
        }
    }
}
