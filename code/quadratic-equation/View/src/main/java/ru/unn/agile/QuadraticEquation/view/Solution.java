package ru.unn.agile.QuadraticEquation.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
//import ru.unn.agile.QuadraticEquation.model.QuadraticEquation;
import ru.unn.agile.QuadraticEquation.viewmodel.ViewModel;

public class Solution {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField first;
    @FXML
    private TextField second;
    @FXML
    private TextField third;
    @FXML
    private Button btnSolve;

    @FXML
    void initialize() {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        first.textProperty().bindBidirectional(viewModel.aProperty());
        second.textProperty().bindBidirectional(viewModel.bProperty());
        third.textProperty().bindBidirectional(viewModel.cProperty());

        btnSolve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.solve();
            }
        });
    }
}

