package com.LMS.LMSBsckend.lms.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class moduals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modeulname;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;
    
    @OneToMany(mappedBy = "moduals", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<videos> videos;
    // Constructors, Getters, Setters

    @OneToMany(mappedBy = "moduals", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<qushe> qush;
    
	public moduals() {
		super();
	}

	public moduals(Long id, String modeulname, Course course,
			List<videos> videos, List<qushe> qush) {
		super();
		this.id = id;
		this.modeulname = modeulname;
		this.course = course;
		this.videos = videos;
		this.qush = qush;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModeulname() {
		return modeulname;
	}

	public void setModeulname(String modeulname) {
		this.modeulname = modeulname;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<videos> getVideos() {
		return videos;
	}

	public void setVideos(List<videos> videos) {
		this.videos = videos;
	}

	public List<qushe> getQush() {
		return qush;
	}

	public void setQush(List<qushe> qush) {
		this.qush = qush;
	}
}