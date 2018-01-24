package ru.unn.agile.AssessmentsAccounting.viewmodel;

import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import ru.unn.agile.AssessmentsAccounting.model.AssessmentsTable;
import ru.unn.agile.AssessmentsAccounting.model.Student;
import ru.unn.agile.AssessmentsAccounting.model.Assessment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.InvalidParameterException;

public class ViewModel {

    public ViewModel() {
        this.currentAssessmentIndex = -1;
        newStudent.set("");
        newSubject.set("");
        newAssessment.set(Assessment.Perfect);

        averageAssessment.set("");
        averageAssessmentForCurrentSubject.set("");
        averageAssessmentOfStudents.set("");

        currentStudent.set("Students");
        currentSubject.set("Subjects");

        status.set("");
    }

    public void addStudent() {
        try {
            table.addStudent(newStudent.get());
            updateStudents();
            currentStudent.set(newStudent.get());
            status.set("");
        } catch (InvalidParameterException t) {
            status.set(Status.INVALID_STUDENT_NAME.toString());
        }
    }

    public void addSubject() {
        try {
            table.addSubject(newSubject.get());
            updateSubjects();
            currentSubject.set(newSubject.get());
            status.set("");
        } catch (InvalidParameterException t) {
            status.set(Status.INVALID_SUBJECT_NAME.toString());
        }
    }

    public void addAssessment() {
        try {
            table.addAssessment(newAssessment.get(), currentStudent.get(), currentSubject.get());
            updateAssessments();
            currentAssessment.set(newAssessment.get());
            status.set("");
        } catch (InvalidParameterException t) {
            status.set(Status.IMMPOSIBLE_TO_ADD.toString());
        }
    }

    public void editStudent() {
        try {
            if (currentStudent.get().equalsIgnoreCase("Students")) {
                status.set("Before editing, add a student.");
            }
            table.renameStudent(currentStudent.get(), newStudent.get());
            updateStudents();
            currentStudent.set(newStudent.get());
            status.set("");
        } catch (InvalidParameterException t) {
            status.set(Status.INVALID_STUDENT_NAME.toString());
        }
    }

    public void editSubject() {
        try {
            if (currentStudent.get().equalsIgnoreCase("Subjects")) {
                status.set("Before editing, add a subject.");
            }
            table.renameSubject(currentSubject.get(), newSubject.get());
            updateSubjects();
            currentSubject.set(newSubject.get());
            status.set("");
        } catch (InvalidParameterException t) {
            status.set(Status.INVALID_SUBJECT_NAME.toString());
        }
    }

    public void editAssessment() {
        try {
            try {
                table.changeAssessment(currentAssessmentIndex, newAssessment.get(),
                        currentStudent.get(), currentSubject.get());
            } catch (InvalidParameterException t) {
                status.set("Before editing, add an assessment.");
                return;
            }
            updateAssessments();
            currentAssessment.set(newAssessment.get());
            status.set("");
        } catch (InvalidParameterException t) {
            status.set(Status.IMMPOSIBLE_TO_ADD.toString());
        }
    }

    public void updateAssessments() {
        try {
            ObservableList<Assessment> aNamesList = assessmentsOfStudent.get();

            aNamesList.clear();
            for (Assessment mark : table.getAssessmentsForStudent(currentSubject.get(),
                    currentStudent.get())) {
                aNamesList.add(mark);
            }

            updateAverageAssessments();
            status.set("");
        } catch (InvalidParameterException t) {
            status.set(Status.NO_ASSESSMENTS.toString());
        }
    }

    public final void updateIndexOfAssessment(final int index) {
        currentAssessmentIndex = index;
    }
    public StringProperty getNewStudent() {
        return newStudent;
    }
    public StringProperty getNewSubject() {
        return newSubject;
    }
    public ObjectProperty<Assessment> getNewAssessment() {
        return newAssessment;
    }

    public ObjectProperty<String> getCurrentStudent() {
        return currentStudent;
    }
    public ObjectProperty<String> getCurrentSubject() {
        return currentSubject;
    }
    public ObjectProperty<Assessment> getCurrentAssessment() {
        return currentAssessment;
    }

    public ObservableList<String> getStudents() {
        return students.get();
    }

