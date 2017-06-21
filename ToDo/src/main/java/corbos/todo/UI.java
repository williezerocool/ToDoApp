package corbos.todo;

import java.util.List;
import java.util.Scanner;

public class UI {

    public static final int MIN_MENU_ITEM = 1;
    public static final int MAX_MENU_ITEM = 5;

    public static final int MENU_LIST = 1;
    public static final int MENU_ADD = 2;
    public static final int MENU_COMPLETE = 3;
    public static final int MENU_DELETE = 4;

    private Scanner scanner = new Scanner(System.in);

    public void displayWelcome() {
        printLine("========================");
        printLine("");
        printLine("Like To-Do Peas in a Pod");
        printLine("");
        printLine("========================");
    }

    public void displayNoItemsFound() {
        printLine("");
        printLine("***");
        printLine("No Tasks Found.");
        printLine("***");
        pressEnterToContinue();
    }

    public void displayTasks(List<ToDo> tasks) {
        // TODO: update this code to make it display each task's status:
        // complete or not.
        printLine("");
        printLine("All Tasks:");
        printLine("===========");
        for (ToDo task : tasks) {
             printLine(task.getName() + " " + task.isComplete());
        }
        pressEnterToContinue();
    }

    public void displayResponse(Response response) {
        printLine("");
        printLine("***");
        if (response.isSuccess()) {
            printLine("Success!");
        } else {
            printLine(response.getMessage());
        }
        printLine("***");
        pressEnterToContinue();
    }

    public void displayGoodbye() {
        printLine("");
        printLine("==========");
        printLine("");
        printLine("Goodbye...");
        printLine("");
        printLine("==========");
    }

    public void printLine(String message) {
        print(message + "\n");
    }

    public int getMenuSelection() {
        printMenu();
        return readInt("Choose an option (1 - 5):", MIN_MENU_ITEM, MAX_MENU_ITEM);
    }

    public ToDo createToDo() {
        printLine("");
        printLine("Add a Task");
        printLine("============");

        ToDo item = new ToDo();
        item.setName(readLine("Enter the task name:"));
        return item;
    }

    private void printMenu() {
        printLine("");
        printLine("===========");
        printLine("Main Menu:");
        printLine("1. List All Tasks");
        printLine("2. Add a Task");
        printLine("3. Complete a Task");
        printLine("4. Delete a Task");
        printLine("5. Exit");
    }

    private void pressEnterToContinue() {
        printLine("");
        readLine("Press <enter> to continue.");
    }

    private void print(String message) {
        System.out.print(message);
    }

    private String readLine(String message) {
        print(message);
        return scanner.nextLine();
    }

    private int readInt(String message) {
        int result = Integer.MIN_VALUE;
        while (result == Integer.MIN_VALUE) {
            try {
                result = Integer.parseInt(readLine(message));
            } catch (NumberFormatException ex) {
            }

        }
        return result;
    }

    private int readInt(String message, int min, int max) {
        int result = Integer.MIN_VALUE;
        while (result < min || result > max) {
            result = readInt(message);
        }
        return result;
    }
    
    
    
    public String getTaskName() {
        
        String taskName = readLine("Enter task name? ");
        return taskName;
    }
    
    
}