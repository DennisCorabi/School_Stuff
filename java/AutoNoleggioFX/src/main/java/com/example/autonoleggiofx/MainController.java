package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.AutoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class MainController {



    @FXML
    TableView<Auto> carTable;

    public TableColumn<Auto, Auto.Marca> MarcaColumn;
    public TableColumn<Auto, String> ModelloColumn;
    public TableColumn<Auto, String> TargaColumn;
    public TableColumn<Auto, Float> CostoColumn;
    public TableColumn<Auto, LocalDate> DataColumn;
    public ChoiceBox<String> FileTypeChoiceBox;
    public ChoiceBox<String> CarModelChoiceBox;

    public static Integer counter = 2;      //numero delle auto disponibili
    @FXML
    public void initialize(){
        InitializeTable();
        InitializeChoiceBox();
        InsertData();


    }

    private void InitializeChoiceBox(){
        List<String> fileTypesList = new ArrayList<>() {{add("JSON"); add("CSV");}};
        ObservableList<String> fileTypesObservableList = FXCollections.observableList(fileTypesList);
        FileTypeChoiceBox.setItems(fileTypesObservableList);
        List<String> carModelsList = new ArrayList<>() {{add("FIAT"); add("FERRARI"); add("LAMBROGHINI");}};
        ObservableList<String> carModelsObservableList = FXCollections.observableList(carModelsList);
        CarModelChoiceBox.setItems(carModelsObservableList);
    }

    private void InitializeTable(){
        MarcaColumn.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        ModelloColumn.setCellValueFactory(new PropertyValueFactory<>("Modello"));
        TargaColumn.setCellValueFactory(new PropertyValueFactory<>("Targa"));
        CostoColumn.setCellValueFactory(new PropertyValueFactory<>("CostoGiornaliero"));
        DataColumn.setCellValueFactory(new PropertyValueFactory<>("DataNoleggio"));
    }

    public void InsertData(){
        if (carTable.getItems().isEmpty() || carTable.getItems().size()<=counter){
            carTable.getItems().clear();
            readData();

        }

        carTable.setItems(FXCollections.observableList(AutoManager.getAutoList()));
    }

    private void readData(){
        AutoManager.Add(new Auto(Auto.Marca.FIAT, "Tipo",  "20/02/2022", 15f));
        AutoManager.Add(new Auto(Auto.Marca.FERRARI, "modello","21/02/2020", 30f));
    }

    public void saveAsButton(){
        switch(FileTypeChoiceBox.getValue()){
            case "JSON" -> AutoManager.saveAsJSON();
            case "CSV" -> AutoManager.saveAsCSV();
            default -> System.out.println("No.");
        }
    }
}