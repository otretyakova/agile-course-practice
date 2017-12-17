package ru.unn.agile.MetricsDistance.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.MetricsDistance.viewmodel.ViewModel;

public class MetricsDistance {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtV1x;
    @FXML
    private TextField txtV1y;
    @FXML
    private TextField txtV2x;
    @FXML
    private TextField txtV2y;
    @FXML
    private ComboBox<String> cbOperation;
    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        txtV1x.textProperty().bindBidirectional(viewModel.x1Property());
        txtV1y.textProperty().bindBidirectional(viewModel.y1Property());
        txtV2x.textProperty().bindBidirectional(viewModel.x2Property());
        txtV2y.textProperty().bindBidirectional(viewModel.y2Property());

        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
