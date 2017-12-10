package ru.unn.agile.assessmentsaccounting.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class AssessmentsTable {

    public AssessmentsTable() {
        this.subjects = new ArrayList<String>();
        this.students = new HashSet<Student>();
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addSubject(final String subject) {
        if (isStringInvalid(subject) || subjects.contains(subject)) {
            throw new InvalidParameterException("Invalid new subject - " + subject);
        }
        subjects.add(subject);
    }

    public void removeSubject(final String subject) {
        checkSubject(subject);
        subjects.remove(subject);
        for (Student student : students) {
            if (student.isRegisteredForSubject(subject)) {
                student.removeSubject(subject);
            }
        }
    }

    public void renameSubject(final String oldName, final String newName) {
        if (!subjects.contains(oldName) || isStringInvalid(newName)
                || subjects.contains(newName)) {
            throw new InvalidParameterException("Invalid renameSubject arguments oldName "
                    + oldName + " newName - " + newName);
        }
        subjects.set(subjects.indexOf(oldName), newName);
        for (Student student : students) {
            student.renameSubject(oldName, newName);
        }
    }

    public void addStudent(final String name) {
        if (isStringInvalid(name)) {
            throw new InvalidParameterException();
        }
        students.add(new Student(name));
    }

    public void renameStudent(final String oldName, final String newName) {
        if (isStringInvalid(newName) || students.contains(newName)) {
            throw new InvalidParameterException("Invalid renameStudent arguments oldName "
                    + oldName + " newName - " + newName);
        }

        Student student = findStudent(oldName);
        student.setName(newName);
    }

    public void removeStudent(final String name) {
        Student student = findStudent(name);
        students.remove(student);
    }

    public void addAssessment(final Assessment assessment,
                              final String studentName,
                              final String subject) {
        checkSubject(subject);
        Student student = findStudent(studentName);
        if (!student.isRegisteredForSubject(subject)) {
            student.addSubject(subject);
        }
        student.addAssessment(assessment, subject);
    }

    public void addAssessment(final Assessment assessment,
                              final List<String> students,
                              final String subject) {
        for (String name : students) {
            addAssessment(assessment, name, subject);
        }
    }

    public void removeAssessment(final int assessmentIndex,
                                 final String studentName,
                                 final String subject) {
        checkSubject(subject);
        Student student = findStudent(studentName);
        student.removeAssessmentAt(assessmentIndex, subject);
    }

    public void changeAsessment(final int asessmentIndex,
                                final Assessment value,
                                final String studentName,
                                final String subject) {
        checkSubject(subject);
        Student student = findStudent(studentName);
        student.changeAssessmentAt(asessmentIndex, value, subject);
    }

    public List<Assessment> getAssessments(final String subject) {
        checkSubject(subject);
        List<Assessment> assessments = new LinkedList<Assessment>();
        for (Student student : students) {
            assessments.addAll(student.getAssessments(subject));
        }
        return assessments;
    }

    public List<Assessment> getAssessmentsForStudent(final String subject,
                                                          final String studentName) {
        checkSubject(subject);
        Student student = findStudent(studentName);
        return student.getAssessments(subject);
    }

    public double getAverageAssessmentForSubject(final String subject) {
        int summOfAssessments = 0;
        int assessmentsCount = 0;
        for (Student student : students) {
            List<Assessment> assessments = student.getAssessments(subject);
            if (assessments != null) {
                for (Assessment assessment : assessments) {
                    summOfAssessments += assessment.getMark();
                }
                assessmentsCount += assessments.size();
            }
        }
        if (assessmentsCount == 0) {
            throw new InvalidParameterException("Subject - " + subject
                    + "doesn't contain any assessments");
        }
        return summOfAssessments / (double) assessmentsCount;
    }

    public double getAverageAssessmentForStudent(final String studentName) {
        Student student = findStudent(studentName);
        return getAverage(student.getAssessments());
    }

    public double getAverageAssessment(final String studentName, final String subject) {
        checkSubject(subject);
        Student student = findStudent(studentName);
        return getAverage(student.getAssessments(subject));
    }

    private Student findStudent(final String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        throw new InvalidParameterException("Table doesn't contain student - " + name);
    }

    private double getAverage(final List<Assessment> assessments) {
        if (assessments == null || assessments.isEmpty()) {
            throw new InvalidParameterException("Assessments are null or empty");
        }
        int assessmentsCount = assessments.size();
        int summOfAssessments = 0;
        for (Assessment assessment : assessments) {
            summOfAssessments += assessment.getMark();
        }
        return summOfAssessments / (double) assessmentsCount;
    }

    private void checkSubject(final String subject) {
        if (!subjects.contains(subject)) {
            throw new InvalidParameterException("Table doesn't contain subject - "
                    + subject);
        }
    }

    private boolean isStringInvalid(final String value) {
        return value == null || value.isEmpty();
    }

    private List<String> subjects;
    private Set<Student> students;
}
