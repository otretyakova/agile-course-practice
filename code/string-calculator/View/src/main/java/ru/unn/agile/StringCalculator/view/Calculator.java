package ru.unn.agile.StringCalculator.view;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.StringCalculator.viewmodel.ViewModel;

public class Calculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtInputString;
    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {
        txtInputString.textProperty().bindBidirectional(viewModel.inputStringProperty());
        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
