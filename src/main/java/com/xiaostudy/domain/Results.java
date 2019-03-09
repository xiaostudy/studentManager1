package com.xiaostudy.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * 成绩domain类
 * 
 * @author liwei
 *
 */
@Component
public class Results implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer resultsId;
	private String resultsNumber;
	private Student student;
	private Test test;
	private Integer score;

	public Integer getResultsId() {
		return resultsId;
	}

	public void setResultsId(Integer resultsId) {
		this.resultsId = resultsId;
	}

	public String getResultsNumber() {
		return resultsNumber;
	}

	public void setResultsNumber(String resultsNumber) {
		this.resultsNumber = resultsNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Results{" +
				"resultsId=" + resultsId +
				", resultsNumber='" + resultsNumber + '\'' +
				", student=" + student +
				", test=" + test +
				", score=" + score +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {return true;}

		if (o == null || getClass() != o.getClass()) {return false;}

		Results results = (Results) o;
		if(resultsId != null && results.resultsId != null) {
			return Objects.equals(resultsId, results.resultsId) &&
					Objects.equals(resultsNumber, results.resultsNumber) &&
					student.equals(results.student) &&
					test.equals(results.test) &&
					Objects.equals(score, results.score);
		}

		return Objects.equals(resultsNumber, results.resultsNumber) &&
				student.equals(results.student) &&
				test.equals(results.test) &&
				Objects.equals(score, results.score);
	}

}
