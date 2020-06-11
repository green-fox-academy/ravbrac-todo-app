import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ToDo {
  public static void main(String[] args) {
    File tasks = new File("tasks.txt");

    if (args.length == 0) {
      printUsage();
    } else if (args[0].equals("-l")) {
      if (tasks.length() == 0) {
        System.out.println("No todos for today! :)");
      } else {
        listTasks(tasks);
      }

    }else if (args[0].equals("-a") && !args[1].isEmpty()) {
      addNewTask(tasks, args[1]);
    }else if (args[0].equals("-r") && args[1].equals("2") && (numberOfLines(tasks) >= 2)) {
      removeTask2(tasks);
    }else {
      System.out.println("Unsupported argument");
      System.out.println();
      printUsage();
    }

  }

  public static void printUsage() {
    System.out.println("\n" +
        "Command Line Todo application\n" +
        "=============================\n" +
        "\n" +
        "Command line arguments:\n" +
        "    -l   Lists all the tasks\n" +
        "    -a   Adds a new task\n" +
        "    -r   Removes an task\n" +
        "    -c   Completes an task");
  }

  public static void listTasks(File file) {
    try {
      Scanner myReader = new Scanner(file);
      int counter = 0;
      while (myReader.hasNextLine()) {
        counter++;
        String data = myReader.nextLine();
        System.out.println(counter + " - " + data);
      }
    } catch (FileNotFoundException e) {
      System.out.println("There's no task list");
      System.exit(2);
    }

  }

  public static void addNewTask(File file, String task) {
    try {
      FileWriter myWriter = new FileWriter(file, true);
      myWriter.write("\n" + task);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      System.exit(2);
    }
  }

  public static int numberOfLines(File file) {
    int counter = 0;
    try {
      java.nio.file.Path filePath = Paths.get(String.valueOf(file));
      List<String> lines = Files.readAllLines(filePath);
      for (int i = 0; i < lines.size(); i++) {
        counter++;
      }
      return counter;
    } catch (IOException e) {
      return 0;
    }

  }

  public static void removeTask2(File file) {
    try {
      java.nio.file.Path filePath = Paths.get(String.valueOf(file));
      List<String> lines;
      lines = Files.readAllLines(filePath);
      lines.remove(1);
      Files.write(filePath, lines);
    } catch (IOException e) {
      System.out.println("An error occurred.");
      System.exit(2);
    }
  }
}
