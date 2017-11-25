package ru.unn.agile.assessmentsaccounting.model;

import org.junit.Test;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class StudentTests {

    @Test
    public void canCreateStudent() {
        Student student = new Student("Max Bespalov");
        assertNotNull(student);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotCreateUnnamedStudent() {
        new Student("");
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotSetEmptyName() {
        Student student = new Student("Max Bespalov");
        student.setName("");
    }

    @Test
    public void canAddSubject() {
        Student student = new Student("Max Bespalov");
        String subject = "Algebra";
        student.addSubject(subject);
        assertNotNull(student.getAssessments(subject));
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotAddUnnamedSubject() {
        Student student = new Student("Max Bespalov");
        student.addSubject("");
    }

    @Test
    public void canRemoveAddedSubject() {
        Student student = new Student("Max Bespalov");
        String subject = "Game development";
        student.addSubject(subject);
        student.removeSubject(subject);
        assertNull(student.getAssessments(subject));
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveNotExistingSubject() {
        Student student = new Student("Max Bespalov");
        student.removeSubject("Laying on sofa");
    }

    @Test
    public void canRenameSubject() {
        Student student = new Student("Max Bespalov");
        String subject = "Philosophy";
        student.addSubject(subject);
        String newName = "Some useless subject";
        student.renameSubject(subject, newName);
        assertNotNull(student.getAssessments(newName));
    }

    @Test
    public void canRemoveRenamedSubject() {
        Student student = new Student("Max Bespalov");
        String subject = "High maths";
        student.addSubject(subject);
        String newName = "Some subject that was very important";
        student.renameSubject(subject, newName);
        student.removeSubject(newName);
        assertNull(student.getAssessments(newName));
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveSubjectByTheOldName() {
        Student student = new Student("Max Bespalov");
        String oldName = "Some meaningless abbreviature";
        student.addSubject(oldName);
        String newName = "Some meaningful abbreviature that was described by teacher";
        student.renameSubject(oldName, newName);
        student.removeSubject(oldName);
    }

    @Test
    public void canAddAssessmentForTheSubject() {
        Student student = new Student("Max Bespalov");
        String subject = "Agile course";
        Assessment assessment = Assessment.VeryBad;
        student.addAssessment(assessment, subject);
        assertEquals(assessment, student.getAssessments(subject).get(0));
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotSetEmptyNameOfTheSubject() {
        Student student = new Student("Max Bespalov");
        String subject = "Philosophy";
        student.addSubject(subject);
        student.renameSubject(subject, "");
    }

    @Test
    public void canRemoveAddedAssessment() {
        Student student = new Student("Max Bespalov");
        String subject = "C++ development";
        student.addAssessment(Assessment.Satisfactorily, subject);
        student.removeAssessmentAt(0, subject);
        assertEquals(student.getAssessments(subject).size(), 0);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveAlreadyRemovedAssessment() {
        Student student = new Student("Max Bespalov");
        String subject = "Python development";
        student.addAssessment(Assessment.Bad, subject);
        student.removeAssessmentAt(0, subject);
        student.removeAssessmentAt(0, subject);
    }

    @Test
    public void canAddTwoAssessments() {
        Student student = new Student("Max Bespalov");
        String subject = "useless speaking";
        student.addAssessment(Assessment.Good, subject);
        student.addAssessment(Assessment.Great, subject);
        assertEquals(student.getAssessments(subject).get(1), Assessment.Great);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveAssessmentFromIndexHigherOrEqualsAssessmentsSize() {
        Student student = new Student("Max Bespalov");
        String subject = "Russian language";
        student.addSubject(subject);
        student.removeAssessmentAt(0, subject);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveAssessmentOfRemovedSubject() {
        Student student = new Student("Max Bespalov");
        String subject = "Visiting pairs";
        student.addAssessment(Assessment.Bad, subject);
        student.removeSubject(subject);
        student.removeAssessmentAt(0, subject);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveAssessmentOfRenamedSubject() {
        Student student = new Student("Max Bespalov");
        String subject = "Unnecessary subject";
        student.addAssessment(Assessment.VeryBad, subject);
        student.renameSubject(subject, "Very important subject in the last week before session");
        student.removeAssessmentAt(0, subject);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveAssessmentAtNegativeIndex() {
        Student student = new Student("Max Bespalov");
        String subject = "Pizza making";
        student.addSubject(subject);
        student.removeAssessmentAt(-1, subject);
    }

    @Test
    public void canGetEmptyAssessmentsOfTheStudent() {
        Student student = new Student("Max Bespalov");
        assertEquals(student.getAssessments().size(), 0);
    }

    @Test
    public void canGetAssessments() {
        Student student = new Student("Max Bespalov");
        student.addAssessment(Assessment.Good, "Combining assessments");
        student.addAssessment(Assessment.Bad, "Program design");
        student.addAssessment(Assessment.Great,
                      "Delivering lab works in the last day before deadline");

        ArrayList<Assessment> allAssessments = student.getAssessments();
        assertEquals(allAssessments.size(), 3);
        assertTrue(allAssessments.contains(Assessment.Good));
        assertTrue(allAssessments.contains(Assessment.Bad));
        assertTrue(allAssessments.contains(Assessment.Great));
    }
}
