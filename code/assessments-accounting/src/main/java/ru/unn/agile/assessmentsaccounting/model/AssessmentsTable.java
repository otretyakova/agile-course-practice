package ru.unn.agile.assessmentsaccounting.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class AssessmentsTable {
    private List<String> subjects;
    private List<Student> students;

    public AssessmentsTable() {
        this.subjects = new ArrayList<String>();
        this.students = new ArrayList<Student>();
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addSubject(final String subject) {
        if (subject.isEmpty() || subjects.contains(subject)) {
            throw new InvalidParameterException();
        }
        subjects.add(subject);
    }

    public void removeSubject(final String subject) {
        if (!subjects.contains(subject)) {
            throw new InvalidParameterException();
        }
        subjects.remove(subject);
        for (Student student : students) {
            if (student.hasSubject(subject)) {
                student.removeSubject(subject);
            }
        }
    }

    public void renameSubject(final String oldName, final String newName) {
        if (!subjects.contains(oldName) || newName.isEmpty()) {
            throw new InvalidParameterException();
        }
        if (newName.equals(oldName)) {
            return;
        }
        subjects.set(subjects.indexOf(oldName), newName);
        for (Student student : students) {
            student.renameSubject(oldName, newName);
        }
    }

    public void addStudent(final String name) {
        if (name.isEmpty()) {
            throw new InvalidParameterException();
        }
        for (Student student : students) {
            if (student.getName().equals(name)) {
                throw new InvalidParameterException();
            }
        }
        students.add(new Student(name));
    }

    public void renameStudent(final String oldName, final String newName) {
        if (newName.isEmpty()) {
            throw new InvalidParameterException();
        }

        Student student = findStudent(oldName);

        if (newName.equals(oldName)) {
            return;
        }
        students.get(students.indexOf(student)).setName(newName);
    }

    public void removeStudent(final String name) {
        Student student = findStudent(name);
        students.remove(student);
    }

    public void addAssessment(final Assessment assessment,
                              final String studentName,
                              final String subject) {
        if (!subjects.contains(subject)) {
            throw new InvalidParameterException();
        }
        Student student = findStudent(studentName);
        if (!student.hasSubject(subject)) {
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
        if (!subjects.contains(subject)) {
            throw new InvalidParameterException();
        }
        Student student = findStudent(studentName);
        student.removeAssessmentAt(assessmentIndex, subject);
    }

    public void changeAsessment(final int asessmentIndex,
                                final Assessment value,
                                final String studentName,
                                final String subject) {
        if (!subjects.contains(subject)) {
            throw new InvalidParameterException();
        }
        Student student = findStudent(studentName);
        student.changeAssessmentAt(asessmentIndex, value, subject);
    }

    public List<Assessment> getAssessments(final String subject) {
        if (!subjects.contains(subject)) {
            throw new InvalidParameterException();
        }
        List<Assessment> assessments = new ArrayList<Assessment>();
        for (Student student : students) {
            assessments.addAll(student.getAssessments(subject));
        }
        return assessments;
    }

    public List<Assessment> getAssessmentsForStudent(final String subject,
                                                          final String studentName) {
        if (!subjects.contains(subject)) {
            throw new InvalidParameterException();
        }
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
            throw new InvalidParameterException();
        }
        return summOfAssessments / (double) assessmentsCount;
    }

    public double getAverageAssessmentForStudent(final String studentName) {
        Student student = findStudent(studentName);
        return getAverage(student.getAssessments());
    }

    public double getAverageAssessment(final String studentName, final String subject) {
        if (!subjects.contains(subject)) {
            throw new InvalidParameterException();
        }
        Student student = findStudent(studentName);
        return getAverage(student.getAssessments(subject));
    }

    private Student findStudent(final String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        throw new InvalidParameterException();
    }
    private double getAverage(final List<Assessment> assessments) {
        if (assessments == null || assessments.isEmpty()) {
            throw new InvalidParameterException();
        }
        int assessmentsCount = assessments.size();
        int summOfAssessments = 0;
        for (Assessment assessment : assessments) {
            summOfAssessments += assessment.getMark();
        }
        return summOfAssessments / (double) assessmentsCount;
    }
}
