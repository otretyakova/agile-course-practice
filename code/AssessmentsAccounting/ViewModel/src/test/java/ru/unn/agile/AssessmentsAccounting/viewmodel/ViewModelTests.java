package ru.unn.agile.AssessmentsAccounting.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.AssessmentsAccounting.model.Assessment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canCreateViewModel() {
        assertNotNull(viewModel);
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getNewStudent().get());
        assertEquals("", viewModel.getNewSubject().get());
        assertEquals(Assessment.Perfect, viewModel.getNewAssessment().get());

        assertEquals("", viewModel.getAverageAssessment());
        assertEquals("", viewModel.getAverageAssessmentOfStudents());
        assertEquals("", viewModel.getAverageAssessmentForCurrentSubject());

        assertEquals("Students", viewModel.getCurrentStudent().get());
        assertEquals("Subjects", viewModel.getCurrentSubject().get());

        assertEquals(0, viewModel.getStudents().size());
        assertEquals(0, viewModel.getSubjects().size());
        assertEquals(0, viewModel.getAssessmentsOfStudent().size());

        assertEquals(Assessment.Perfect, viewModel.getNewAssessment().get());
    }

    @Test
    public void canAddNewStudent() {
        viewModel.getNewStudent().set("Maxim");
        viewModel.addStudent();
        assertEquals(1, viewModel.getStudents().size());
    }

    @Test
    public void canAddNewSubject() {
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();
        assertEquals(1, viewModel.getSubjects().size());
    }

    @Test
    public void canAddAssessment() {
        viewModel.getNewStudent().set("Great Max");
        viewModel.addStudent();
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();

        viewModel.getNewAssessment().set(Assessment.VeryBad);
        viewModel.addAssessment();
        assertEquals(1, viewModel.getAssessmentsOfStudent().size());
    }

    @Test
    public void currentStudentIsNewStudent() {
        viewModel.getNewStudent().set("Maxim");
        viewModel.addStudent();

        assertEquals(viewModel.getNewStudent().get(), viewModel.getCurrentStudent().get());
    }

    @Test
    public void currentSubjectIsNewSubject() {
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();

        assertEquals(viewModel.getNewSubject().get(), viewModel.getCurrentSubject().get());
    }

    @Test
    public void currentAssessmentIsNewAssessment() {
        viewModel.getNewStudent().set("Legat Maximus");
        viewModel.addStudent();
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();

        viewModel.getNewAssessment().set(Assessment.VeryBad);
        viewModel.addAssessment();
        assertEquals(viewModel.getNewAssessment().get(), viewModel.getCurrentAssessment().get());
    }

    @Test
    public void canGetAverageAssessment() {
        viewModel.getNewStudent().set("Maxik");
        viewModel.addStudent();
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();

        viewModel.getNewAssessment().set(Assessment.VeryBad);
        viewModel.addAssessment();
        assertEquals("1.0", viewModel.averageAssessmentProperty().get());
    }

    @Test
    public void averageAssessmentForSeveralMarks() {
        viewModel.getNewStudent().set("Maxon");
        viewModel.addStudent();
        viewModel.getNewSubject().set("Programming");
        viewModel.addSubject();

        viewModel.getNewAssessment().set(Assessment.VeryBad);
        viewModel.addAssessment();
        viewModel.getNewAssessment().set(Assessment.Bad);
        viewModel.addAssessment();
        assertEquals("1.5", viewModel.averageAssessmentProperty().get());
    }
    @Test
    public void canGetAverageAssessmentOfStudentsForOneStudent() {
        viewModel.getNewStudent().set("Maximilyan");
        viewModel.addStudent();
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();
        viewModel.getNewAssessment().set(Assessment.VeryBad);
        viewModel.addAssessment();

        assertEquals("1.0", viewModel.averageAssessmentOfStudentsProperty().get());
    }

    @Test
    public void canGetAverageAssessmentOfStudentsForSeveralStudents() {
        viewModel.getNewStudent().set("Maxim Pulemet");
        viewModel.addStudent();
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();
        viewModel.getNewAssessment().set(Assessment.VeryBad);
        viewModel.addAssessment();

        viewModel.getNewStudent().set("Eugene");
        viewModel.addStudent();
        viewModel.getNewAssessment().set(Assessment.Perfect);
        viewModel.addAssessment();

        assertEquals("4.0", viewModel.averageAssessmentOfStudentsProperty().get());
    }

    @Test
    public void canGetAverageAssessmentForCurrentSubject() {
        viewModel.getNewStudent().set("Mabean");
        viewModel.addStudent();
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();

        viewModel.getNewAssessment().set(Assessment.VeryBad);
        viewModel.addAssessment();
        assertEquals("1.0", viewModel.averageAssessmentForCurrentSubjectProperty().get());
    }

    @Test
    public void canGetAverageAssessmentForCurrentSubjectForSeveralMarks() {
        viewModel.getNewStudent().set("Max");
        viewModel.addStudent();
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();

        viewModel.getNewAssessment().set(Assessment.VeryBad);
        viewModel.addAssessment();
        viewModel.getNewAssessment().set(Assessment.Bad);
        viewModel.addAssessment();
        assertEquals("1.5", viewModel.averageAssessmentForCurrentSubjectProperty().get());
    }

    @Test
    public void canEditStudent() {
        viewModel.getNewStudent().set("Maxim");
        viewModel.addStudent();
        viewModel.getNewStudent().set("Maxim Galkin");
        viewModel.editStudent();

        assertEquals("Maxim Galkin", viewModel.getCurrentStudent().get());
        assertEquals(1, viewModel.getStudents().size());
    }

    @Test
    public void canEditSubject() {
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();
        viewModel.getNewSubject().set("Programming");
        viewModel.editSubject();

        assertEquals("Programming", viewModel.getCurrentSubject().get());
        assertEquals(1, viewModel.getSubjects().size());
    }

    @Test
    public void canEditAssessment() {
        viewModel.getNewStudent().set("Maxim");
        viewModel.addStudent();
        viewModel.getNewSubject().set("Software engineering");
        viewModel.addSubject();

        viewModel.getNewAssessment().set(Assessment.VeryBad);
        viewModel.addAssessment();
        viewModel.updateIndexOfAssessment(0);

        viewModel.getNewAssessment().set(Assessment.Bad);
        viewModel.editAssessment();

        assertEquals(Assessment.Bad, viewModel.getCurrentAssessment().get());
        assertEquals(1, viewModel.getAssessmentsOfStudent().size());
    }

}
