package ru.unn.agile.NumbersInWords.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.NumbersInWords.viewmodel.ViewModel;

public class TranslatorNumbers {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtNumber;
    @FXML
    private Button btnTranslate;

    @FXML
    void initialize() {

        txtNumber.textProperty().bindBidirectional(viewModel.numberProperty());
        btnTranslate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.translate();
            }
        });
    }
}
