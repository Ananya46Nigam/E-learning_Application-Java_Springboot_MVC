package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class InstructorController
{
  @Autowired // MainController class can use the methods provided by the 
  //InstructorService class without having to explicitly create an instance of it.
  private InstructorService instructorService;
  //display all the instructors
  @GetMapping("/")
  public String viewHomePage(Model model)
  {
    return "index";
  }

  @GetMapping("/LoginInstructorForm")
  public String showNewInstructorForm(Model model)
  {
    //create model attribute to bind the form data
    Instructor instructor = new Instructor();
    //thymleaf template will access this empty instructor object for binding form data{"instructor"}
    model.addAttribute("instructor",instructor);
    return "instructor_login";
  }

   
  @GetMapping("/instructor_register")
    public String InstructorRegister(Model model) {
        Instructor instructor = new Instructor();
        model.addAttribute("instructor", instructor);
        return "new_instructor";
  }

  @PostMapping("/saveInstructor")
  public String saveInstructor(@ModelAttribute("instructor") Instructor instructor)
  {
    //save instructor to database
    instructorService.saveInstructor(instructor);
    return "redirect:/";
  }

@GetMapping("/instructor_login")
    public String InstructorLogin(Model model) 
    {
        Instructor instructor = new Instructor();
        model.addAttribute("instructor", instructor);
        return "instructor_login";
    }
@PostMapping("/instructor_login")
public String handleInstructorLogin(@ModelAttribute("instructor") Instructor instructor) 
{
      
        if (instructorService.isValidInstructor(instructor.getUsername(),instructor.getPassword())) {
            return "instructor_functions";
        } else {
            return "instructor_login";
        }
    } 

@GetMapping("/instructor_functions")
public String InstructorFunctions(Model model, @RequestParam("username") String username) 
    {
        // get instructor from the service based on the username
        Instructor instructor = instructorService.getInstructorByUsername(username);
        // create model attribute to bind the form data
        model.addAttribute("instructor", instructor);
        return "instructor_functions";
    }
    @GetMapping("/updateinstructor")
    public String showFormForUpdate(Model model, @RequestParam("username") String username) {
        // get instructor from the service based on the username
        Instructor instructor = instructorService.getInstructorByUsername(username);
        // create model attribute to bind the form data
        model.addAttribute("instructor", instructor);
        return "update_instructor";
    }

  //   @PostMapping("/saveUpdatedInstructor")
  // public String saveUpdatedInstructor(@ModelAttribute("instructor") Instructor instructor,Mode)
  // {
  //   instructorService.saveUpdatedInstructor(instructor);
  //   return "instructor_functions";
  // }

@PostMapping("/saveUpdatedInstructor")
public String saveUpdatedInstructor(@ModelAttribute("instructor") Instructor instructor, Model model)
{
    instructorService.saveUpdatedInstructor(instructor);
    // retrieve the updated instructor object from the service layer
    Instructor updatedInstructor = instructorService.getInstructorByUsername(instructor.getUsername());
    // add the updated instructor object to the model attribute
    model.addAttribute("instructor", updatedInstructor);
    return "instructor_details";
    
}

}