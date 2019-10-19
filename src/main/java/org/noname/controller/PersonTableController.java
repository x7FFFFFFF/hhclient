package org.noname.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.noname.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PersonTableController {


    @FXML
    private TableColumn<String, Employee> age;
    @FXML
    private TableColumn<Integer, Employee> salary;
    @FXML
    private TableView<Employee> personTable;


    public void initialize() {
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        personTable.setRowFactory(rf -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    // ProductDetailsModalStage stage = new ProductDetailsModalStage();
                    // stage.showDetails(row.getItem());
                }
            });
            return row;
        });
    }

    public void fillTable(List<Employee> employees) {
        personTable.setItems(FXCollections.observableArrayList(employees));
    }
}
