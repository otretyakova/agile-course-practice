package ru.unn.agile.assessmentsaccounting.model;

import org.junit.Test;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AssessmentTableTests {

    @Test
    public void canRemoveAddedSubject() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "TDD";
        table.addSubject(subject);
        table.removeSubject(subject);
        assertEquals(table.getSubjects().size(), 0);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveNotExistingSubject() {
        AssessmentsTable table = new AssessmentsTable();
        table.removeSubject("Some subject, that every student hates");
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveSubjectTwice() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "The worst subject ever";
        table.addSubject(subject);
        table.removeSubject(subject);
        table.removeSubject(subject);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotAddExistingSubject() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "The best subject ever";
        table.addSubject(subject);
        table.addSubject(subject);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotGetAssessmentsOfRemovedSubject() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Math Analysis";
        table.addSubject(subject);
        table.removeSubject(subject);
        table.getAssessments(subject);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotGetAssessmentsOfNotExistingSubject() {
        AssessmentsTable table = new AssessmentsTable();
        table.getAssessments("History");
    }

    @Test
    public void canRenameSubject() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        table.addSubject(subject);
        String newName = "Very important subject";
        table.renameSubject(subject, newName);
        assertTrue(table.getSubjects().get(0).equals(newName));
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRenameNotExistingSubject() {
        AssessmentsTable table = new AssessmentsTable();
        table.renameSubject("Game development", "Best subject");
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRenameSubjectToEmptyName() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Very short course";
        table.addSubject(subject);
        table.renameSubject(subject, "");
    }

    @Test
    public void afterRenamingSubjectStudentSubjectsGetRenamed() {
        AssessmentsTable table = new AssessmentsTable();
        String student = "Max Bespalov";
        String subject = "Agile course";
        Assessment assessment = Assessment.VeryBad;

        table.addSubject(subject);
        table.addStudent(student);
        table.addAssessment(assessment, student, subject);

        String newName = "Very important subject";
        table.renameSubject(subject, newName);

        assertEquals(table.getAssessmentsForStudent(newName, student).get(0), assessment);
    }

    @Test
    public void canAddStudent() {
        AssessmentsTable table = new AssessmentsTable();
        String name = "Max Bespalov";
        table.addStudent(name);

        List<Student> students = table.getStudents();
        assertEquals(students.size(), 1);
        assertTrue(students.get(0).getName().equals(name));
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotAddExistingStudent() {
        AssessmentsTable table = new AssessmentsTable();
        String name = "Max Bespalov";
        table.addStudent(name);
        table.addStudent(name);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotAddUnnamedStudent() {
        AssessmentsTable table = new AssessmentsTable();
        table.addStudent("");
    }

    @Test
    public void canRemoveAddedStudent() {
        AssessmentsTable table = new AssessmentsTable();
        String name = "Max Bespalov";
        table.addStudent(name);
        table.removeStudent(name);
        assertEquals(table.getStudents().size(), 0);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveNotExistingStudent() {
        AssessmentsTable table = new AssessmentsTable();
        table.removeStudent("Albert Einstein");
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveStudentTwice() {
        AssessmentsTable table = new AssessmentsTable();
        String name = "Max Bespalov";
        table.addStudent(name);
        table.removeStudent(name);
        table.removeStudent(name);
    }

    @Test
    public void canRenameStudent() {
        AssessmentsTable table = new AssessmentsTable();
        String name = "Max Bespalov";
        table.addStudent(name);
        String newName = "Very lazy student";
        table.renameStudent(name, newName);
        assertTrue(table.getStudents().get(0).getName().equals(newName));
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRenameNotExistingStudent() {
        AssessmentsTable table = new AssessmentsTable();
        table.renameStudent("Albert Einstein", "This student was Albert Einstein");
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRenameStudentToEmptyName() {
        AssessmentsTable table = new AssessmentsTable();
        String name = "Max Bespalov";
        table.addStudent(name);
        table.renameStudent(name, "");
    }

    @Test
    public void canAddAssessmentToStudent() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        String studentName = "Max Bespalov";
        Assessment assessment = Assessment.VeryBad;

        table.addSubject(subject);
        table.addStudent(studentName);
        table.addAssessment(assessment, studentName, subject);

        assertEquals(table.getStudents().get(0).getAssessments(subject).get(0), assessment);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotAddAssessmentForNotExistingStudent() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        table.addAssessment(Assessment.VeryBad, "Mystical student", subject);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotAddAssessmentForNotExistingSubject() {
        AssessmentsTable table = new AssessmentsTable();
        String studentName = "Max Bespalov";

        table.addStudent(studentName);
        table.addAssessment(Assessment.Great, studentName, "Game development");
    }

    @Test
    public void canAddAssessmentsToTwoStudents() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        String firstStudentName = "Max Bespalov";
        String secondStudentName = "Ivan Ivanov";
        Assessment firstAssessment = Assessment.Bad;
        Assessment secondAssessment = Assessment.VeryGood;
        Assessment thirdAssessment = Assessment.Satisfactorily;

        table.addSubject(subject);
        table.addStudent(firstStudentName);
        table.addStudent(secondStudentName);
        table.addAssessment(firstAssessment, firstStudentName, subject);
        table.addAssessment(secondAssessment, secondStudentName, subject);
        table.addAssessment(thirdAssessment, secondStudentName, subject);

        assertEquals(table.getStudents().get(0).getAssessments(subject).get(0), firstAssessment);
        assertEquals(table.getStudents().get(1).getAssessments(subject).get(0), secondAssessment);
        assertEquals(table.getStudents().get(1).getAssessments(subject).get(1), thirdAssessment);
    }

    @Test
    public void canAddAssessmentsOnTwoSubjects() {
        AssessmentsTable table = new AssessmentsTable();
        String firstSubject = "Writing usefull tests";
        String secondSubject = "Writing bad code";
        String studentName = "Max Bespalov";
        Assessment firstAssessment = Assessment.VeryBad;
        Assessment secondAssessment = Assessment.Great;

        table.addSubject(firstSubject);
        table.addSubject(secondSubject);
        table.addStudent(studentName);
        table.addAssessment(firstAssessment, studentName, firstSubject);
        table.addAssessment(secondAssessment, studentName, secondSubject);

        assertEquals(table.getStudents().get(0).getAssessments(firstSubject).get(0),
                firstAssessment);
        assertEquals(table.getStudents().get(0).getAssessments(secondSubject).get(0),
                secondAssessment);
    }

    @Test
    public void canRemoveAssessment() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        String studentName = "Max Bespalov";
        Assessment assessment = Assessment.Great;

        table.addSubject(subject);
        table.addStudent(studentName);
        table.addAssessment(assessment, studentName, subject);
        table.removeAssessment(0, studentName, subject);

        assertEquals(table.getStudents().get(0).getAssessments(subject).size(), 0);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveAssessmentTwice() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Java development";
        String studentName = "Max Bespalov";
        Assessment assessment = Assessment.VeryGood;

        table.addSubject(subject);
        table.addStudent(studentName);
        table.addAssessment(assessment, studentName, subject);
        table.removeAssessment(0, studentName, subject);
        table.removeAssessment(0, studentName, subject);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotRemoveNotExistingAssessment() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Haskell development";
        String studentName = "Max Bespalov";
        Assessment assessment = Assessment.Great;

        table.addSubject(subject);
        table.addStudent(studentName);
        table.removeAssessment(0, studentName, subject);
    }

    @Test
    public void canChangeAssessment() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        String studentName = "Max Bespalov";
        Assessment assessment = Assessment.Great;
        Assessment newAssessment = Assessment.VeryBad;

        table.addSubject(subject);
        table.addStudent(studentName);
        table.addAssessment(assessment, studentName, subject);
        table.changeAsessment(0, newAssessment, studentName, subject);

        assertEquals(table.getStudents().get(0).getAssessments(subject).get(0), newAssessment);
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotChangeNotExistingAssessment() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Code reviewong";
        String studentName = "Max Bespalov";

        table.addSubject(subject);
        table.addStudent(studentName);
        table.changeAsessment(0, Assessment.VeryBad, studentName, subject);
    }

    @Test
    public void canChangeAssessmentTwice() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Java understanding";
        String studentName = "Max Bespalov";
        Assessment assessment = Assessment.VeryBad;
        Assessment secondAssessment = Assessment.Great;
        Assessment thirdAssessment = Assessment.Bad;


        table.addSubject(subject);
        table.addStudent(studentName);
        table.addAssessment(assessment, studentName, subject);
        table.changeAsessment(0, secondAssessment, studentName, subject);
        table.changeAsessment(0, thirdAssessment, studentName, subject);

        assertEquals(table.getStudents().get(0).getAssessments(subject).get(0), thirdAssessment);
    }

    @Test
    public void canAddAssessmentToGroupOfStudents() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        String firstStudentName = "Max Bespalov";
        String secondStudentName = "Never seen student";
        List<String> students = new ArrayList<String>();
        students.add(firstStudentName);
        students.add(secondStudentName);
        Assessment assessment = Assessment.VeryBad;

        table.addSubject(subject);
        table.addStudent(firstStudentName);
        table.addStudent(secondStudentName);
        table.addAssessment(assessment, students, subject);

        assertEquals(table.getStudents().get(0).getAssessments(subject).get(0), assessment);
        assertEquals(table.getStudents().get(1).getAssessments(subject).get(0), assessment);
    }

    @Test
    public void canGetEmptySubjectAssessments() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        table.addSubject(subject);
        assertNotNull(table.getAssessments(subject));
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotGetAssessmentsForNotExistingSubject() {
        AssessmentsTable table = new AssessmentsTable();
        table.getAssessments("Game development");
    }

    @Test
    public void canGetAssessmentForSubject() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        String firstStudentName = "Max Bespalov";
        String secondStudentName = "Ivan Ivanov";
        Assessment firstAssessment = Assessment.VeryBad;
        Assessment secondAssessment = Assessment.Great;

        table.addSubject(subject);
        table.addStudent(firstStudentName);
        table.addStudent(secondStudentName);
        table.addAssessment(firstAssessment, firstStudentName, subject);
        table.addAssessment(secondAssessment, secondStudentName, subject);

        List<Assessment> assessments = table.getAssessments(subject);
        assertEquals(assessments.size(), 2);
        assertTrue(assessments.contains(firstAssessment));
        assertTrue(assessments.contains(secondAssessment));
    }

    @Test
    public void canGetEmptyAssessmentsOfTheStudent() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile course";
        String student = "Max Bespalov";
        table.addSubject(subject);
        table.addStudent(student);
        assertNull(table.getAssessmentsForStudent(subject, student));
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotGetAssessmentsOfNotExistingStudent() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Theory of relativity";
        table.addSubject(subject);
        table.getAssessmentsForStudent(subject, "Albert Einstein");
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotGetStudentAssessmentsForNotExistingSubject() {
        AssessmentsTable table = new AssessmentsTable();
        String student = "Max Bespalov";
        table.addStudent(student);
        table.getAssessmentsForStudent("Game development", student);
    }

    @Test
    public void canGetStudentAssessments() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Writing hundreds of test code rows";
        String student = "Max Bespalov";
        Assessment firstAssessment = Assessment.VeryGood;
        Assessment secondAssessment = Assessment.Perfect;

        table.addStudent(student);
        table.addSubject(subject);
        table.addAssessment(firstAssessment, student, subject);
        table.addAssessment(secondAssessment, student, subject);

        List<Assessment> assessments = table.getAssessmentsForStudent(subject, student);
        assertEquals(assessments.size(), 2);
        assertTrue(assessments.contains(firstAssessment));
        assertTrue(assessments.contains(secondAssessment));
    }

    @Test
    public void canGetAverageAssessmentForStudentOnSubject() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Agile Course";
        String student = "Max Bespalov";
        Assessment firstAssessment = Assessment.VeryBad;
        Assessment secondAssessment = Assessment.Bad;
        double average = 1.5;

        table.addStudent(student);
        table.addSubject(subject);
        table.addAssessment(firstAssessment, student, subject);
        table.addAssessment(secondAssessment, student, subject);

        assertTrue(table.getAverageAssessment(student, subject) == average);
    }

    @Test
    public void canGetAverageAssessmentForStudentOnSubjectWithSingleAssessment() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Reaching the end of tests";
        String student = "Max Bespalov";
        Assessment assessment = Assessment.VeryGood;

        table.addStudent(student);
        table.addSubject(subject);
        table.addAssessment(assessment, student, subject);

        assertTrue(table.getAverageAssessment(student, subject) == assessment.getMark());
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotGetAverageAssessmentForStudentOnSubjectsWithoutAssessments() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Giving up";
        String student = "Max Bespalov";

        table.addStudent(student);
        table.addSubject(subject);
        table.getAverageAssessment(student, subject);
    }

    @Test
    public void canGetAverageAssessmentForStudent() {
        AssessmentsTable table = new AssessmentsTable();
        String firstSubject = "Letting you down";
        String secondSubject = "Running around";
        String student = "Max Bespalov";
        Assessment firstAssessment = Assessment.VeryGood;
        Assessment secondAssessment = Assessment.Perfect;
        double average = 6;

        table.addStudent(student);
        table.addSubject(firstSubject);
        table.addSubject(secondSubject);
        table.addAssessment(firstAssessment, student, firstSubject);
        table.addAssessment(secondAssessment, student, secondSubject);

        assertTrue(table.getAverageAssessmentForStudent(student) == average);
    }

    @Test
    public void canGetAverageAssessmentForStudentWithSingleAssessment() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Deserting you";
        String student = "Max Bespalov";
        Assessment assessment = Assessment.Good;

        table.addStudent(student);
        table.addSubject(subject);
        table.addAssessment(assessment, student, subject);

        assertTrue(table.getAverageAssessmentForStudent(student) == assessment.getMark());
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotGetAverageAssessmentForStudentWithoutAssessments() {
        AssessmentsTable table = new AssessmentsTable();
        String student = "Max Bespalov";

        table.addStudent(student);
        table.getAverageAssessmentForStudent(student);
    }

    @Test
    public void canGetAverageAssessmentForSubject() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Making you cry";
        String firstStudent = "Max Bespalov";
        String secondStudent = "Ivan Ivanov";
        Assessment firstAssessment = Assessment.Perfect;
        Assessment secondAssessment = Assessment.VeryBad;
        double average = 4;

        table.addStudent(firstStudent);
        table.addStudent(secondStudent);
        table.addSubject(subject);
        table.addAssessment(firstAssessment, firstStudent, subject);
        table.addAssessment(secondAssessment, secondStudent, subject);

        assertTrue(table.getAverageAssessmentForSubject(subject) == average);
    }

    @Test
    public void canGetAverageAssessmentForSubjectWithSingleAssessment() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Saying goodbye";
        String student = "Max Bespalov";
        Assessment assessment = Assessment.Perfect;

        table.addStudent(student);
        table.addSubject(subject);
        table.addAssessment(assessment, student, subject);

        assertTrue(table.getAverageAssessmentForSubject(subject) == assessment.getMark());
    }

    @Test(expected = InvalidParameterException.class)
    public void canNotGetAverageAssessmentForSubjectWithoutAssessments() {
        AssessmentsTable table = new AssessmentsTable();
        String subject = "Going to tell a lie and hurting you";

        table.addSubject(subject);
        table.getAverageAssessmentForSubject(subject);
    }
}
