package ru.unn.agile.QuadraticEquation.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import ru.unn.agile.QuadraticEquation.viewmodel.ViewModel;

import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class Solution {

    @FXML
    void initialize() {

        final List<TextField> textFields = new ArrayList<TextField>() {
            {
                add(first);
                add(second);
                add(third);
            }
        };
        first.textProperty().bindBidirectional(viewModel.aProperty());
        second.textProperty().bindBidirectional(viewModel.bProperty());
        third.textProperty().bindBidirectional(viewModel.cProperty());

        Pattern p = Pattern.compile("[-+]?[0-9]{0,7}(\\.[0-9]{0,7})?([0-9]{0,3})?");
        for (TextField textField : textFields) {
            TextFormatter<String> formatter = new TextFormatter<>(
                    (UnaryOperator<TextFormatter.Change>) change -> {
                        return p.matcher(change.getControlNewText()).matches() ? change : null;
                    });
            textField.setTextFormatter(formatter);
        }
        btnSolve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.solve();
            }
        });
    }

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
}


