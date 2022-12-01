package com.adria.crud.demo.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class moduleController implements Initializable{
    private Stage stage;
    private static DataBaseMDAO bd = new DataBaseMySQLDAO();
        @FXML
        private Button addButton;

        @FXML
        private Button alumneButton;

        @FXML
        private Button clearButton;

        @FXML
        private Button deleteButton;

        @FXML
        private TableColumn<moduleModel, String> idColumn;

        @FXML
        private TableColumn<moduleModel, String> idPColumn;

        @FXML
        private TextField idPTextField;

        @FXML
        private TextField idTextField;

        @FXML
        private Button logoutButton;

        @FXML
        private Button modulsButton;

        @FXML
        private TableColumn<moduleModel, String> nameColumn;

        @FXML
        private TextField nameTextField;

        @FXML
        private Button professorButton;

        @FXML
        private Button refreshButton;

        @FXML
        private Button searchButton;

        @FXML
        private TableView<moduleModel> table;

        @FXML
        private Button updateButton;

    public void alumneButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("pupilMain.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void professorButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("teacherMain.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<moduleModel> listView = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        select();
    }

    public void select() {
        listView.clear();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        idPColumn.setCellValueFactory(new PropertyValueFactory<>("id_professor"));
        table.setItems(bd.selectModules(listView));
        table.setItems(listView);
        table.getSortOrder().add(idColumn);
        valorNull();
    }
    public void searchButtonOnAction(ActionEvent event){
        if(idTextField.getText().isEmpty() && nameTextField.getText().isEmpty() && idPTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No hi ha cap camp omplert");
            alert.showAndWait();

        }else{
            moduleModel module = bd.selectModule(idTextField.getText());
            nameTextField.setText(module.getNom());
            idPTextField.setText(module.getId_professor());
        }
    }
    public void addButtonOnAction(ActionEvent event) {
        if(nameTextField.getText().isEmpty() || idPTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Falten camps per omplir");
            alert.showAndWait();
        }else{
            moduleModel module = new moduleModel(idTextField.getText(), nameTextField.getText(), idPTextField.getText());
            bd.insertModule(module);
            select();
            valorNull();
        }
    }
    public void valorNull() {
        idTextField.setText("");
        nameTextField.setText("");
        idPTextField.setText("");
    }

    public void updateButtonOnAction(ActionEvent event) {
        if(idTextField.getText().isEmpty()||nameTextField.getText().isEmpty() || idPTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Falten camps per omplir");
            alert.showAndWait();
        }else{
            moduleModel module = new moduleModel(idTextField.getText(), nameTextField.getText(), idPTextField.getText());
            bd.updateModule(module);
            select();
            valorNull();
        }
    }
    public void deleteButtonOnAction() {
        if(idTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Falta omplir el camp id");
            alert.showAndWait();
        }else{
            bd.deleteModule(idTextField.getText());
            select();
            valorNull();
        }
    }

    public void logoutButtonOnAction(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }
}
