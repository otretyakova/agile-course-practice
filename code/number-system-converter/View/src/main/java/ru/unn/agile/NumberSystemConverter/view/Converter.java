package ru.unn.agile.NumberSystemConverter.view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.NumberSystemConverter.model.NumberSystemBase;
import ru.unn.agile.NumberSystemConverter.viewmodel.NumberSystemConverterViewModel;



public class Converter {
    @FXML
    void initialize() {
        this.textAreaBaseNumber.textProperty().bindBidirectional(
                this.viewModel.numberInBaseNumberSystemProperty()
        );

        this.textAreaTargetNumber.textProperty().bindBidirectional(
                this.viewModel.numberInTargetNumberSystemProperty()
        );

        this.comboBoxBase.valueProperty().bindBidirectional(
                this.viewModel.baseNumberSystemProperty()
        );

        this.comboBoxTarget.valueProperty().bindBidirectional(
                this.viewModel.targetNumberSystemProperty()
        );

        this.buttonConvert.disableProperty().bind(
                this.viewModel.conversionEnabledProperty().not()
        );

        this.buttonConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                viewModel.convert();
            }
        });

        this.viewModel.errorMessageIsShownProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Alert alert = new Alert(Alert.AlertType.ERROR, this.viewModel.getErrorMessage(), ButtonType.CLOSE);
                alert.showAndWait()
                        .filter(response -> response == ButtonType.CLOSE)
                        .ifPresent(response -> this.viewModel.closeErrorDialog());
            }
        });
    }

    @FXML
    private NumberSystemConverterViewModel viewModel;

    @FXML
    private TextArea textAreaBaseNumber;

    @FXML
    private TextArea textAreaTargetNumber;

    @FXML
    private ComboBox<NumberSystemBase> comboBoxBase;

    @FXML
    private ComboBox<NumberSystemBase> comboBoxTarget;

    @FXML
    private Button buttonConvert;
}
