package ru.unn.agile.AssessmentsAccounting.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.AssessmentsAccounting.model.Assessment;
import ru.unn.agile.AssessmentsAccounting.viewmodel.ViewModel;

public class Accounting {

    @FXML
    void initialize() {

        txtSubjectName.textProperty().bindBidirectional(viewModel.getNewSubject());
        txtStudentName.textProperty().bindBidirectional(viewModel.getNewStudent());
        cbAssessments.valueProperty().bindBidirectional(viewModel.getNewAssessment());

        cbStudents.valueProperty().bindBidirectional(viewModel.getCurrentStudent());
        cbSubjects.valueProperty().bindBidirectional(viewModel.getCurrentSubject());
        cbAssessmentOfStudent.valueProperty().bindBidirectional(viewModel.getCurrentAssessment());

        btnAddStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addStudent();
            }
        });

        btnAddSubject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addSubject();
            }
        });

        btnAddMark.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addAssessment();
            }
        });

        btnEditStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.editStudent();
            }
        });

        btnEditSubject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.editSubject();
            }
        });

        btnEditMark.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.editAssessment();
            }
        });

        cbSubjects.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.updateAssessments();
            }
        });

        cbStudents.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.updateAssessments();
            }
        });

        cbAssessmentOfStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.updateIndexOfAssessment(
                        cbAssessmentOfStudent.getSelectionModel().getSelectedIndex());
            }
        });
    }

    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtStudentName;
    @FXML
    private TextField txtSubjectName;
    @FXML
    private ComboBox<Assessment> cbAssessments;
    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnAddSubject;
    @FXML
    private Button btnAddMark;
    @FXML
    private Button btnEditStudent;
    @FXML
    private Button btnEditSubject;
    @FXML
    private Button btnEditMark;
    @FXML
    private ComboBox<Assessment> cbAssessmentOfStudent;
    @FXML
    private ComboBox<String> cbStudents;
    @FXML
    private ComboBox<String> cbSubjects;
}
