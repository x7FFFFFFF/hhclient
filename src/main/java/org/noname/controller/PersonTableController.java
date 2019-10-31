package org.noname.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.noname.View;
import org.noname.entities.Employee;
import org.noname.entities.EmployeeSimple;
import org.noname.entities.ResumeLink;
import org.noname.view.EmployeeDetailsStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.util.List;

@Component
public class PersonTableController {

    @FXML
    private TableColumn<String, ResumeLink> jobTitle;
    @FXML
    private TableColumn<String, EmployeeSimple> age;
    @FXML
    private TableColumn<String, EmployeeSimple> salary;
    @FXML
    private TableView<EmployeeSimple> personTable;

    @View("employeeDetails")
    private Parent employeeDetails;

    @Autowired
    private ConfigurableBeanFactory expressionResolver;


    public void initialize() {
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        jobTitle.setCellValueFactory(new PropertyValueFactory<>("resumeLink"));
        Scene employeeDetailsScene = new Scene(employeeDetails);
        personTable.setRowFactory(rf -> {
            TableRow<EmployeeSimple> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    EmployeeDetailsStage stage = new EmployeeDetailsStage(employeeDetailsScene, row.getItem());
                    stage.show();
                }
            });
            return row;
        });


    }

    public void fillTable(List<EmployeeSimple> employees) {
        personTable.setItems(FXCollections.observableArrayList(employees));
    }
}
