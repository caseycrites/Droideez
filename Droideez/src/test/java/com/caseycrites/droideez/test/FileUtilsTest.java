package com.caseycrites.droideez.test;

import com.caseycrites.droideez.FileUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.lang.RuntimeException;

import dalvik.annotation.TestTargetClass;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class FileUtilsTest {

  private File rootDir;
  private File file1;
  private File file2;

  @Before
  public void setUp() throws Exception {
    rootDir = new File(Robolectric.getShadowApplication().getFilesDir().getAbsolutePath(),
      "/directory_1");
    rootDir.mkdir();
    byte[] fakeData = "what even is files?".getBytes("utf-8");
    file1 = new File(rootDir, "file_1");
    FileOutputStream os = new FileOutputStream(file1);
    os.write(fakeData);
    os.close();

    file2 = new File(rootDir, "file_2");
    os = new FileOutputStream(file2);
    os.write(fakeData);
    os.close();
  }

  @After
  public void tearDown() {
    FileUtils.deleteDirectory(rootDir);
  }

  @Test
  public void calculateDirectorySize() throws Exception {
    long size = FileUtils.calculateSize(rootDir);
    assertThat(size).isEqualTo(file1.length() + file2.length());
  }

  @Test
  public void deletedDirectory() throws Exception {
    assertThat(rootDir.exists()).isTrue();
    assertThat(file1.exists()).isTrue();
    assertThat(file2.exists()).isTrue();

    FileUtils.deleteDirectory(rootDir);

    assertThat(rootDir.exists()).isFalse();
    assertThat(file1.exists()).isFalse();
    assertThat(file2.exists()).isFalse();
  }

}
