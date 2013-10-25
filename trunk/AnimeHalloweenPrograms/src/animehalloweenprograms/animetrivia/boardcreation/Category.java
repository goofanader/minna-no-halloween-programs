/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animehalloweenprograms.animetrivia.boardcreation;

/**
 *
 * @author pdouglas
 */
public class Category {
   private String[] questions;
   private String[] answers;
   private int difficulty;
   private String title;
   private String description;

   public Category() {
      questions = new String[Main.TOTAL_QUESTIONS];
      answers = new String[Main.TOTAL_QUESTIONS];
      title = description = "";
      difficulty = 0;
   }

   public String[] getAnswers() {
      return answers;
   }

   public void setAnswers(String[] answers) {
      this.answers = answers;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getDifficulty() {
      return difficulty;
   }

   public void setDifficulty(int difficulty) {
      this.difficulty = difficulty;
   }

   public String[] getQuestions() {
      return questions;
   }

   public void setQuestions(String[] questions) {
      this.questions = questions;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   @Override
   public String toString() {
      return "Category: " + title + "; Difficulty: " + difficulty;
   }
}
