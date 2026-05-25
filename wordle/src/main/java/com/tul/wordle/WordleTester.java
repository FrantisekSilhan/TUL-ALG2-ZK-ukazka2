/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tul.wordle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Admin
 */
public class WordleTester {
  private List<WordPair> items;
  private int currentIndex;
  private int correctAnswers;
  private boolean inverse;
  private String name;
  
  public WordleTester() {}
  
  public void createNewTest(List<WordPair> data, String name, int count, boolean inverse) {
    this.currentIndex = 0;
    this.correctAnswers = 0;
    this.items = new ArrayList<>(data);
    this.name = name;
    Collections.shuffle(this.items);
    if (count < items.size()) {
      this.items = this.items.subList(0, count);
    }
    this.inverse = inverse;
  }
  
  public boolean hasNext() {
    return currentIndex < items.size();
  }
  
  public String getNextQuestion() {
    WordPair current = items.get(currentIndex);
    return inverse ? current.getPair() : current.getWord();
  }
  
  public boolean checkAnswer(String userReply) {
    WordPair current = items.get(currentIndex);
    String expected = inverse ? current.getWord() : current.getPair();
    
    boolean isCorrect = expected.equalsIgnoreCase(userReply.trim());
    if (isCorrect) correctAnswers++;
    
    currentIndex++;
    return isCorrect;
  }
  
  public String getFinalResult() {
    double percent = (double) correctAnswers / items.size() * 100;
    return String.format("Otazek: %d, Spravne: %d, Uspesnost: %.1f%%", items.size(), correctAnswers, percent);
  }
  
  public void saveResults(String filename) throws FileNotFoundException {
    if (this.currentIndex == 0) return;
    // Saves file in app running directory
    PrintWriter pw = new PrintWriter(new FileOutputStream(filename, true));
    double percent = (double) correctAnswers / items.size() * 100;
    
    pw.write(String.format("%1$s | %2$tF %2$tT %3$d/%4$d (%5$.1f%%)\n", this.name, LocalDateTime.now(), correctAnswers, items.size(), percent));
    pw.close();
  }
}
