package com.example.servingwebcontent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Integer> 
{
    Learner findByUsername(String username);
}