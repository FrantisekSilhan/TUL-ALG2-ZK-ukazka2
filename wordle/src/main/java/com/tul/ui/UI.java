/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tul.ui;

import com.tul.wordle.WordPair;
import com.tul.wordle.Wordle;
import com.tul.wordle.WordleTester;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class UI {
  private static final Scanner sc = new Scanner(System.in);
  private static final Wordle wd = new Wordle();
  private static final WordleTester wt = new WordleTester();
  
  private static String menu() {
    StringBuilder sb = new StringBuilder();
    sb.append("Hlavni Menu\n");
    sb.append("1) Zadat adresar s testovacimi soubory\n");
    sb.append("2) Zobrazit seznam souboru\n");
    sb.append("3) Zvolit testovaci soubor\n");
    sb.append("4) Jednoduchy test\n");
    sb.append("5) Uplny test\n");
    sb.append("6) Ulozit posledni vysledek testu\n");
    sb.append("0) Ukoncit aplikaci\n");
    return sb.toString();
  }
  
  private static int readInt(String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = sc.nextLine();
      try {
        return Integer.parseInt(input);
      } catch (Exception e) {
        System.out.println("Musis zadat cislo");
      }
    }
  }
  
  private static String readString(String prompt) {
    while (true)  {
      System.out.print(prompt);
      String input = sc.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("Musis zadat textovy retezec");
        continue;
      }
      return input;
    }
  }
  
  private static boolean readBoolean(String prompt) {
    System.out.print(prompt);
    String input = sc.nextLine().trim();
    return input.isEmpty() || "a".equals(input.toLowerCase());
  }
  
  private static void run() {
    boolean running = true;
    while (running) {
      System.out.println(menu());
      int choice = readInt("Volba: ");
      switch(choice) {
        case 1: {
          handleSetDir();
          break;
        }
        case 2: {
          handleShowDir();
          break;
        }
        case 3: {
          handleSelectFile();
          break;
        }
        case 4: {
          handleTest(false);
          break;
        }
        case 5: {
          handleTest(true);
          break;
        }
        case 6: {
          handleResultSave();
          break;
        }
        case 0: {
          running = false;
          break;
        }
        default: {
          System.out.println("Neplatna volba");
          break;
        }
      }
    }
  }
  
  private static void handleSetDir() {
    String dir = readString("Zadej adresar: ");
    try {
      wd.setWorkingDir(dir);
    } catch (FileNotFoundException e) {
      System.out.println("Slozka neexistuje");
    }
  }
  
  private static void handleShowDir() {
    String[] files = wd.getDirTestFiles();
    for (String f: files) {
      System.out.println(String.format("Soubor: %s", f));
    }
  }
  
  private static void handleSelectFile() {
    String file = readString("Zadej nazev souboru: ");
    try {
      wd.loadDictionaryTest(file);
    } catch (FileNotFoundException e) {
      System.out.println(String.format("Soubor neexistuje: %s", e.getMessage()));
    } catch (Exception e) {
      System.out.println(String.format("Chyba pri nacitani souboru: %s", e.getMessage()));
    }
  }
  
  private static void handleResultSave() {
    String file = readString("Zadej nazev souboru: ");
    try {
      wt.saveResults(file);
    } catch (FileNotFoundException e) {
      System.out.println(String.format("Chyba pri ukladani souboru: %s", e.getMessage()));
    }
  }
  
  private static void handleTest(boolean full) {
    try {
      List<WordPair> data = wd.getData();
      boolean inverse = readBoolean("Chcete inverzni variantu testu? (A/n): ");
      wt.createNewTest(data, wd.getName(), full ? data.size() : 2, inverse);

      System.out.println("Test");
      System.out.println(wd.getQuestion(inverse));
      while (wt.hasNext()) {
        System.out.print(wt.getNextQuestion());
        String answer = readString(": ");
        wt.checkAnswer(answer);
      }
      System.out.println(wt.getFinalResult());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public static void main(String[] args) {
    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      // ignore
    }
    
    String name = readString("Zadej své jméno: ");
    wd.setName(name);
    run();
  }
}
