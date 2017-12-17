package ru.unn.agile.ConverterTemperature.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.ConverterTemperature.viewmodel.ViewModel;
import ru.unn.agile.ConverterTemperature.viewmodel.NameSystem;


public class ConverterTemperature {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField inputTemp;
    @FXML
    private ComboBox<NameSystem> cbOperation;
    @FXML
    private ComboBox<NameSystem> cbOperation1;
    @FXML
    private Button btnCalc;


    @FXML
    void initialize() {
        inputTemp.textProperty().bindBidirectional(viewModel.inputTemperatureProperty());
        cbOperation.valueProperty().bindBidirectional(viewModel.inputTypeProperty());
        cbOperation1.valueProperty().bindBidirectional(viewModel.outputTypeProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.convert();
            }
        });
    }
}
