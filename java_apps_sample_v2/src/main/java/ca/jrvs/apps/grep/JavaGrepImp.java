package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaGrepImp implements JavaGrep {

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();

    for (File file : listFiles(this.getRootPath())) {
      for (String line : readLines(file)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> files = new ArrayList<>();
    File dir = new File(rootDir);

    for (File file : dir.listFiles()) {
      if (file.isDirectory()) {
        files.addAll(listFiles(file.getPath()));
      } else {
        //base case
        files.add(file);
      }
    }
    return files;
  }

  public void listFilesLambda(String rootPath, List<File> files) throws IOException {
    Files.walk(Paths.get(rootPath)).filter(Files::isRegularFile).map(Path::toFile)
        .forEach(files::add);
  }

  public List<File> listFilesLambda(String rootPath) throws IOException {
    return Files.walk(Paths.get(rootPath)).filter(Files::isRegularFile).map(Path::toFile)
        .collect(Collectors.toList());
  }

  public List<String> readLines(File inputFile) {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException("Not a file");
    }
    List<String> lines = new ArrayList<>();
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(inputFile));
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }

  public List<String> readLinesLambda(File inputFile) throws IOException {
    return Files.lines(Paths.get(inputFile.getPath())).collect(Collectors.toList());
  }

  public void searchFileLambda(File file, List<String> matchedLines) throws IOException {
    Files.lines(file.toPath(), StandardCharsets.UTF_8)
        .filter(text -> text.matches(this.getRegex()))
        .map(text -> file.getPath() + ":" + text)
        .forEach(matchedLines::add);
  }

  public List<String> searchFileLambda(File file) throws IOException {
    return Files.lines(file.toPath(), StandardCharsets.UTF_8)
        .filter(text -> text.matches(this.getRegex()))
        .map(text -> file.getPath() + ":" + text)
        .collect(Collectors.toList());

  }

  @Override
  public boolean containsPattern(String line) {
    return line.matches(this.getRegex());
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    FileOutputStream fos = new FileOutputStream(this.getOutFile());
    OutputStreamWriter osw = new OutputStreamWriter(fos);
    BufferedWriter bw = new BufferedWriter(osw);

    for (String line : lines) {
      bw.write(line);
      bw.newLine();
    }
    //empty outFile is not close....
    bw.close();
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

}

