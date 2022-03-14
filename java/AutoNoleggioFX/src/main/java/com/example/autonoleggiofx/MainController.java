package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.AutoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainController {



    @FXML
    TableView<Auto> carTable;

    public TableColumn<Auto, Auto.Produttore> ProduttoreColumn;
    public TableColumn<Auto, String> ModelloColumn;
    public TableColumn<Auto, String> TargaColumn;
    public TableColumn<Auto, Float> CostoColumn;
    public TableColumn<Auto, LocalDate> DataColumn;
    public ChoiceBox<String> FileTypeChoiceBox;
    public ChoiceBox<String> CarModelChoiceBox;

    public Label TargaValue;
    public Label CostoValue;
    public Label ModelloValue;
    public Label DataValue;
    public Label ProduttoreValue;
    @FXML
    public void initialize(){
        InitializeTable();
        AutoManager.Add(new Auto(Auto.Produttore.FIAT, "Tipo",  "20/02/2022", 15f));
        AutoManager.Add(new Auto(Auto.Produttore.FERRARI, "modello","21/02/2020", 30f));
        InitializeChoiceBox();
        InsertData();

        carTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> CarInspector(newValue));
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
        ProduttoreColumn.setCellValueFactory(new PropertyValueFactory<>("Produttore"));
        ModelloColumn.setCellValueFactory(new PropertyValueFactory<>("Modello"));
        TargaColumn.setCellValueFactory(new PropertyValueFactory<>("Targa"));
        CostoColumn.setCellValueFactory(new PropertyValueFactory<>("CostoGiornaliero"));
        DataColumn.setCellValueFactory(new PropertyValueFactory<>("DataNoleggio"));
    }

    private void InsertData(){
        ObservableList<Auto> cars = FXCollections.observableList(AutoManager.getAutoList());
        carTable.setItems(cars);
        System.out.println("Sono stati caricate "+AutoManager.getCounter()+" auto.");
    }
    private void CarInspector(Auto car){
        ProduttoreValue.setText(car.getProduttore());
        ModelloValue.setText(car.getModello());
        TargaValue.setText(car.getTarga());
        CostoValue.setText(car.getCostoGiornaliero().toString());
        DataValue.setText(car.getDataNoleggio().toString());
    }

    public void saveAsButton(){
        switch(FileTypeChoiceBox.getValue()){
            case "JSON" -> AutoManager.saveAsJSON();
            case "CSV" -> AutoManager.saveAsCSV();
            default -> System.out.println("No.");
        }
    }

    public void GetCarsByMarca(){
        List<Auto> filteredList = AutoManager.getCarsByModel(CarModelChoiceBox.getValue());
        ObservableList<Auto> filteredObservableList = FXCollections.observableList(filteredList);
        carTable.getItems().clear();
        carTable.setItems(filteredObservableList);
    }


}