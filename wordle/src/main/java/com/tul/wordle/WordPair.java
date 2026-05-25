/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tul.wordle;

/**
 *
 * @author Admin
 */
public class WordPair {
  private String word;
  private String pair;
  
  public WordPair(String word, String pair) {
    this.word = word;
    this.pair = pair;
  }
  
  public String getWord() {
    return this.word;
  }
  
  public String getPair() {
    return this.pair;
  }
}
