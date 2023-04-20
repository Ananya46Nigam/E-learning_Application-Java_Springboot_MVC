package com.example.servingwebcontent;
import java.util.List;

interface InstructorService 
{
    List<Instructor> getAllInstructors();
    void saveInstructor(Instructor instructor);
    Instructor getInstructorById(Integer id);
    void deleteInstructorById(Integer id);
    Instructor getInstructorByUsername(String username);
    boolean isValidInstructor(String username, String password);
    boolean isValidPassword(String password); 
    void saveUpdatedInstructor(Instructor instructor);
    //public void addNewCourse(Course course);   
}
