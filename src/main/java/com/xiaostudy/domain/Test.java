package com.xiaostudy.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * 考试domain类
 *
 * @author liwei
 */
@Component
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer testId;
    private String testNumber;
    private String testName;
    private Subject subject;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", testNumber='" + testNumber + '\'' +
                ", testName='" + testName + '\'' +
                ", subject=" + subject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Test test = (Test) o;
        if (testId != null && test.testId != null) {
            return Objects.equals(testId, test.testId) &&
                    Objects.equals(testNumber, test.testNumber) &&
                    Objects.equals(testName, test.testName) &&
                    subject.equals(test.subject);
        }

        return Objects.equals(testNumber, test.testNumber) &&
                Objects.equals(testName, test.testName) &&
                subject.equals(test.subject);
    }

}
