package corbos.todo;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ToDoApp {

    private UI ui = new UI();
    private ToDoDataStore data = new ToDoDataStore();

    public void run() {

        ui.displayWelcome();

        int selection = UI.MIN_MENU_ITEM;
        while (selection >= UI.MIN_MENU_ITEM && selection < UI.MAX_MENU_ITEM) {

            selection = ui.getMenuSelection();

            switch (selection) {
                case UI.MENU_LIST:
                    list();
                    break;
                case UI.MENU_ADD:
                    add();
                    break;
                case UI.MENU_COMPLETE:
                    complete();
                    break;
                case UI.MENU_DELETE:
                    delete();
                    break;
            }
        }

        ui.displayGoodbye();
    }

    private void list() {
        List<ToDo> items = data.all();
        if (items.size() == 0) {
            ui.displayNoItemsFound();
        } else {
            ui.displayTasks(items);
        }
    }

    private void add() {
        ToDo task = ui.createToDo();
        Response response = data.add(task);
        ui.displayResponse(response);
    }

    private void complete() {
        String taskName = ui.getTaskName();
        ToDo todo = data.completeTask(taskName);
        response(todo);
        ui.printLine("Completing a To-Do!");
    }

    private void delete() {
        String taskName = ui.getTaskName();
        ToDo todo = data.deleteTodo(taskName);
        response(todo);
        ui.printLine("Deleting a To-Do!");
    }
    
    private void response(ToDo todo) {
        Response response = new Response();
        if(todo == null) {
            ui.displayNoItemsFound();
        }else {
            response.setSuccess(true);
            response.setMessage("Error! no task found by that name");
            if(!response.isSuccess() ){
                ui.printLine(response.getMessage());
            }else {
                ui.printLine("Success!");
            }
        }
    }
    
    
}