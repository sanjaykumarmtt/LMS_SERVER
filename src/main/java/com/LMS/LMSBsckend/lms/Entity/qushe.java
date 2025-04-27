package com.LMS.LMSBsckend.lms.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class qushe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String Question;
	private String Option1;
	private String Option2;
	private String Option3;
	private String Option4;
	private int anser;
		
	  @ManyToOne(cascade=CascadeType.ALL)
	  @JoinColumn(name = "moduals_id")
	  @JsonBackReference
	 private moduals moduals;
	  
	  
	  public qushe() {
			super();
		}


	public qushe(int id, String question, String option1, String option2, String option3, String option4, int anser,
			moduals moduals) {
		super();
		this.id = id;
		Question = question;
		Option1 = option1;
		Option2 = option2;
		Option3 = option3;
		Option4 = option4;
		this.anser = anser;
		this.moduals = moduals;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getQuestion() {
		return Question;
	}


	public void setQuestion(String question) {
		Question = question;
	}


	public String getOption1() {
		return Option1;
	}


	public void setOption1(String option1) {
		Option1 = option1;
	}


	public String getOption2() {
		return Option2;
	}


	public void setOption2(String option2) {
		Option2 = option2;
	}


	public String getOption3() {
		return Option3;
	}


	public void setOption3(String option3) {
		Option3 = option3;
	}


	public String getOption4() {
		return Option4;
	}


	public void setOption4(String option4) {
		Option4 = option4;
	}


	public int getAnser() {
		return anser;
	}


	public void setAnser(int anser) {
		this.anser = anser;
	}


	public moduals getModuals() {
		return moduals;
	}


	public void setModuals(moduals moduals) {
		this.moduals = moduals;
	}
}
