package com.example.servingwebcontent;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

@Controller 
@RequestMapping(path="/") 
public class MainCourseController {
  @Autowired 
  private CourseRepository courseRepository;

  @PostMapping(path="/admin/add") // Map POST Requests
  public @ResponseBody ModelAndView addNewCourse (
  //  @RequestParam Integer course_id, 
    @RequestParam String course_name,
    @RequestParam String course_desc,
    @RequestParam String instructor_name,
    @RequestParam String course_img_url,
    @RequestParam Double course_rating,
    @RequestParam Integer course_price,
    @RequestParam String course_difficulty_level,
    @RequestParam Double course_estimated_duration
    ) {

    Course newCourse = new Course();
  //  newCourse.setCourseId(course_id);
    newCourse.setCourseName(course_name);
    newCourse.setCourseDesc(course_desc);
    newCourse.setCourseInstructor(instructor_name);
    newCourse.setCourseImgUrl(course_img_url);
    newCourse.setCourseRating(course_rating);
    newCourse.setCoursePrice(course_price);
    newCourse.setCourseDifficultyLevel(course_difficulty_level);
    newCourse.setCourseDuration(course_estimated_duration);
    courseRepository.save(newCourse);
    return new ModelAndView("redirect:/list");
  }

  // Update a course by ID
  // @GetMapping("/update/{course_id}")
  // public ModelAndView showUpdateCourseForm(@PathVariable("course_id") Integer course_id) {
  //     ModelAndView mav = new ModelAndView("update-course");
  //     Course course = courseRepository.findById(course_id)
  //         .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + course_id));
  //     mav.addObject("course", course);
  //     return mav;
  // }

  // @PostMapping("/update/{course_id}")
  // public @ResponseBody ModelAndView updateCourse(@PathVariable("course_id") Integer course_id, @Valid Course courseDetails, BindingResult result, Model model) {
  //     // if (result.hasErrors()) {
  //     //     courseDetails.setCourseId(course_id);
  //     //     return "update-course";
  //     // }
  //     Course course = courseRepository.findById(course_id)
  //         .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + course_id));
  //     course.setCourseName(courseDetails.getCourseName());
  //     course.setCourseDesc(courseDetails.getCourseDesc());
  //     course.setCourseInstructor(courseDetails.getCourseInstructor());
  //     course.setCourseImgUrl(courseDetails.getCourseImgUrl());
  //     course.setCourseRating(courseDetails.getCourseRating());
  //     course.setCoursePrice(courseDetails.getCoursePrice());
  //     course.setCourseDifficultyLevel(courseDetails.getCourseDifficultyLevel());
  //     course.setCourseDuration(courseDetails.getCourseDuration());
  //     courseRepository.save(course);
  //     return new ModelAndView("redirect:/list");
  //   }
  @GetMapping(path="/admin/update/{course_id}")
  public ModelAndView showUpdateForm(@PathVariable Integer course_id) {
  Optional<Course> optionalCourse = courseRepository.findById(course_id);
  if (!optionalCourse.isPresent()) {
  return new ModelAndView("error");
  }
  Course course = optionalCourse.get();
  ModelAndView modelAndView = new ModelAndView("update-course");
  modelAndView.addObject("course", course);
  return modelAndView;
  }

  @PostMapping(path="/admin/update/{course_id}")
  public @ResponseBody ModelAndView updateCourse (
  @PathVariable Integer course_id,
  @RequestParam String course_name,
  @RequestParam String course_desc,
  @RequestParam String instructor_name,
  @RequestParam String course_img_url,
  @RequestParam Double course_rating,
  @RequestParam Integer course_price,
  @RequestParam String course_difficulty_level,
  @RequestParam Double course_estimated_duration
  ) {
  Optional<Course> optionalCourse = courseRepository.findById(course_id);
  if (!optionalCourse.isPresent()) {
  return new ModelAndView("error");
  }
  Course existingCourse = optionalCourse.get();
  existingCourse.setCourseName(course_name);
  existingCourse.setCourseDesc(course_desc);
  existingCourse.setCourseInstructor(instructor_name);
  existingCourse.setCourseImgUrl(course_img_url);
  existingCourse.setCourseRating(course_rating);
  existingCourse.setCoursePrice(course_price);
  existingCourse.setCourseDifficultyLevel(course_difficulty_level);
  existingCourse.setCourseDuration(course_estimated_duration);
  courseRepository.save(existingCourse);
  return new ModelAndView("redirect:/admin/list");
  }
  // Delete a course by ID
  @GetMapping("/admin/delete/{course_id}")
  public String deleteCourse(@PathVariable("course_id") Integer course_id) {
      Course course = courseRepository.findById(course_id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + course_id));
      courseRepository.delete(course);
      return "redirect:/admin/list";
  }

  


  @GetMapping("/admin/add-course")
  public String showAddCourseForm(Model model) {
      model.addAttribute("courses", new Course());
      return "add-course";
  }

  @GetMapping("/admin/list")
  public String getAllAdminCourses(Model model) {
      model.addAttribute("courses", courseRepository.findAll());
      return "admin-list-courses";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<Course> getAllCoursesList() {
    
    return courseRepository.findAll();
  }

  @GetMapping("/list")
  public String getAllCourses(Model model) {
      model.addAttribute("courses", courseRepository.findAll());
      return "list-courses";
  }

  @GetMapping("/courses/{course_id}")
  public String getIndividualCourse(Model model, @PathVariable("course_id") Integer course_id) {
    Course course = courseRepository.findById(course_id)
    .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + course_id));
    model.addAttribute("courses", course);
      return "course-details";
  }

  @GetMapping({"/home", "/"})
  public String getIndex(Model model) {
      model.addAttribute("courses", courseRepository.findAll());
      return "home";
  }

   
}