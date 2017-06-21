package corbos.todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoDataStore {

    private static final String DATA_FILE_PATH = "to-do-data.txt";

    public List<ToDo> all() {
        return read();
    }

    public Response add(ToDo task) {

        // validate the data
        if (task.getName() == null || task.getName().trim().length() == 0) {
            Response response = new Response();
            response.setMessage("Task name is required!");
            return response;
        }
        
        List<ToDo> all = read();
        all.add(task);
        return write(all);
        
    }

    private List<ToDo> read() {
        List<ToDo> result = new ArrayList<>();

        File file = new File(DATA_FILE_PATH);
        if (!file.exists()) {
            return result;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length == 3) {
                    ToDo task = new ToDo();
                    task.setName(tokens[0]);
                    task.setIsComplete(Boolean.parseBoolean(tokens[1]));
                    task.setCreateDate(LocalDate.parse(tokens[2]));
                    result.add(task);
                }

            }
            reader.close();

        } catch (IOException ex) {
            System.out.printf("ERROR!: %s", ex.getMessage());
        }

        return result;
    }

    public Response write(List<ToDo> tasks) {

        Response result = new Response();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(DATA_FILE_PATH);
            for (ToDo task : tasks) {
                writer.write(String.format(
                        "%s|%s|%s\n",
                        task.getName(),
                        task.isComplete(),
                        task.getCreateDate()));
            }
            writer.flush();
            result.setSuccess(true);
        } catch (FileNotFoundException ex) {
            result.setMessage(ex.getMessage());
        } finally {
            writer.close();
        }

        return result;
    }
    
    public ToDo getToDoName(String taskName) {
       
        for(ToDo item : all() ) {
            if(item.getName().equalsIgnoreCase(taskName)) {
                return item;
            }
        }
        return null;
    }
    
    public void writeTodo(ToDo task) {
        List<ToDo> item = new ArrayList<>();
        item.add(task);
        write(item);
    }
    
    public ToDo completeTask(String TaskName) {
        List<ToDo> list = new ArrayList<>();
        ToDo todo = null;
        
        for(ToDo task : all() ) {
            if(task.getName().equalsIgnoreCase(TaskName) ) {
                task.setIsComplete(true);
                todo = task;
              }
            list.add(task);
        }
        write(list);
        return todo;
    }
    
   public ToDo deleteTodo(String taskName) {
       List<ToDo> todo = new ArrayList<>();
        ToDo item = null;
        for(ToDo itemTo : all() ) {
            if(itemTo.getName().equalsIgnoreCase(taskName) ) {
                
                item = itemTo;
                
            }
            todo.add(itemTo);
            todo.remove(item);
            
        }
        write(todo);
        return item;
   }
    
    
}