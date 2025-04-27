package com.LMS.LMSBsckend.lms.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student_progress {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Long phon;
    private String coursename;
    private Long courseId;
	private  String Course_Provider;
	private int amont;
	private String orderStatus;
	private String razorpayid;
	List<Long> completedVideoIds;
	double percentage;
	boolean CertificateAvailable;
    
	public Student_progress() {
		super();
	}



	public Student_progress(Long id, String name, String email, Long phon, String coursename, Long courseId,
			String course_Provider, int amont, String orderStatus, String razorpayid, List<Long> completedVideoIds,
			double percentage, boolean certificateAvailable) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phon = phon;
		this.coursename = coursename;
		this.courseId = courseId;
		Course_Provider = course_Provider;
		this.amont = amont;
		this.orderStatus = orderStatus;
		this.razorpayid = razorpayid;
		this.completedVideoIds = completedVideoIds;
		this.percentage = percentage;
		CertificateAvailable = certificateAvailable;
	}



	public boolean isCertificateAvailable() {
		return CertificateAvailable;
	}



	public void setCertificateAvailable(boolean certificateAvailable) {
		CertificateAvailable = certificateAvailable;
	}



	public List<Long> getCompletedVideoIds() {
		return completedVideoIds;
	}
	


	public void setCompletedVideoIds(List<Long> completedVideoIds) {
		this.completedVideoIds = completedVideoIds;
	}


	public double getPercentage() {
		return percentage;
	}


	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhon() {
		return phon;
	}

	public void setPhon(Long phon) {
		this.phon = phon;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourse_Provider() {
		return Course_Provider;
	}

	public void setCourse_Provider(String course_Provider) {
		Course_Provider = course_Provider;
	}

	public int getAmont() {
		return amont;
	}

	public void setAmont(int amont) {
		this.amont = amont;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRazorpayid() {
		return razorpayid;
	}

	public void setRazorpayid(String razorpayid) {
		this.razorpayid = razorpayid;
	}
}