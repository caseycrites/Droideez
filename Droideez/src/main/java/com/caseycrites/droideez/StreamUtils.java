package com.caseycrites.droideez;

import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {

  private StreamUtils() {}

  public static void closeQuitely(InputStream s) {
    try {
      s.close();
    } catch (IOException e) {}
  }
}
