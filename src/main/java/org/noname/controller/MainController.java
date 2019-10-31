package org.noname.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.noname.dao.EmployeesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainController {
    @FXML
    private Button load;

    @FXML
    private TextField searchTerm;

    @Autowired
    private PersonTableController tableController;
    @Autowired
    private EmployeesDao employeesDao;

    public void onClickLoad(MouseEvent mouseEvent) {
        load.setDisable(true);
        tableController.fillTable(employeesDao.getAllEmployees(searchTerm.getText(), 1));
        load.setDisable(false);
    }
}
