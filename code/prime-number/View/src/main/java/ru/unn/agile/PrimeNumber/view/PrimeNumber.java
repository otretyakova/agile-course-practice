package ru.unn.agile.PrimeNumber.view;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TextFormatter;
import ru.unn.agile.PrimeNumber.Model.PrimeNumber.Methods;

import java.util.regex.Pattern;

import ru.unn.agile.PrimeNumber.infrastructure.TxtLogger;
import ru.unn.agile.PrimeNumber.viewmodel.Query;
import ru.unn.agile.PrimeNumber.viewmodel.ViewModel;

public class PrimeNumber {
    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./TxtLogger_Tests-lab3.log"));

        final ChangeListener<Boolean> rangeFocusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onRangeFocusChanged(oldValue, newValue);
            }
        };
        Bindings.bindBidirectional(txtFrom.textProperty(), viewModel.rangeFromProperty());
        txtFrom.focusedProperty().addListener(rangeFocusChangeListener);
        Bindings.bindBidirectional(txtTo.textProperty(), viewModel.rangeToProperty());
        txtTo.focusedProperty().addListener(rangeFocusChangeListener);

        final ChangeListener<Boolean> primesFocusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onMaxCountPrimesFocusChanged(oldValue, newValue);
            }
        };
        Bindings.bindBidirectional(txtNumberOfPrimes.textProperty(),
                viewModel.maxCountPrimesProperty());
        txtNumberOfPrimes.focusedProperty().addListener(primesFocusChangeListener);

        Bindings.bindBidirectional(cbMethods.valueProperty(), viewModel.methodProperty());
        cbMethods.valueProperty().addListener(new ChangeListener<Methods>() {
            @Override
            public void changed(final ObservableValue<? extends Methods> observable,
                                final Methods oldValue,
                                final Methods newValue) {
                viewModel.onMethodChanged(oldValue, newValue);
            }
        });

        Bindings.bindBidirectional(lstQuery.itemsProperty(), viewModel.answersListProperty());
        lstQuery.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    viewModel.chooseAnswerById(lstQuery.getSelectionModel().getSelectedIndex());
                }
        );

        buFind.setOnAction(
                (event) -> viewModel.calculate()
        );

        txtTo.setTextFormatter(nonNegativeIntegerFormatter());
        txtFrom.setTextFormatter(nonNegativeIntegerFormatter());
        txtNumberOfPrimes.setTextFormatter(nonNegativeIntegerFormatter());
    }

    private TextFormatter<String> nonNegativeIntegerFormatter() {
        Pattern pattern = Pattern.compile("\\d*");
        return new TextFormatter<>(
                change -> pattern.matcher(change.getControlNewText()).matches() ? change : null
        );
    }

    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtFrom;
    @FXML
    private TextField txtTo;
    @FXML
    private TextField txtNumberOfPrimes;
    @FXML
    private ChoiceBox<Methods> cbMethods;
    @FXML
    private Button buFind;
    @FXML
    private ListView<Query> lstQuery;
}
