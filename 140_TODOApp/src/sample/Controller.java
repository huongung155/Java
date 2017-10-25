package sample;

import javafx.application.Platform;
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
import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.datamodel.TodoData;
import sample.datamodel.TodoItem;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {
    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> listView;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private Label deadLineLabel;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ContextMenu listContextMenu;

    @FXML
    private ToggleButton filterToggleButton;

    private FilteredList<TodoItem> filteredList;

    private Predicate<TodoItem> wantAllItems;
    private Predicate<TodoItem> wantTodayItems;

    public void initialize(){
//        TodoItem item1 = new TodoItem("Receive mail", "Receive mail from", LocalDate.of(2016, Month.APRIL, 22));
//        TodoItem item2 = new TodoItem("Receive mail Quan", "Receive mail from Quan", LocalDate.of(2016, Month.JANUARY, 2));
//        TodoItem item3 = new TodoItem("Receive mail An", "Receive mail from An", LocalDate.of(2016, Month.DECEMBER, 12));
//        TodoItem item4 = new TodoItem("Receive mail Nguyen", "Receive mail from nguyen", LocalDate.of(2016, Month.JULY, 11));
//        TodoItem item5 = new TodoItem("Receive mail Hai", "Receive mail from Hai", LocalDate.of(2016, Month.MARCH, 2));
//
//        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);
//
//        TodoData.getInstance().setTodoItems(todoItems);

        /*listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null){
                    TodoItem item = listView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                }
            }
        });*/

        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        listContextMenu.getItems().addAll(deleteMenuItem);
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TodoItem item = listView.getSelectionModel().getSelectedItem();
                deleteItem(item);
            }
        });
        //listContextMenu.getItems().addAll(deleteMenuItem);

        wantAllItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return true;
            }
        };

        wantTodayItems = new Predicate<TodoItem>() {
            @Override
            public boolean test(TodoItem item) {
                return item.getDeadLine().equals(LocalDate.now());
            }
        };

        filteredList = new FilteredList<TodoItem>(TodoData.getInstance().getTodoItems(), wantAllItems);

        SortedList<TodoItem> sortedList = new SortedList<TodoItem>(filteredList, new Comparator<TodoItem>() {
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
                ListCell<TodoItem> cell = new ListCell<TodoItem>(){
                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty){
                            setText(null);
                        }else {
                            setText(item.getShortDescription());
                            if(item.getDeadLine().equals(LocalDate.now())){
                                setTextFill(Color.RED);
                            }

                        }
                    }
                };

                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if(isNowEmpty){
                                cell.setContextMenu(null);
                            }else {
                                cell.setContextMenu(listContextMenu);
                            }
                        }
                );
                return cell;
            }
        });
    }

    @FXML
    public void handleFilterButton(){
        TodoItem selectedItem = listView.getSelectionModel().getSelectedItem();
        if(filterToggleButton.isSelected()){
            filteredList.setPredicate(wantTodayItems);
            if(filteredList.isEmpty()){
                itemDetailsTextArea.clear();
                deadLineLabel.setText("");
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
    public void handleChange(){
        TodoItem item = listView.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        deadLineLabel.setText(df.format(item.getDeadLine()));

    }

    @FXML
    public void handleKeyPressed(KeyEvent e){
        TodoItem selectedItem = listView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            if(e.getCode().equals(KeyCode.DELETE)){
                deleteItem(selectedItem);
            }
        }
    }

    @FXML
    public void handleExit(){
        Platform.exit();
    }

    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Use this dialog to create a new todo item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("todoItemDialog.fxml"));
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResult();
            listView.getItems().setAll(TodoData.getInstance().getTodoItems());
            listView.getSelectionModel().select(newItem);
            System.out.println("OK Pressed");
        }else {
            System.out.println("Cancel Pressed");
        }
    }

    public void deleteItem(TodoItem item){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete item");
        alert.setHeaderText("Delete item: " + item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm or Cancel to rollback");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            TodoData.getInstance().deleteTodoItem(item);
            listView.getItems().setAll(TodoData.getInstance().getTodoItems());
        }
    }
}