    public ObservableList<String> getSubjects() {
        return subjects.get();
    }

    public ObservableList<Assessment> getAssessmentsOfStudent() {
        return assessmentsOfStudent.get();
    }

    public final ObservableList<Assessment> getAssessments() {
        return assessments.get();
    }

    public StringProperty averageAssessmentProperty() {
        return averageAssessment;
    }
    public String getAverageAssessment() {
        return averageAssessment.get();
    }

    public StringProperty averageAssessmentForCurrentSubjectProperty() {
        return averageAssessmentForCurrentSubject;
    }
    public String getAverageAssessmentForCurrentSubject() {
        return averageAssessmentForCurrentSubject.get();
    }

    public StringProperty averageAssessmentOfStudentsProperty() {
        return averageAssessmentOfStudents;
    }
    public String getAverageAssessmentOfStudents() {
        return averageAssessmentOfStudents.get();
    }

    private void updateStudents() {
        ObservableList<String> aNamesList = students.get();
        aNamesList.clear();
        for (Student student : table.getStudents()) {
            aNamesList.add(student.getName());
        }
    }

    private void updateSubjects() {
        ObservableList<String> aNamesList = subjects.get();
        aNamesList.clear();
        for (String name : table.getSubjects()) {
            aNamesList.add(name);
        }
    }

    private void refreshAverageAssessments() {
        averageAssessment.set("");
        averageAssessmentForCurrentSubject.set("");
        averageAssessmentOfStudents.set("");
    }

    private void updateAverageAssessments() {
        refreshAverageAssessments();

        updateAverageAssessment();
        updateAverageAssessmentForCurrentSubject();
        updateAverageAssessmentForStudents();
    }

    private void updateAverageAssessment() {
        try {
            averageAssessment.set(
                    String.valueOf(table.getAverageAssessmentForStudent(currentStudent.get())));
        } catch (InvalidParameterException t) {
            status.set(Status.NO_ASSESSMENTS.toString());
        }
    }

    private void updateAverageAssessmentForCurrentSubject() {
        try {
            averageAssessmentForCurrentSubject.set(
                    String.valueOf(
                            table.getAverageAssessment(currentStudent.get(),
                                    currentSubject.get())));
        } catch (InvalidParameterException t) {
            status.set(Status.NO_ASSESSMENTS.toString());
        }
    }

    private void updateAverageAssessmentForStudents() {
        try {
            averageAssessmentOfStudents.set(
                    String.valueOf(table.getAverageAssessmentForSubject(currentSubject.get())));
        } catch (InvalidParameterException t) {
            status.set(Status.NO_ASSESSMENTS.toString());
        }
    }

    public final String getStatus() {
        return status.get();
    }
    public StringProperty statusProperty() {
        return status;
    }
    private final AssessmentsTable table = new AssessmentsTable();

    private final StringProperty newStudent = new SimpleStringProperty();
    private final StringProperty newSubject = new SimpleStringProperty();
    private final ObjectProperty<Assessment> newAssessment = new SimpleObjectProperty<>();

    private final ObjectProperty<String> currentStudent = new SimpleObjectProperty<>();
    private final ObjectProperty<String> currentSubject = new SimpleObjectProperty<>();
    private final ObjectProperty<Assessment> currentAssessment = new SimpleObjectProperty<>();
    private int currentAssessmentIndex;

    private final ObjectProperty<ObservableList<String>> students =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());
    private final ObjectProperty<ObservableList<String>> subjects =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());
    private final ObjectProperty<ObservableList<Assessment>> assessmentsOfStudent =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());

    private final StringProperty averageAssessment = new SimpleStringProperty();
    private final StringProperty averageAssessmentForCurrentSubject = new SimpleStringProperty();
    private final StringProperty averageAssessmentOfStudents = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Assessment>> assessments =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Assessment.values()));

    private final StringProperty status = new SimpleStringProperty();
}

enum Status {
    INVALID_STUDENT_NAME("Some student already has this name or name is empty."),
    INVALID_SUBJECT_NAME("Some subject already has this name or name is empty."),
    IMMPOSIBLE_TO_ADD("Choose student and subject to add or edit an assessment"),
    NO_ASSESSMENTS("The student has no assessments for this subject.");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
