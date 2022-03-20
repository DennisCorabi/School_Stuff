package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.AutoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class AutoNoleggiateController {

    @FXML
    TableView<Auto> carTable;

    public TableColumn<Auto, Auto.Produttore> ProduttoreColumn;
    public TableColumn<Auto, String> ModelloColumn;
    public TableColumn<Auto, String> TargaColumn;
    public TableColumn<Auto, Float> CostoColumn;
    public TableColumn<Auto, LocalDate> DataColumn;

    @FXML
    public void initialize(){
        InitializeNoleggiateTable();

        //Se è la prima volta nel programma che visualizzo le auto noleggiate. Evita doppie letture del file
        if (AutoManager.getAutoNoleggiateList().isEmpty()) AutoManager.ReadJsonNoleggiati();
        UpdateTable();
    }

    public void InitializeNoleggiateTable(){
        ProduttoreColumn.setCellValueFactory(new PropertyValueFactory<>("Produttore"));
        ModelloColumn.setCellValueFactory(new PropertyValueFactory<>("Modello"));
        TargaColumn.setCellValueFactory(new PropertyValueFactory<>("Targa"));
        CostoColumn.setCellValueFactory(new PropertyValueFactory<>("CostoGiornaliero"));
        DataColumn.setCellValueFactory(new PropertyValueFactory<>("DataNoleggio"));
    }

    public void UpdateTable(){

        ObservableList<Auto> clearObservableList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearObservableList);

        ObservableList<Auto> cars = FXCollections.observableList(AutoManager.getAutoNoleggiateList());
        carTable.setItems(cars);
    }



}
