package com.LMS.LMSBsckend.lms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.LMS.LMSBsckend.lms.Entity.Course;
import com.LMS.LMSBsckend.lms.Entity.Course_det;

@Repository
public interface LmsCorse_Title_Reposirory extends JpaRepository<Course,Long>{
	
	@Query(value="SELECT *FROM Course WHERE course_name= :course",nativeQuery=true)
	Course_det getByusername(@Param("course") String course);

}
