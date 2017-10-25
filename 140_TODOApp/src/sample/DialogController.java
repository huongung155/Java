package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.datamodel.TodoData;
import sample.datamodel.TodoItem;

import java.time.LocalDate;

/**
 * Created by M4800 on 21-Apr-17.
 */
public class DialogController {
    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TextArea detailsArea;

    @FXML
    private DatePicker deadLinePicker;

    public TodoItem processResult(){
        String shortDescription = shortDescriptionField.getText();
        String details = detailsArea.getText();
        LocalDate deadLine = deadLinePicker.getValue();

        TodoItem newItem = new TodoItem(shortDescription, details, deadLine);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }
}
