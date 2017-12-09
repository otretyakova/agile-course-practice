package ru.unn.agile.assessmentsaccounting.model;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Student {

    protected Student(final String name) {
        if (isStringInvalid(name)) {
            throw new InvalidParameterException("Name is null or empty");
        }
        this.name = name;
        this.assessments = new HashMap<String, List<Assessment>>();
    }

    @Override
    public boolean equals(final Object o) {
        return (o instanceof Student) && (((Student) o).getName()).equals(this.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        if (isStringInvalid(name)) {
            throw new InvalidParameterException("Name is null or empty");
        }
        if (name == this.name) {
            return;
        }
        this.name = name;
    }

    public void addSubject(final String subject) {
        if (isStringInvalid(subject) || isRegisteredForSubject(subject)) {
            throw new InvalidParameterException("Subject - " + subject
                    + " doesn't exist for this student");
        }
        assessments.put(subject, new LinkedList<Assessment>());
    }

    public void renameSubject(final String oldName, final String newName) {
        if (!isRegisteredForSubject(oldName) || isStringInvalid(newName)
                || isRegisteredForSubject(newName)) {
            throw new InvalidParameterException("Invalid renameSubject arguments oldName "
                    + oldName + " newName - " + newName);
        }
        if (newName.equals(oldName)) {
            return;
        }
        assessments.put(newName, assessments.get(oldName));
        assessments.remove(oldName);
    }

    public void removeSubject(final String subject) {
        if (!isRegisteredForSubject(subject)) {
            throw new InvalidParameterException("Student isn't registered for "
                    + subject);
        }
        assessments.remove(subject);
    }

    public boolean isRegisteredForSubject(final String subject) {
        return assessments.containsKey(subject);
    }

    public void addAssessment(final Assessment assessment, final String subject) {
        if (!isRegisteredForSubject(subject)) {
            throw new InvalidParameterException("Student isn't registered for "
                    + subject);
        }
        List<Assessment> subjectAssessments = assessments.get(subject);
        subjectAssessments.add(assessment);
        assessments.put(subject, subjectAssessments);
    }

    public void removeAssessmentAt(final int index, final String subject) {
        checkAssessmentsIndex(index, subject);
        List<Assessment> subjectAssessments = assessments.get(subject);
        subjectAssessments.remove(index);
        assessments.put(subject, subjectAssessments);
    }

    public void changeAssessmentAt(final int index,
                                   final Assessment assessment,
                                   final String subject) {

        checkAssessmentsIndex(index, subject);
        List<Assessment> subjectAssessments = assessments.get(subject);
        subjectAssessments.set(index, assessment);
        assessments.put(subject, subjectAssessments);
    }

    public List<Assessment> getAssessments() {
        List<Assessment> allAssessments = new LinkedList<Assessment>();
        for (Map.Entry<String, List<Assessment>> entry : assessments.entrySet()) {
            allAssessments.addAll(entry.getValue());
        }
        return allAssessments;
    }

    public List<Assessment> getAssessments(final String subject) {
        if (!isRegisteredForSubject(subject)) {
            throw new InvalidParameterException("Student isn't registered for "
                    + subject);
        }
        return assessments.get(subject);
    }

    private void checkAssessmentsIndex(final int index, final String subject) {
        if (!assessments.containsKey(subject)) {
            throw new InvalidParameterException("Student isn't registered for "
                    + subject);
        }
        if (index < 0 || index >= assessments.get(subject).size()) {
            throw new InvalidParameterException("Index out of range for subject - " + subject);
        }
    }

    private boolean isStringInvalid(final String value) {
        return value == null || value.isEmpty();
    }

    private String name;
    private Map<String, List<Assessment>> assessments;
}
