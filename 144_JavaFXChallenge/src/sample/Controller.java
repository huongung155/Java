package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import sample.datamodel.Contact;
import sample.datamodel.ContactData;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private BorderPane mainPane;

    @FXML
    private TableView<Contact> contactsTable;

    private ContactData data;

    public void initialize(){
        data = new ContactData();
        data.loadContacts();
        contactsTable.setItems(data.getContacts());
    }

    @FXML
    public void showAddContactDialog(){
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle("Add new Contact");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("contactdialog.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());

        }catch (IOException e){
            System.out.println("Could not load dialog");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            ContactController contactController = loader.getController();
            Contact newContact = contactController.getNewContact();
            data.addContactData(newContact);
            data.saveContacts();
        }
    }

    @FXML
    public void showEditContactDialog(){
        Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if(selectedContact == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the contact you want to edit");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle("Edit Contact");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("contactDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());
        }catch (IOException e){
            System.out.println("Could not load dialog");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ContactController contactController = loader.getController();
        contactController.editContact(selectedContact);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            contactController.updateContact(selectedContact);
            data.saveContacts();
        }
    }

    @FXML
    public void deleteContact(){
        Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if(selectedContact == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the contact you want to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Please Contact");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to delete the selected contact: " + selectedContact.getFirstName() + " " + selectedContact.getLastName());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            data.deleteContactData(selectedContact);
            data.saveContacts();
        }
    }
}
