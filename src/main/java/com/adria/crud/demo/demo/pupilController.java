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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class pupilController implements Initializable {
    private Stage stage;
    private static final DataBasePDAO bd = new DataBasePostGreSQLDAO();
    @FXML
    private Button addButton;
    @FXML
    private Button delinfoButton;

    @FXML
    private Button alumneButton;
    @FXML
    private TableView<pupilModel> table;
    @FXML
    private TableColumn<pupilModel, String> birthColumn;
    @FXML
    private TableColumn<pupilModel, String> courseColumn;
    @FXML
    private TableColumn<pupilModel, String> idColumn;
    @FXML
    private TableColumn<pupilModel, String> nameColumn;
    @FXML
    private TableColumn<pupilModel, String> parentsColumn;
    @FXML
    private TableColumn<pupilModel, String> surnameColumn;

    @FXML
    private TextField cognomsTextField;

    @FXML
    private ChoiceBox<String> cursChoiceBox;

    String[] curs = { "ESO" , "Cicles Formatius" , "Batxillerat"};
    @FXML
    private Button deleteButton;

    @FXML
    private DatePicker dnaixDatePicker;

    @FXML
    private TextField idTextField;

    @FXML
    private Button modulsButton;

    @FXML
    private TextField nomTextField;

    @FXML
    private Button professorButton;

    @FXML
    private TextField prog1TextField;

    @FXML
    private TextField prog2TextField;

    @FXML
    private Button updateButton;

    public void updateButtonOnAction(ActionEvent event) {
        boolean insert = false;
        if (idTextField.getText().isEmpty()||nomTextField.getText().isEmpty() || cognomsTextField.getText().isEmpty() || cursChoiceBox.getValue().isEmpty() || prog1TextField.getText().isEmpty() || prog2TextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        } else {
            String progenitors = "'{\"" + prog1TextField.getText() + "\",\"" + prog2TextField.getText() + "\"}'";
            String data_naixement = dnaixDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            insert = bd.updatePupil(new pupilModel(idTextField.getText(), nomTextField.getText(), cognomsTextField.getText(), data_naixement, cursChoiceBox.getValue(), progenitors));
            if (insert) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informació");
                alert.setHeaderText("Alumne modificat correctament");
                alert.setContentText("Informació");
                alert.showAndWait();
                selectAlumnes();
                valorNull();
            }
        }
    }
    public void deleteButtonOnAction(ActionEvent event){
        boolean delete = false;
        delete = bd.deletePupil(idTextField.getText());
        if(delete){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informació");
            alert.setHeaderText("Alumne afegit correctament");
            alert.setContentText("Informació");
            alert.showAndWait();
            selectAlumnes();
            valorNull();
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
    ObservableList<pupilModel> listView = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectAlumnes();
        cursChoiceBox.getItems().addAll(curs);
    }

    public void addButtonOnAction(){
        boolean insert = false;
        if(nomTextField.getText().isEmpty() || cognomsTextField.getText().isEmpty() || cursChoiceBox.getValue().isEmpty() || prog1TextField.getText().isEmpty() || prog2TextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No has omplert tots els camps");
            alert.setContentText("Error");
            alert.showAndWait();
        }else {
            String progenitors = "'{\""+prog1TextField.getText() + "\",\"" + prog2TextField.getText()+"\"}'";
            String data_naixement = dnaixDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            insert = bd.inserirAlumnes(new pupilModel(idTextField.getId(), nomTextField.getText(), cognomsTextField.getText(), data_naixement, cursChoiceBox.getValue(), progenitors));
            if(insert){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informació");
                alert.setHeaderText("Alumne afegit correctament");
                alert.setContentText("Informació");
                alert.showAndWait();
                selectAlumnes();
                valorNull();
            }
        }
    }

    public void valorNull(){
        idTextField.setText("");
        nomTextField.setText("");
        cognomsTextField.setText("");
        dnaixDatePicker.setValue(null);
        cursChoiceBox.setValue(null);
        prog1TextField.setText("");
        prog2TextField.setText("");
    }
    public void selectAlumnes() {
        listView.clear();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("cognoms"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("data_naixement"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("curs_actual"));
        parentsColumn.setCellValueFactory(new PropertyValueFactory<>("progenitors"));
        bd.selectAlumnes(listView);
        table.setItems(listView);
        table.getSortOrder().add(idColumn);
    }
}
