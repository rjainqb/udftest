package com.qubole.stringutils;

public class StringUtils {
  String s;
  public StringUtils(String s) {
    this.s = s;
  }

  public String lower() {
    return s.toLowerCase();
  }

  public String upper() {
    return s.toUpperCase();
  }
}
