package com.example.servingwebcontent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> 
//exposes CRUD operations to Instructor database
{
    Instructor findByUsername(String username);
}