package com.example.autonoleggiofx;

import com.example.autonoleggiofx.Model.Auto;
import com.example.autonoleggiofx.Model.AutoManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
        AdminController.InitializeDisponibiliTable(ProduttoreColumn, ModelloColumn, TargaColumn, CostoColumn, DataColumn);
        UpdateTable();
    }

    public void UpdateTable(){
        ObservableList<Auto> clearObservableList = FXCollections.observableArrayList(new ArrayList<>());
        carTable.setItems(clearObservableList);
        ObservableList<Auto> cars = FXCollections.observableList(AutoManager.getAutoNoleggiateList());
        carTable.setItems(cars);
    }



}
