package ru.unn.agile.MetricsDistance.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.MetricsDistance.viewmodel.ViewModel;
import ru.unn.agile.MetricsDistance.Model.Metric;
import ru.unn.agile.MetricsDistance.Infrastructure.TxtLogger;

public class MetricsDistance {
    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./TxtLogger-lab3.log"));

        txtV1x.textProperty().bindBidirectional(viewModel.vec1XProperty());
        txtV1y.textProperty().bindBidirectional(viewModel.vec1YProperty());
        txtV2x.textProperty().bindBidirectional(viewModel.vec2XProperty());
        txtV2y.textProperty().bindBidirectional(viewModel.vec2YProperty());
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
