package ru.unn.agile.MetricsDistance.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.MetricsDistance.viewmodel.ViewModel;
import ru.unn.agile.MetricsDistance.Model.MetricsDistance.Metric;

public class MetricsDistance {
    @FXML
    void initialize() {
        txtV1x.textProperty().bindBidirectional(viewModel.x1Property());
        txtV1y.textProperty().bindBidirectional(viewModel.y1Property());
        txtV2x.textProperty().bindBidirectional(viewModel.x2Property());
        txtV2y.textProperty().bindBidirectional(viewModel.y2Property());
        txtDim.textProperty().bindBidirectional(viewModel.dimProperty());
        cbMetric.valueProperty().bindBidirectional(viewModel.metricProperty());
        btnCalc.setOnAction(event -> viewModel.calculate());
    }

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
    private TextField txtDim;
    @FXML
    private ComboBox<Metric> cbMetric;
    @FXML
    private Button btnCalc;
}
