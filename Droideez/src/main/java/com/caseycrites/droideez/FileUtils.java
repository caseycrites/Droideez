package com.caseycrites.droideez;

import java.io.File;

/**
 * Collection of methods pertaining to files.
 */
public class FileUtils {

  /**
   * Calculate the size of a directory or file
   *
   * If the file is a directory, it will recursively calculate the size of its children.
   *
   * @param file File whose size is to be calculated
   * @return long size in bytes
   */
  public static long calculateSize(File file) {
    long bytes = 0;
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      for (File child : files) {
        if (child.isDirectory())
          bytes += calculateSize(child);
        else
          bytes += child.length();
      }
    } else
      bytes = file.length();

    return bytes;
  }

  /**
   * Delete a directory
   *
   * In order to delete a directory it must be empty, so delete all children first. If
   * a regular file is passed in, the method will abort without deleting it.
   *
   * @param directory Directory to be deleted
   */
  public static void deleteDirectory(File directory) {
    if (!directory.isDirectory() || !directory.exists())
      return;

    for (File child : directory.listFiles()) {
      if (child.isDirectory())
        deleteDirectory(child);
      else
        child.delete();
    }

    directory.delete();
  }
}
