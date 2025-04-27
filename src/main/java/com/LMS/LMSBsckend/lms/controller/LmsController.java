package com.LMS.LMSBsckend.lms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.LMS.LMSBsckend.lms.Entity.Course;
import com.LMS.LMSBsckend.lms.Entity.Student_progress;
import com.LMS.LMSBsckend.lms.Service.LmsService;
import com.razorpay.RazorpayException;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LmsController {

	@Autowired
	LmsService LmsService;

	@PostMapping("/public/postCoruse_data")
	public ResponseEntity<String> Coruse_data(@RequestPart("fail") MultipartFile fail,
			@RequestPart("Course") Course Course_det) throws IOException {
		System.out.println(fail.getOriginalFilename());
		System.out.println(Course_det.getCoursename());
		LmsService.Save_Corse_data(Course_det, fail);
		return ResponseEntity.ok().body("Save Successful Data");
	}

//	@PostMapping("/public/video_data")
//	public ResponseEntity<String> video_data(@RequestPart("fail") MultipartFile fail,
//			@RequestPart("Course") Entvideose Entvideose) throws IOException, HostelException {
////		System.out.println(fail.getOriginalFilename());
////		System.out.println(Entvideose.getVideoname());
////		System.out.println(Entvideose.getChildFolderName());
//
//		return ResponseEntity.ok().body(LmsService.storeVideo(fail, Entvideose));
//		// return ResponseEntity.ok().body("hello");
//	}

	@GetMapping("/public/getCoruse_data") // get All Images Overall_dataEnt
	public ResponseEntity<List<Map<String, Object>>> GetAllcorude() throws IOException {
		return ResponseEntity.ok(LmsService.GetAllcourse());
	}

//	@GetMapping("/public/Getvideo_data") // get strem one video
//	public ResponseEntity<Resource> streamVideo(@RequestParam("parentFolderName") String parentFolderName,
//			@RequestParam("childFolderName") String childFolderName, @RequestParam("url") String url)
//			throws IOException {
//		Path filePath = LmsService.getVideoPath(parentFolderName, childFolderName, url);
//		Resource resource = new UrlResource(filePath.toUri());
//
//		if (resource.exists() && resource.isReadable()) {
//			return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//					.body(resource);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

	@GetMapping("/user/getvideodata/{id}")
	public Optional<Course> Get(@PathVariable long id) {
		return LmsService.getonevideodata(id);
	}

//	@GetMapping("/user/getvideodata/{video}")
//	public ResponseEntity<List<Entvideose>> getvideodata(@PathVariable("id") Long id) {
//		return ResponseEntity.ok().body(LmsService.getvideodataurl(id));
//
//	}

	@PostMapping("/user/paymen_progressr")
	public ResponseEntity<Student_progress> creder(@RequestBody Student_progress setubaye) throws RazorpayException {
		return new ResponseEntity<>(LmsService.Baycorus(setubaye), HttpStatus.CREATED);
	}

	@PostMapping("/user/paymen_progressr-updata")
	public RedirectView UpdateStetes(@RequestParam Map<String, String> responsepay) throws RazorpayException {
		Student_progress setubaye = LmsService.updateorder(responsepay);
		System.out.println(responsepay);
		return new RedirectView("http://localhost:5173/");
	}

	@GetMapping("/user/oneidandemail")
	public ResponseEntity<Student_progress> Idemailgetpay(@RequestParam long id, @RequestParam String email)
			throws RazorpayException {
		return ResponseEntity.ok(LmsService.getemailandid(id, email));
	}

	@PutMapping("user/updatedata_paragarse")
	public ResponseEntity<Student_progress> Update_progress(@RequestParam String email, @RequestParam Long courseId,
			@RequestParam List<Long> completedVideoIds) {
		return ResponseEntity.ok(LmsService.updateProgress(email, courseId, completedVideoIds));

	}

	@GetMapping("user/certificate")
	public ResponseEntity<byte[]> getCertificate(@RequestParam String email, @RequestParam Long courseId)
			throws Exception {
//	        Student student = new Student();
//	        student.setName("John Doe");
//	        student.setCourse("Java Spring Boot");
//	        student.setCompletionDate("date " + new Date());
		byte[] pdfBytes = LmsService.generateCertificate(courseId, email);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("certificate.pdf", "certificate.pdf");
		return ResponseEntity.ok().headers(headers).body(pdfBytes);
		
//		byte[] pdfbyt = StudService.month_mess_fess_pdfdownote(month);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_PDF);
//		headers.setContentDispositionFormData("attachment", "Attendanceonemonth.pdf");
//
//		return ResponseEntity.ok().headers(headers).body(pdfbyt);
	}

//lnkhuyftdzdf
	@GetMapping("/admin/get")
	public String admin() {
		return "Admin Login LMS Spring";
	}

	@GetMapping("/user2/get")
	public String user2() {
		return "User Login LMS Spring";
	}

	@GetMapping("/user/get")
	public String user() {
		return "User1 Login LMS Spring";
	}

	@GetMapping("/public/get")
	public String user1() {
		return "User2 Login LMS Spring";
	}
}
