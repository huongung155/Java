package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Todomodel.TodoData;
import sample.Todomodel.TodoItem;

import java.time.LocalDate;

/**
 * Created by M4800 on 25-Apr-17.
 */
public class DialogController {
    @FXML
    private TextField addDescription;

    @FXML
    private TextArea detailArea;

    @FXML
    private DatePicker addDeadLine;

    public TodoItem processResult(){
        String shortDescription = addDescription.getText();
        String details = detailArea.getText();
        LocalDate deadLine = addDeadLine.getValue();

        TodoItem item = new TodoItem(shortDescription, details, deadLine);
        TodoData.getInstance().addTodoItem(item);
        return item;
    }
}
