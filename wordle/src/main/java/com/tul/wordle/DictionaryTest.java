/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tul.wordle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DictionaryTest {
  private String q1;
  private String q2;
  private List<WordPair> pairs = new ArrayList<>();
  
  public DictionaryTest() {}
  
  public void setQ1(String q1) {
    this.q1 = q1;
  }
  
  public void setQ2(String q2) {
    this.q2 = q2;
  }
  
  public void addPair(WordPair pair) {
    this.pairs.add(pair);
  }
  
  public List<WordPair> getPairs() { return new ArrayList<>(this.pairs); }
  public String getQ1() { return q1; }
  public String getQ2() { return q2; }
}
