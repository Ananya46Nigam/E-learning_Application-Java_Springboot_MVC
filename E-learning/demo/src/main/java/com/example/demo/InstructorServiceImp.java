package com.example.demo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
//A service component is a class that 
//contains business logic and performs some specific operations in an application. 
//@Service annotation,we are instructing the Spring Framework to automatically create 
//an instance of the class at runtime and manage its lifecycle.
public class InstructorServiceImp implements InstructorService 
{
    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<Instructor> getAllInstructors()
    {
        return instructorRepository.findAll();
    }

    @Override
    public void saveInstructor(Instructor instructor)
    {
        this.instructorRepository.save(instructor); //save instructor to the database
    }

    @Override
    public Instructor getInstructorById(Integer id)
    {
        Optional<Instructor> optional = instructorRepository.findById(id);
        Instructor instructor = null;
        if(optional.isPresent())
        {
            instructor = optional.get();
        }
        else
        {
            throw new RuntimeException("Instructor not found for id::" + id);
        }
        return instructor;
    }

    @Override
    public void deleteInstructorById(Integer id)
    {
        this.instructorRepository.deleteById(id);
    }

    @Override
    public Instructor getInstructorByUsername(String username) {
        return instructorRepository.findByUsername(username);
     }
    
    @Override
    public boolean isValidInstructor(String username, String password) {
        // retrieve the instructor object by username
        Instructor instructor = getInstructorByUsername(username);
    
        // check if the instructor object is not null and password matches
        if (instructor != null && instructor.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isValidPassword(String password) {
        // password should have at least 8 characters and contain at least one digit, lowercase letter, and uppercase letter
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return password.matches(passwordRegex);
    }

    @Override
    public void saveUpdatedInstructor(Instructor instructor) {
        Instructor existingInstructor = getInstructorByUsername(instructor.getUsername());
    
        if (existingInstructor != null) 
        {
            existingInstructor.setFirstname(instructor.getFirstname());
            existingInstructor.setLastname(instructor.getLastname());
            existingInstructor.setEmail_id(instructor.getEmail_id());
            existingInstructor.setContact_no(instructor.getContact_no());
            this.instructorRepository.save(existingInstructor);
        }
    }
}
