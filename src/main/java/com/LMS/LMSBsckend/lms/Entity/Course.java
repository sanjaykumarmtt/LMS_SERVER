package com.LMS.LMSBsckend.lms.Entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String coursename;
	private  String Course_Provider;
	private  String Price;
	private  String Deuration;
	private  String Description;
	private  String what_you_learn1;
	private  String what_you_learn2;
	private  String what_you_learn3;
	private  String what_you_learn4;
	
	private String imgname;
	@Lob
	@Column(name="Imgbytdata",columnDefinition="LONGBLOB")
	private byte[] bytdata;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<moduals> modual;

	public Course(Long id, String coursename, String course_Provider, String price, String deuration,
			String description, String what_you_learn1, String what_you_learn2, String what_you_learn3,
			String what_you_learn4, String imgname, byte[] bytdata, List<moduals> modual) {
		super();
		this.id = id;
		this.coursename = coursename;
		Course_Provider = course_Provider;
		Price = price;
		Deuration = deuration;
		Description = description;
		this.what_you_learn1 = what_you_learn1;
		this.what_you_learn2 = what_you_learn2;
		this.what_you_learn3 = what_you_learn3;
		this.what_you_learn4 = what_you_learn4;
		this.imgname = imgname;
		this.bytdata = bytdata;
		this.modual = modual;
	}
	
	public String getCourse_Provider() {
		return Course_Provider;
	}


	public void setCourse_Provider(String course_Provider) {
		Course_Provider = course_Provider;
	}


	public String getPrice() {
		return Price;
	}


	public void setPrice(String price) {
		Price = price;
	}


	public String getDeuration() {
		return Deuration;
	}


	public void setDeuration(String deuration) {
		Deuration = deuration;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getWhat_you_learn1() {
		return what_you_learn1;
	}


	public void setWhat_you_learn1(String what_you_learn1) {
		this.what_you_learn1 = what_you_learn1;
	}


	public String getWhat_you_learn2() {
		return what_you_learn2;
	}


	public void setWhat_you_learn2(String what_you_learn2) {
		this.what_you_learn2 = what_you_learn2;
	}


	public String getWhat_you_learn3() {
		return what_you_learn3;
	}


	public void setWhat_you_learn3(String what_you_learn3) {
		this.what_you_learn3 = what_you_learn3;
	}


	public String getWhat_you_learn4() {
		return what_you_learn4;
	}


	public void setWhat_you_learn4(String what_you_learn4) {
		this.what_you_learn4 = what_you_learn4;
	}


	public String getImgname() {
		return imgname;
	}


	public void setImgname(String imgname) {
		this.imgname = imgname;
	}


	public byte[] getBytdata() {
		return bytdata;
	}


	public void setBytdata(byte[] bytdata) {
		this.bytdata = bytdata;
	}


	public Course() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public List<moduals> getModual() {
		return modual;
	}

	public void setModual(List<moduals> modual) {
		this.modual = modual;
	}	
}