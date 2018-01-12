package ru.unn.agile.Triangle.View;

import ru.unn.agile.Triangle.ViewModel.ViewModel;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class Calculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField pointAX;
    @FXML
    private TextField pointAY;
    @FXML
    private TextField pointBX;
    @FXML
    private TextField pointBY;
    @FXML
    private TextField pointCX;
    @FXML
    private TextField pointCY;
    @FXML
    private Button btnCalc;


    @FXML
    void initialize() {
        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        pointAX.textProperty().bindBidirectional(viewModel.coordAxProperty());
        pointAY.textProperty().bindBidirectional(viewModel.coordAyProperty());
        pointBX.textProperty().bindBidirectional(viewModel.coordBxProperty());
        pointBY.textProperty().bindBidirectional(viewModel.coordByProperty());
        pointCX.textProperty().bindBidirectional(viewModel.coordCxProperty());
        pointCY.textProperty().bindBidirectional(viewModel.coordCyProperty());
        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
