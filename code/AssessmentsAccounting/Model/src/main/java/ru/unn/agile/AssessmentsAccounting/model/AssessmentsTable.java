package ru.unn.agile.AssessmentsAccounting.model;

import java.security.InvalidParameterException;
import java.util.UUID;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class AssessmentsTable {

    public AssessmentsTable() {
        this.subjects = new HashMap<String, UUID>();
        this.students = new HashSet<Student>();
    }

    public List<String> getSubjects() {
        return new ArrayList<String>(subjects.keySet());
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void addSubject(final String subject) {
        if (isStringInvalid(subject) || subjects.containsKey(subject)) {
            throw new InvalidParameterException("Invalid new subject - " + subject);
        }
        subjects.put(subject, UUID.randomUUID());
    }

    public void removeSubject(final String subject) {
        UUID uuid = getSubjectUUID(subject);
        subjects.remove(subject);
        for (Student student : students) {
            if (student.isRegisteredForSubject(uuid)) {
                student.removeSubject(uuid);
            }
        }
    }

    public void renameSubject(final String oldName, final String newName) {
        if (!subjects.containsKey(oldName) || isStringInvalid(newName)
                || subjects.containsKey(newName)) {
            throw new InvalidParameterException("Invalid renameSubject arguments oldName "
                    + oldName + " newName - " + newName);
        }
        UUID uuid = subjects.get(oldName);
        subjects.remove(oldName);
        subjects.put(newName, uuid);
    }

    public void addStudent(final String name) {
        if (isStringInvalid(name)) {
            throw new InvalidParameterException("Invalid addStudent arguments name " + name);
        }

        try {
            findStudent(name);
        } catch (InvalidParameterException t) {
            students.add(new Student(name));
            return;
        }

        throw new InvalidParameterException("Invalid addStudent arguments name " + name);
    }

    public void renameStudent(final String oldName, final String newName) {
        if (students.contains(new Student(newName))) {
            throw new InvalidParameterException("Invalid renameStudent arguments oldName "
                    + oldName + " newName - " + newName);
        }
        try {
            findStudent(newName);
        } catch (InvalidParameterException t) {
            Student student = findStudent(oldName);
            student.setName(newName);
            return;
        }

        throw new InvalidParameterException("Invalid renameStudent arguments oldName "
                + oldName + " newName - " + newName);
    }

    public void removeStudent(final String name) {
        Student student = findStudent(name);
        students.remove(student);
    }

    public void addAssessment(final Assessment assessment,
                              final String studentName,
                              final String subject) {
        UUID uuid = getSubjectUUID(subject);
        Student student = findStudent(studentName);
        if (!student.isRegisteredForSubject(uuid)) {
            student.addSubject(uuid);
        }
        student.addAssessment(assessment, uuid);
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
        UUID uuid = getSubjectUUID(subject);
        Student student = findStudent(studentName);
        student.removeAssessmentAt(assessmentIndex, uuid);
    }

    public void changeAssessment(final int asessmentIndex,
                                final Assessment value,
                                final String studentName,
                                final String subject) {
        UUID uuid = getSubjectUUID(subject);
        Student student = findStudent(studentName);
        student.changeAssessmentAt(asessmentIndex, value, uuid);
    }

    public List<Assessment> getAssessments(final String subject) {
        UUID uuid = getSubjectUUID(subject);
        List<Assessment> assessments = new LinkedList<Assessment>();
        for (Student student : students) {
            assessments.addAll(student.getAssessments(uuid));
        }
        return assessments;
    }

    public List<Assessment> getAssessmentsForStudent(final String subject,
                                                          final String studentName) {
        try {
            UUID uuid = getSubjectUUID(subject);
            Student student = findStudent(studentName);
            return student.getAssessments(uuid);
        } catch (InvalidParameterException e) {
            return new LinkedList<Assessment>();
        }
    }

    public double getAverageAssessmentForSubject(final String subject) {
        int sumOfAssessments = 0;
        int assessmentsCount = 0;
        UUID uuid = getSubjectUUID(subject);
        for (Student student : students) {
            try {
                List<Assessment> assessments = student.getAssessments(uuid);
                if (assessments != null) {
                    for (Assessment assessment : assessments) {
                        sumOfAssessments += assessment.getMark();
                    }
                    assessmentsCount += assessments.size();
                }
            } catch (InvalidParameterException t) {
                continue;
            }
        }
        if (assessmentsCount == 0) {
            throw new InvalidParameterException("Subject - " + subject
                    + "doesn't contain any assessments");
        }
        return sumOfAssessments / (double) assessmentsCount;
    }

    public double getAverageAssessmentForStudent(final String studentName) {
        Student student = findStudent(studentName);
        return getAverage(student.getAssessments());
    }

    public double getAverageAssessment(final String studentName, final String subject) {
        UUID uuid = getSubjectUUID(subject);
        Student student = findStudent(studentName);
        return getAverage(student.getAssessments(uuid));
    }

    public UUID getSubjectUUID(final String subject) {
        if (!subjects.containsKey(subject)) {
            throw new InvalidParameterException("Table doesn't contain subject - "
                    + subject);
        }
        return subjects.get(subject);
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
        int sumOfAssessments = 0;
        for (Assessment assessment : assessments) {
            sumOfAssessments += assessment.getMark();
        }
        return sumOfAssessments / (double) assessmentsCount;
    }

    private boolean isStringInvalid(final String value) {
        return value == null || value.isEmpty();
    }

    private Map<String, UUID> subjects;
    private Set<Student> students;
}
