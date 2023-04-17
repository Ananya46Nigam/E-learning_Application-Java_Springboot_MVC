package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class LearnerController
{
  @Autowired
  private LearnerService learnerService;
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
            return "course";
        } else {
            return "learner_login";
        }
    } 
}

