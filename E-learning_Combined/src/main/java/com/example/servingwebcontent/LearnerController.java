package com.example.servingwebcontent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LearnerController
{
  @Autowired
  private LearnerService learnerService;

  @Autowired 
  private CourseRepository courseRepository;
  
  @GetMapping("/LoginLearnerForm")
  public String LearnerLogin(Model model)
  {
    Learner learner = new Learner();
    model.addAttribute("learner",learner);
    return "learner_login";
  }

   
  @GetMapping("/learner_register")
    public String LearnerRegister(Model model) {
        Learner learner = new Learner();
        model.addAttribute("learner", learner);
        return "new_learner";
  }

  @PostMapping("/saveLearner")
  public String saveInstructor(@ModelAttribute("learner")  Learner learner)
  {
    //save instructor to database
   learnerService.saveLearner(learner);
    return "learner_login";
  }

@GetMapping("/learner_login")
    public String InstructorLogin(Model model) 
    {
        Learner learner = new Learner();
        model.addAttribute("learner", learner);
        return "learner_login";
    }
@PostMapping("/learner_login")
public String handleInstructorLogin(@ModelAttribute("learner")  Learner learner) 
{
      
        if (learnerService.isValidLearner(learner.getUsername(),learner.getPassword())) {
            return "learner_functions";
        } else {
            return "learner_login";
        }
    }
    @GetMapping("/learner_functions")
    public String InstructorFunctions(Model model, @RequestParam("username") String username) 
        {
            // get instructor from the service based on the username
            Learner learner = learnerService.getLearnerByUsername(username);
            // create model attribute to bind the form data
            model.addAttribute("learner", learner);
            return "learner_functions";
        }
        @GetMapping("/updatelearner")
        public String showFormForUpdate(Model model, @RequestParam("username") String username) {
            // get instructor from the service based on the username
            Learner learner = learnerService.getLearnerByUsername(username);
            // create model attribute to bind the form data
            model.addAttribute("learner",learner);
            return "update_learner";
        }
    
        @PostMapping("/saveUpdatedLearner")
      public String saveUpdatedLearner(@ModelAttribute("learner") Learner learner)
      {
        learnerService.saveUpdatedLearner(learner);
        return "learner_details";
      }

      @GetMapping("/enrollcourse")
      public String getAllCourses(Model model) {
          model.addAttribute("courses", courseRepository.findAll());
          return "list-courses";
      }
      @PostMapping("/enrollforcourse")
      public String enrollForCourse(@RequestParam("courseId") Integer courseId, Model model) {
        Course course = courseRepository.findById(courseId).get();
        int enroll = course.getLearner_enrolled() + 1;
        course.setLearner_enrolled(enroll);
        courseRepository.save(course); // Save the updated course to the database
        model.addAttribute("course", course);
        return "courseenrolled";
      }


  //@GetMapping("/cour")
}

