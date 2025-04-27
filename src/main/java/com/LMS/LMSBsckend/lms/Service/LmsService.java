package com.LMS.LMSBsckend.lms.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.LMS.LMSBsckend.Exception.HostelException;
import com.LMS.LMSBsckend.Login.Entity.LoginEntity;
import com.LMS.LMSBsckend.Login.Repository.LogRepository;
import com.LMS.LMSBsckend.lms.Entity.Course;
import com.LMS.LMSBsckend.lms.Entity.Course_det;
import com.LMS.LMSBsckend.lms.Entity.Entvideose;
import com.LMS.LMSBsckend.lms.Entity.Student_progress;
import com.LMS.LMSBsckend.lms.Entity.moduals;
import com.LMS.LMSBsckend.lms.Entity.qushe;
import com.LMS.LMSBsckend.lms.Entity.videos;
import com.LMS.LMSBsckend.lms.Repository.LmsCorse_Title_Reposirory;
import com.LMS.LMSBsckend.lms.Repository.PayRepository;
import com.LMS.LMSBsckend.lms.Repository.video_det;
import com.lowagie.text.DocumentException;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class LmsService {

	private LmsCorse_Title_Reposirory LmsCorse_Title_Reposirory;
	private video_det video_det;
	private Path videoStorageLocation;
	private PayRepository PayRepository;
	private LogRepository LogRepository;
	private final TemplateEngine templateEngine;


	@Value("${razorpay.key.id}")
	String keyid;
	@Value("${razorpay.secret.key}")
	String secretid;

	private RazorpayClient clinde;

	public LmsService(LmsCorse_Title_Reposirory lmsCorse_Title_Reposirory, video_det video_det,
			PayRepository PayRepository, LogRepository LogRepository,TemplateEngine templateEngine) {
		super();
		LmsCorse_Title_Reposirory = lmsCorse_Title_Reposirory;
		this.video_det = video_det;
		this.PayRepository = PayRepository;
		this.LogRepository = LogRepository;
		this.templateEngine = templateEngine;
		this.videoStorageLocation = Paths.get("");
	}

	public void Save_Corse_data(Course Course_det, MultipartFile fail) throws IOException {

		// videoStorageLocation = Paths.get(Course_det.getCourse_name());

//		Files.createDirectories(videoStorageLocation);
		Course newCourse = new Course();
		newCourse.setBytdata(fail.getBytes());
		newCourse.setImgname(fail.getOriginalFilename());

		newCourse.setCoursename(Course_det.getCoursename());
		newCourse.setCourse_Provider(Course_det.getCourse_Provider());
		newCourse.setPrice(Course_det.getPrice());
		newCourse.setDeuration(Course_det.getDeuration());
		newCourse.setDescription(Course_det.getDescription());
		newCourse.setWhat_you_learn1(Course_det.getWhat_you_learn1());
		newCourse.setWhat_you_learn2(Course_det.getWhat_you_learn2());
		newCourse.setWhat_you_learn3(Course_det.getWhat_you_learn3());
		newCourse.setWhat_you_learn4(Course_det.getWhat_you_learn4());

		List<moduals> newModules = new ArrayList<>();

		for (moduals moduleData : Course_det.getModual()) {
			moduals newModule = new moduals();
			newModule.setModeulname(moduleData.getModeulname());
			newModule.setCourse(newCourse);

			List<videos> lisvideos = new ArrayList<>();
			if (moduleData.getVideos() != null) {
				for (videos videos : moduleData.getVideos()) {
					videos newvideos = new videos();
					newvideos.setVideoname(videos.getVideoname());
					newvideos.setVideourl(videos.getVideourl());
					newvideos.setModuals(newModule);

					lisvideos.add(newvideos);
				}
			}
			List<qushe> lisqushe = new ArrayList<>();
			if (moduleData.getQush() != null) {
				for (qushe qushe : moduleData.getQush()) {
					qushe newqushe = new qushe();
					newqushe.setQuestion(qushe.getQuestion());
					newqushe.setOption1(qushe.getOption1());
					newqushe.setOption2(qushe.getOption2());
					newqushe.setOption3(qushe.getOption3());
					newqushe.setOption4(qushe.getOption4());

					newqushe.setAnser(qushe.getAnser());

					newqushe.setModuals(newModule);

					lisqushe.add(newqushe);
				}
			}

			newModule.setQush(lisqushe);
			newModule.setVideos(lisvideos);
			newModules.add(newModule);

		}
		newCourse.setModual(newModules);

		LmsCorse_Title_Reposirory.save(newCourse);
	}

	public String storeVideo(MultipartFile file, Entvideose Entvideose) throws HostelException {
		String fileName = Entvideose.getVideoname() + "_" + file.getOriginalFilename();
		try {

			Course_det Course_det = LmsCorse_Title_Reposirory.getByusername(Entvideose.getVideoname());

			if (Course_det != null) {
				videoStorageLocation = Paths.get(Entvideose.getVideoname());
				Path childFolderPath = videoStorageLocation.resolve(Entvideose.getChildFolderName());

				// பெற்றோர் கோப்புறையை உருவாக்குதல்
				if (!Files.exists(videoStorageLocation)) {
					Files.createDirectories(videoStorageLocation);
				}

				// குழந்தை கோப்புறையை உருவாக்குதல்
				if (!Files.exists(childFolderPath)) {
					Files.createDirectories(childFolderPath);
				}

				Path targetLocation = childFolderPath.resolve(fileName);
				Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

				Entvideose.setVideourl(fileName);
				Entvideose.setVideoAtername(Course_det.getCourse_Provider());

				video_det.save(Entvideose);
				return fileName + "sucessfull store video";
			} else {
				throw new HostelException("Not video name");
			}

		} catch (IOException e) {
			throw new RuntimeException("வீடியோவை சேமிக்க முடியவில்லை", e);
		}
	}

	public Path getVideoPath(String parentFolderName, String childFolderName, String url) {
		videoStorageLocation = Paths.get(parentFolderName, childFolderName);
		return videoStorageLocation.resolve(url);
	}

	// get All Student Image St_imag
	public List<Map<String, Object>> GetAllcourse() throws IOException {
		return LmsCorse_Title_Reposirory.findAll().stream().map(course -> {
			Map<String, Object> imgdata = new HashMap<>();
			imgdata.put("id", course.getId());
			imgdata.put("course_name", course.getCoursename());
			imgdata.put("course_Provider", course.getCourse_Provider());
			imgdata.put("price", course.getPrice());
			imgdata.put("deuration", course.getDeuration());
			imgdata.put("description", course.getDescription());
			imgdata.put("what_you_learn1", course.getWhat_you_learn1());
			imgdata.put("what_you_learn2", course.getWhat_you_learn2());
			imgdata.put("what_you_learn3", course.getWhat_you_learn3());
			imgdata.put("what_you_learn4", course.getWhat_you_learn4());
			imgdata.put("course_image_name", course.getImgname());

			imgdata.put("data", Base64.getEncoder().encodeToString(course.getBytdata()));
			return imgdata;
		}).toList();
	}

//	public List<Entvideose> getvideodataurl(Long id){
//		List<Entvideose> Entvideose=video_det.getByvideo(coname);
//		
//		return Entvideose;
//	}
//		
	public Optional<Course> getonevideodata(long id) {
		return LmsCorse_Title_Reposirory.findById(id);
	}

	// payment table
	public Student_progress Baycorus(Student_progress setubaye) throws RazorpayException, HostelException {
		this.clinde = new RazorpayClient(keyid, secretid);
		Student_progress baye = PayRepository.findBycourseid(setubaye.getCourseId(), setubaye.getEmail());
		if (baye != null) {
			System.out.println(baye.getEmail());
			return baye;
		} else {
			LoginEntity LoginEntity = LogRepository.getByusername(setubaye.getEmail());
			if (LoginEntity != null) {
				JSONObject orderreq = new JSONObject();

				orderreq.put("amount", setubaye.getAmont() * 100);
				orderreq.put("currency", "INR");
				orderreq.put("receipt", setubaye.getEmail());

				Order razorpay = clinde.orders.create(orderreq);

				setubaye.setRazorpayid(razorpay.get("id"));
				setubaye.setOrderStatus(razorpay.get("status"));
				setubaye.setPhon(LoginEntity.getPhone());
				setubaye.setName(LoginEntity.getName());

				return PayRepository.save(setubaye);
			} else {
				throw new HostelException("username not founde");
			}
		}

	}

	// states payment table
	public Student_progress updateorder(Map<String, String> responsepay) {
		String razorpayorderid = responsepay.get("razorpay_order_id");
		Student_progress baye = PayRepository.findByRazOrpayId(razorpayorderid);
		baye.setOrderStatus("PAYMENT_COMPLETED");
		return PayRepository.save(baye);
	}

	// get id and email payment table
	public Student_progress getemailandid(long id, String email) {
		return PayRepository.findByidandemail(id, email);
	}

	public Student_progress updateProgress(String email, Long courseId, List<Long> completedVideoIds) {
		Student_progress progress = PayRepository.findByidandemail(courseId, email);

//		        progress.setUserId(userId);
//		        progress.setCourseId(courseId);

		// 1. Save completed video list
		progress.setCompletedVideoIds(completedVideoIds);

		// 2. Calculate total videos in course
		Course course = LmsCorse_Title_Reposirory.findById(courseId)
				.orElseThrow(() -> new RuntimeException("Course not found"));

		int totalVideos = course.getModual().stream().flatMap(module -> module.getVideos().stream())
				.collect(Collectors.toList()).size();

		// 3. Calculate percentage
		float percentage = (completedVideoIds.size() / (float) totalVideos) * 100;
		progress.setPercentage(percentage);

		// 4. Check for certificate availability
		if (percentage >= 100.0) {
			progress.setCertificateAvailable(true);
		} else {
			progress.setCertificateAvailable(false);
		}

		// 5. Save updated progress
		return PayRepository.save(progress);
	}
	
    public byte[] generateCertificate(long cores_id,String email) throws IOException, DocumentException,HostelException {
    	Student_progress progress = PayRepository.findByidandemail(cores_id, email);
    	if(progress.getCertificateAvailable()) {
    		 Context context = new Context();
    	        context.setVariable("progress", progress);
    	        String html = templateEngine.process("certificate-template", context);
    	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    	        ITextRenderer renderer = new ITextRenderer();
    	        renderer.setDocumentFromString(html);
    	        renderer.layout();
    	        renderer.createPDF(outputStream);
    	        return outputStream.toByteArray();
    	}else {
    		throw new HostelException("Your Course DId Not Completed");
    	}
       
    }
}
