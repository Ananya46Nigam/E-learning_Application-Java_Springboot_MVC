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
}

// @Controller
// public class InstructorController {
    
//     @Autowired
//     private InstructorService instructorService;
    
//     // Display all the instructors
//     @GetMapping("/")
//     public String viewHomePage(Model model) {
//         return "index";
//     }
    
//     // Show form for registering a new instructor
//     @GetMapping("/instructor_register")
//     public String showNewInstructorForm(Model model) {
//         Instructor instructor = new Instructor();
//         model.addAttribute("instructor", instructor);
//         return "new_instructor";
//     }
    
//     // Save a new instructor to the database
//     @PostMapping("/saveInstructor")
//     public String saveInstructor(@ModelAttribute("instructor") Instructor instructor) {
//         instructorService.saveInstructor(instructor);
//         return "redirect:/";
//     }
    
//     // Show form for logging in as an instructor
//     @GetMapping("/instructor_login")
//     public String showInstructorLoginForm(Model model) {
//         Instructor instructor = new Instructor();
//         model.addAttribute("instructor", instructor);
//         return "instructor_login";
//     }
    
//     // Handle instructor login form submission
//     @PostMapping("/instructor_login")
//     public String handleInstructorLoginForm(@ModelAttribute("instructor") Instructor instructor, Model model) {
//         boolean isValidInstructor = instructorService.isValidInstructor(instructor.getUsername(), instructor.getPassword());
//         if (isValidInstructor) {
//             model.addAttribute("username", instructor.getUsername());
//             return "redirect:/instructor_functions";
//         } else {
//             model.addAttribute("errorMessage", "Invalid username or password");
//             return "instructor_login";
//         }
//     }
    
//     // Show the instructor functions page
//     @GetMapping("/instructor_functions")
//     public String showInstructorFunctionsPage(Model model, @RequestParam("username") String username) {
//         Instructor instructor = instructorService.getInstructorByUsername(username);
//         model.addAttribute("instructor", instructor);
//         return "instructor_functions";
//     }
    
//     // Show form for updating instructor information
//     @GetMapping("/update_instructor")
//     public String showUpdateInstructorForm(Model model, @RequestParam("username") String username) {
//         Instructor instructor = instructorService.getInstructorByUsername(username);
//         model.addAttribute("instructor", instructor);
//         return "update_instructor";
//     }
    
//     // Handle instructor update form submission
//     @PostMapping("/update_instructor")
//     public String handleUpdateInstructorForm(@ModelAttribute("instructor") Instructor instructor) {
//         instructorService.saveInstructor(instructor);
//         return "redirect:/instructor_functions?username=" + instructor.getUsername();
//     }
// }
