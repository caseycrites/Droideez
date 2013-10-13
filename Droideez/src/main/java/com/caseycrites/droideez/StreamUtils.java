package com.caseycrites.droideez;

import java.io.IOException;
import java.io.InputStream;

/**
 * Collection of methods related to *Streams.
 */
public class StreamUtils {

  private StreamUtils() {}

  /**
   * Swallow IOException on close.
   *
   * @param s InputStream to close.
   */
  public static void closeQuitely(InputStream s) {
    try {
      s.close();
    } catch (IOException e) {}
  }
}
