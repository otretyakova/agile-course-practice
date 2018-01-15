package ru.unn.agile.StringCalculator.view;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.StringCalculator.viewmodel.ViewModel;
import ru.unn.agile.StringCalculator.infrastructure.TxtLogger;

public class Calculator {
    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./TxtLogger.log"));

        final ChangeListener<Boolean> focusChangeListener = (observable, oldValue, newValue)
                -> viewModel.onFocusChanged(oldValue, newValue);

        txtInputString.textProperty().bindBidirectional(viewModel.inputStringProperty());
        txtInputString.focusedProperty().addListener(focusChangeListener);

        btnCalc.setOnAction(event -> viewModel.calculate());
    }

    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtInputString;
    @FXML
    private Button btnCalc;
}
