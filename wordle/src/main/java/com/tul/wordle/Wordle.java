/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tul.wordle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Wordle {
  private Path workingDir;
  private String name;
  private String selectedTest;
  private Map<String, DictionaryTest> dictionaryTests = new HashMap<>();
  
  public Wordle() {}
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setWorkingDir(String dir) throws FileNotFoundException {
    this.workingDir = Paths.get(dir);
    if (!Files.isDirectory(workingDir)) {
      throw new FileNotFoundException("Slozka neexistuje");
    }
  }
  
  public void loadDictionaryTest(String path) throws FileNotFoundException, UnsupportedEncodingException, IOException, Exception {
    if (path.toLowerCase().endsWith(".stq")) {
      path = path.substring(0, path.length() - 4);
    }
    if (this.dictionaryTests.containsKey(path)) {
      this.selectedTest = path;
      return;
    }
    
    String filePath = this.workingDir.resolve(path + ".stq").toString();
    
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
    
    String line;
    DictionaryTest dt = new DictionaryTest();
    while ((line = br.readLine()) != null) {
      line = line.trim();
      if (line.isEmpty()) continue;
      switch(line) {
        case "Q1": {
          line = br.readLine().trim();
          if (line.isEmpty()) throw new Exception("Nespravny format souboru");
          dt.setQ1(line);
          break;
        }
        case "Q2": {
          line = br.readLine().trim();
          if (line.isEmpty()) throw new Exception("Nespravny format souboru");
          dt.setQ2(line);
          break;
        }
        case "Items": {
          while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\|");
            String word = parts[0].trim();
            String pair = parts[1].trim();
            if (word.isEmpty() || pair.isEmpty()) throw new Exception("Nespravny format souboru");
            dt.addPair(new WordPair(word, pair));
          }
          break;
        }
      }
    }
    br.close();
    dictionaryTests.put(path, dt);
    this.selectedTest = path;
  }
  
  public String[] getDirTestFiles() {
    File dir = new File(this.workingDir.toString());
    String[] files = dir.list((d, name) -> (name.endsWith(".stq")));
    
    return files;
  }
  
  public String getName() {
    return this.name;
  }
  
  public List<WordPair> getData() {
    var test = this.dictionaryTests.get(this.selectedTest);
    if (test == null) throw new IllegalArgumentException("Musis vybrat test");
    return test.getPairs();
  }
  
  public String getQuestion(boolean inverse) {
    var test = this.dictionaryTests.get(this.selectedTest);
    return inverse ? test.getQ2() : test.getQ1();
  }
}
