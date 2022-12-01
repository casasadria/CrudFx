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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class teacherController implements Initializable {
    private Stage stage;
    private static DataBaseMDAO bd = new DataBaseMySQLDAO();
        @FXML
        private Button addButton;

        @FXML
        private Button alumneButton;

        @FXML
        private Button clearButton;

        @FXML
        private TextField cognomTextField;

        @FXML
        private Button deleteButton;

        @FXML
        private TableColumn<teacherModel, String> idColumn;

        @FXML
        private TextField idTextField;

        @FXML
        private Button logoutButton;

        @FXML
        private Button modulsButton;

        @FXML
        private TableColumn<teacherModel, String> nameColumn;

        @FXML
        private TextField nomTextField;

        @FXML
        private Button professorButton;

        @FXML
        private Button refreshButton;

        @FXML
        private Button searchButton;

        @FXML
        private TableColumn<teacherModel, String> surnameColumn;

        @FXML
        private TableView<teacherModel> table;

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
    public void modulsButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("moduleMain.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    select();
    }
    ObservableList<teacherModel> listView = FXCollections.observableArrayList();
    public void select(){
        listView.clear();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("cognoms"));
        bd.selectTeachers(listView);
        table.setItems(listView);
        table.getSortOrder().add(idColumn);
        valorNull();
    }
    public void searchButtonOnAction(ActionEvent event){
        if(idTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Introdueix un ID");
            alert.showAndWait();
        }else{
            teacherModel professor = bd.selectTeacher(idTextField.getText());
            nomTextField.setText(professor.getNom());
            cognomTextField.setText(professor.getCognoms());
        }
    }
    public void addButtonOnAction(ActionEvent event){
        if(nomTextField.getText().isEmpty() || cognomTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Introdueix un ID, nom i cognoms");
            alert.showAndWait();
        }else{
            teacherModel professor = new teacherModel(idTextField.getText(),nomTextField.getText(),cognomTextField.getText());
            bd.insertTeacher(professor);
            select();
            valorNull();
        }
    }
    public void valorNull(){
        idTextField.setText("");
        nomTextField.setText("");
        cognomTextField.setText("");
    }
    public void updateButtonOnAction(ActionEvent event){
        if(idTextField.getText().isEmpty() || nomTextField.getText().isEmpty() || cognomTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Introdueix un ID, nom i cognoms");
            alert.showAndWait();
        }else{
            teacherModel professor = new teacherModel(idTextField.getText(),nomTextField.getText(),cognomTextField.getText());
            bd.updateTeacher(professor);
            select();
            valorNull();
        }
    }
    public void deleteButtonOnAction(ActionEvent event){
        if(idTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Introdueix un ID");
            alert.showAndWait();
        }else{
            bd.deleteTeacher(idTextField.getText());
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
