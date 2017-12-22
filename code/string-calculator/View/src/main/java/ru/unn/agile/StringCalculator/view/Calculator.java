package ru.unn.agile.StringCalculator.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.StringCalculator.viewmodel.ViewModel;

public class Calculator {
    @FXML
    void initialize() {
        txtInputString.textProperty().bindBidirectional(viewModel.inputStringProperty());
        btnCalc.setOnAction(event -> viewModel.calculate());
    }

    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtInputString;
    @FXML
    private Button btnCalc;
}
