import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ToDo {
    public static void main(String[] args) {
        File tasks = new File("tasks.txt");

        if (args.length == 0){
            System.out.println(printUsage());
        } else if (args[0].equals("-l")){
            if (tasks.length() == 0){
                System.out.println("No todos for today! :)");
            } else{
                listTasks(tasks);
            }

        }

    }

    public static String printUsage() {
        return  "\n" +
                "Command Line Todo application\n" +
                "=============================\n" +
                "\n" +
                "Command line arguments:\n" +
                "    -l   Lists all the tasks\n" +
                "    -a   Adds a new task\n" +
                "    -r   Removes an task\n" +
                "    -c   Completes an task";
    }

    public static void listTasks(File file) {
        try {
            Scanner myReader = new Scanner(file);
            int counter = 0;
            while (myReader.hasNextLine()) {
                counter++;
                String data = myReader.nextLine();
                System.out.println(Integer.toString(counter) + " - " + data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("There's no task list");
            System.exit(2);
        }

    }
}
