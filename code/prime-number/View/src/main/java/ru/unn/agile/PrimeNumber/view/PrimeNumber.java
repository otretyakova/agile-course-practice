package ru.unn.agile.PrimeNumber.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import ru.unn.agile.PrimeNumber.Model.PrimeNumber.Methods;
import ru.unn.agile.PrimeNumber.viewmodel.Query;

import ru.unn.agile.PrimeNumber.viewmodel.ViewModel;

public class PrimeNumber {
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
    private ListView lstQuery;

    @FXML
    @SuppressWarnings("unchecked")
    void initialize() {
        txtFrom.textProperty().bindBidirectional(viewModel.rangeFromProperty());
        txtTo.textProperty().bindBidirectional(viewModel.rangeToProperty());
        txtNumberOfPrimes.textProperty().bindBidirectional(viewModel.maxCountPrimesProperty());

        cbMethods.valueProperty().bindBidirectional(viewModel.methodProperty());

        lstQuery.itemsProperty().bindBidirectional(viewModel.answersListProperty());
        lstQuery.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Query>() {
                @Override
                public void changed(final ObservableValue<? extends Query> observable,
                                    final Query oldValue, final Query newValue) {
                    viewModel.chooseAnswerById(lstQuery.getSelectionModel().getSelectedIndex());
                }
            }
        );

        buFind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
