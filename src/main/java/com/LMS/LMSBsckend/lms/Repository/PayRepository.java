package com.LMS.LMSBsckend.lms.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.LMS.LMSBsckend.lms.Entity.Student_progress;

@Repository
public interface PayRepository extends JpaRepository<Student_progress,Integer>{

	@Query(value="SELECT *FROM Student_progress WHERE razorpayid= :ordetid",nativeQuery=true)
	public Student_progress findByRazOrpayId(@Param("ordetid") String ordetid);
	
	@Query(value="SELECT *FROM Student_progress WHERE course_id= :id  and email=:emai",nativeQuery=true)
	public Student_progress findBycourseid(@Param("id") long id,@Param("emai") String emai);
	
	@Query(value="SELECT *FROM Student_progress WHERE course_id= :id AND email=:email",nativeQuery=true)
	public Student_progress findByidandemail(@Param("id") long id,@Param("email") String email);
	
}
//select *from student_progress where course_id=1 and email="sanjaykumarmtt@gmail.coml";