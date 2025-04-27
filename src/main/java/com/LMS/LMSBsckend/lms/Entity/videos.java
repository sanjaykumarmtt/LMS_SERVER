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
public class videos {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String videoname;
	 private String videourl;
	 
	   @ManyToOne(cascade=CascadeType.ALL)
	    @JoinColumn(name = "moduals_id")
	    @JsonBackReference
	 private moduals moduals;

	public videos(Long id, String videoname, String videourl,
		   moduals moduals) {
		super();
		this.id = id;
		this.videoname = videoname;
		this.videourl = videourl;
		this.moduals = moduals;
	}

	public videos() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public moduals getModuals() {
		return moduals;
	}

	public void setModuals(moduals moduals) {
		this.moduals = moduals;
	}
}

