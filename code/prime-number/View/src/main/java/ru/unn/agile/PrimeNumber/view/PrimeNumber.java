package ru.unn.agile.PrimeNumber.view;

import javafx.beans.binding.Bindings;
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
    @SuppressWarnings("unchecked")
    void initialize() {
        Bindings.bindBidirectional(txtFrom.textProperty(), viewModel.rangeFromProperty());
        Bindings.bindBidirectional(txtTo.textProperty(), viewModel.rangeToProperty());
        Bindings.bindBidirectional(txtNumberOfPrimes.textProperty(),
                viewModel.maxCountPrimesProperty());

        Bindings.bindBidirectional(cbMethods.valueProperty(), viewModel.methodProperty());

        Bindings.bindBidirectional(lstQuery.itemsProperty(), viewModel.answersListProperty());
        lstQuery.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    viewModel.chooseAnswerById(lstQuery.getSelectionModel().getSelectedIndex());
                }
        );

        buFind.setOnAction(
                (event) -> viewModel.calculate()
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
