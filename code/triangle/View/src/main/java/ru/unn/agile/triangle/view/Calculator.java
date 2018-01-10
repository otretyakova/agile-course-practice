package ru.unn.agile.Triangle.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import ru.unn.agile.Triangle.viewmodel.ViewModel;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Calculator {
    @FXML
    private ViewModel viewModel;
    // Dots
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
    //

    // Sides
    @FXML
    private Label sideAB;
    @FXML
    private Label sideBC;
    @FXML
    private Label sideAC;
    //

    // Corners
    @FXML
    private Label cornerABC;
    @FXML
    private Label cornerACB;
    @FXML
    private Label cornerBAC;
    //

    // Other
    @FXML
    private Label perimeterVal;
    @FXML
    private Label squareVal;
    //

    // Status
    @FXML
    private Label statusLine;

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
        // Status
        statusLine.textProperty().bindBidirectional(viewModel.statusProperty());
        // Sides
        sideAB.textProperty().bindBidirectional(viewModel.sideABProperty());
        sideAC.textProperty().bindBidirectional(viewModel.sideACProperty());
        sideBC.textProperty().bindBidirectional(viewModel.sideBCProperty());
        // Corners
        cornerABC.textProperty().bindBidirectional(viewModel.cornerABCProperty());
        cornerACB.textProperty().bindBidirectional(viewModel.cornerACBProperty());
        cornerBAC.textProperty().bindBidirectional(viewModel.cornerBACProperty());
        // Other
        perimeterVal.textProperty().bindBidirectional(viewModel.perimeterProperty());
        squareVal.textProperty().bindBidirectional(viewModel.surfaceAreaProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
