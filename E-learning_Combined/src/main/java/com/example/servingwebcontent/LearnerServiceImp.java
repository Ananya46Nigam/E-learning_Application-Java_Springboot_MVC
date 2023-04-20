package com.example.servingwebcontent;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
//A service component is a class that 
//contains business logic and performs some specific operations in an application. 
//@Service annotation,we are instructing the Spring Framework to automatically create 
//an instance of the class at runtime and manage its lifecycle.
public class LearnerServiceImp implements LearnerService 
{
    @Autowired
    private LearnerRepository learnerRepository;

    @Override
    public List<Learner> getAllLearners()
    {
        return learnerRepository.findAll();
    }

    @Override
    public void saveLearner(Learner learner)
    {
        this.learnerRepository.save(learner); //save instructor to the database
    }

    @Override
    public Learner getLearnerById(Integer id)
    {
        Optional<Learner> optional = learnerRepository.findById(id);
        Learner learner= null;
        if(optional.isPresent())
        {
            learner = optional.get();
        }
        else
        {
            throw new RuntimeException("Learner not found for id::" + id);
        }
        return learner;
    }

    @Override
    public void deleteLearnerById(Integer id)
    {
        this.learnerRepository.deleteById(id);
    }

    @Override
    public Learner getLearnerByUsername(String username) {
        return learnerRepository.findByUsername(username);
     }
    
    @Override
    public boolean isValidLearner(String username, String password) {
        // retrieve the instructor object by username
        Learner learner = getLearnerByUsername(username);
    
        // check if the instructor object is not null and password matches
        if (learner != null && learner.getPassword().equals(password)) {
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
    public void saveUpdatedLearner(Learner learner) {
        Learner existingLearner = getLearnerByUsername(learner.getUsername());
    
        if (existingLearner != null) 
        {
            existingLearner.setFirstname(learner.getFirstname());
            existingLearner.setLastname(learner.getLastname());
            existingLearner.setEmail_id(learner.getEmail_id());
            existingLearner.setContact_no(learner.getContact_no());
            this.learnerRepository.save(existingLearner);
        }
    }
}
