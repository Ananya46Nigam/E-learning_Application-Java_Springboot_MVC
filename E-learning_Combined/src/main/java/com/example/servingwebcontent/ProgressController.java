// package com.example.servingwebcontent;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.SessionAttributes;

// import jakarta.servlet.http.HttpSession;

// @Controller
// @SessionAttributes("progress")
// public class ProgressController {
    
//     @PostMapping("/markAsDone")
//     public String markAsDone(@RequestParam int itemId, HttpSession session) {
//         int progress = (int) session.getAttribute("progress");
//         progress++;
//         session.setAttribute("progress", progress);
//         return "redirect:/";
//     }

//     @GetMapping("/view-progress")
//     public String myPage(Model model, HttpSession session) {
//         if (!model.containsAttribute("progress")) {
//             session.setAttribute("progress", 0);
//         }
//         // Other model attributes and view name
//         return "view-progress";
//     }
    
//     // Other controller methods
    
// }