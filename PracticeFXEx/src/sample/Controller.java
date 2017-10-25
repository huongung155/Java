package sample;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import sample.Todomodel.TodoData;
import sample.Todomodel.TodoItem;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {
    @FXML
    private ListView<TodoItem> listView;

    @FXML
    private Label label;

    @FXML
    private TextArea textArea;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private ToggleButton filterToggleButton;

    private FilteredList<TodoItem> filteredList;
    private SortedList<TodoItem> sortedList;

    private Predicate<TodoItem> wantAllItems;
    private Predicate<TodoItem> wantTodayItems;

    public void initialize(){
        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete...");
        listContextMenu.getItems().addAll(deleteMenuItem);
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TodoItem item = listView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });

        wantAllItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem todoItem) {
                return true;
            }
        };

        wantTodayItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem todoItem) {
                return todoItem.getDeadLine().equals(LocalDate.now());
            }
        };

        filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), wantAllItems);
        sortedList = new SortedList<TodoItem>(filteredList, new Comparator<TodoItem>() {
            @Override
            public int compare(TodoItem o1, TodoItem o2) {
                return o1.getDeadLine().compareTo(o2.getDeadLine());
            }
        });

        //listView.getItems().setAll(TodoData.getInstance().getTodoItems());
        listView.setItems(sortedList);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectFirst();

        listView.setCellFactory(new Callback<ListView<TodoItem>, ListCell<TodoItem>>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> param) {
                ListCell<TodoItem> call = new ListCell<TodoItem>(){
                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty){
                            setText(null);
                        }else {
                            setText(item.getShortDescription());
                        }
                    }
                };
                call.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) ->{
                            if(isNowEmpty){
                                call.setContextMenu(null);
                            }else {
                                call.setContextMenu(listContextMenu);
                            }
                        }
                );
                return call;
            }
        });
    }

    @FXML
    public void handleFilterButton(){
        TodoItem selectedItem = listView.getSelectionModel().getSelectedItem();
        if(filterToggleButton.isSelected()){
            filteredList.setPredicate(wantTodayItems);
            if(filteredList.isEmpty()){
                textArea.clear();
                label.setText("");
            }else if(filteredList.contains(selectedItem)){
                listView.getSelectionModel().select(selectedItem);
            }else {
                listView.getSelectionModel().selectFirst();
            }
        }else {
            filteredList.setPredicate(wantAllItems);
            listView.getSelectionModel().select(selectedItem);
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent e){
        TodoItem selectedItem = listView.getSelectionModel().getSelectedItem();
        if(e.getCode().equals(KeyCode.DELETE)){
            deleteItem(selectedItem);
        }
    }

    @FXML
    public void handleChange(){
        TodoItem item = listView.getSelectionModel().getSelectedItem();
        textArea.setText(item.getDetails());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        label.setText(item.getDeadLine().format(formatter));
    }
    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Use this dialog to create a new todo item");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        }catch (IOException e){

        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            DialogController controller = loader.getController();
            TodoItem item = controller.processResult();
            listView.getItems().setAll(TodoData.getInstance().getTodoItems());
            listView.getSelectionModel().select(item);
            System.out.println("OK Pressed");
        }else {
            System.out.println("Cancel Pressed");
        }
    }

    public void deleteItem(TodoItem item){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete item");
        alert.setHeaderText("Delete item: " + item.getShortDescription());
        alert.setContentText("Are you sure? Press Ok to delete or Cancel to rollback");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            TodoData.getInstance().deleteTodoItem(item);
            listView.getItems().setAll(TodoData.getInstance().getTodoItems());
        }
    }
}
