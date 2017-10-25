package sample.Todomodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Pavilion
 * Date: 4/23/17
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class TodoData {
    private static TodoData instance = new TodoData();
    private final static String filename = "TodoListItems.txt";

    private ObservableList<TodoItem> todoItems;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static TodoData getInstance() {
        return instance;
    }

    public ObservableList<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(ObservableList<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public void addTodoItem(TodoItem item){
        todoItems.add(item);
    }

    public void deleteTodoItem(TodoItem item){
        todoItems.remove(item);
    }

    public void loadTodoItem() throws IOException {
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;
        try {
            while ((input = br.readLine()) != null){
                String[] name = input.split("\t");
                String shortDescription = name[0];
                String details = name[1];
                String deadLine = name[2];

                LocalDate date = LocalDate.parse(deadLine, formatter);
                todoItems.add(new TodoItem(shortDescription, details, date));
            }
        }finally {
            if(br != null){
                br.close();
            }
        }
    }

    public void storeTodoItem() throws IOException{
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        Iterator<TodoItem> iter = todoItems.iterator();

        try {
            while (iter.hasNext()){
                TodoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s", item.getShortDescription(), item.getDetails(), item.getDeadLine().format(formatter)));
                bw.newLine();
            }
        }finally {
            if(bw != null){
                bw.close();
            }
        }
    }
}
