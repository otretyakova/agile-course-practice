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
    }

    public void addStudent() {
        table.addStudent(newStudent.get());
        updateStudents();
        currentStudent.set(newStudent.get());
    }

    public void addSubject() {
        table.addSubject(newSubject.get());
        updateSubjects();
        currentSubject.set(newSubject.get());
    }

    public void addAssessment() {
        table.addAssessment(newAssessment.get(), currentStudent.get(), currentSubject.get());
        updateAssessments();
        currentAssessment.set(newAssessment.get());
    }

    public void editStudent() {
        table.renameStudent(currentStudent.get(), newStudent.get());
        updateStudents();
        currentStudent.set(newStudent.get());
    }

    public void editSubject() {
        table.renameSubject(currentSubject.get(), newSubject.get());
        updateSubjects();
        currentSubject.set(newSubject.get());
    }

    public void editAssessment() {
        table.changeAsessment(currentAssessmentIndex, newAssessment.get(), currentStudent.get(),
                currentSubject.get());
        updateAssessments();
        currentAssessment.set(newAssessment.get());
    }

    public void updateAssessments() {

        if (currentSubject.get().equalsIgnoreCase("Subjects")) {
            return;
        }
        if (currentStudent.get().equalsIgnoreCase("Students")) {
            return;
        }
        ObservableList<Assessment> aNamesList = assessmentsOfStudent.get();

        aNamesList.clear();
        for (Assessment mark : table.getAssessmentsForStudent(currentSubject.get(),
                currentStudent.get())) {
            aNamesList.add(mark);
        }

        updateAverageAssessments();
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
        averageAssessment.set(
                String.valueOf(table.getAverageAssessmentForStudent(currentStudent.get())));
    }

    private void updateAverageAssessmentForCurrentSubject() {
        averageAssessmentForCurrentSubject.set(
                String.valueOf(
                        table.getAverageAssessment(currentStudent.get(), currentSubject.get())));
    }

    private void updateAverageAssessmentForStudents() {
        averageAssessmentOfStudents.set(
                String.valueOf(table.getAverageAssessmentForSubject(currentSubject.get())));
    }

    private AssessmentsTable table = new AssessmentsTable();

    private final StringProperty newStudent = new SimpleStringProperty();
    private final StringProperty newSubject = new SimpleStringProperty();
    private final ObjectProperty<Assessment> newAssessment = new SimpleObjectProperty<>();

    private final ObjectProperty<String> currentStudent = new SimpleObjectProperty<>();
    private final ObjectProperty<String> currentSubject = new SimpleObjectProperty<>();
    private final ObjectProperty<Assessment> currentAssessment = new SimpleObjectProperty<>();
    private int currentAssessmentIndex;

    private ObjectProperty<ObservableList<String>> students =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<ObservableList<String>> subjects =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());
    private ObjectProperty<ObservableList<Assessment>> assessmentsOfStudent =
            new SimpleObjectProperty<>(FXCollections.observableArrayList());

    private final StringProperty averageAssessment = new SimpleStringProperty();
    private final StringProperty averageAssessmentForCurrentSubject = new SimpleStringProperty();
    private final StringProperty averageAssessmentOfStudents = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Assessment>> assessments =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Assessment.values()));
}
