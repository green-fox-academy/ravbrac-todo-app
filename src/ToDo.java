import javafx.scene.shape.Path;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ToDo {
    public static void main(String[] args) {
        File tasks = new File("tasks.txt");

        if (args.length == 0){
            printUsage();
        } else if (args[0].equals("-l")){
            if (tasks.length() == 0){
                System.out.println("No todos for today! :)");
            } else{
                listTasks(tasks);
            }

        }

        if (args[0].equals("-a") && !args[1].isEmpty()){
            addNewTask(tasks, args[1]);
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
            myWriter.write("\n" +task);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            System.exit(2);
        }
    }
}
