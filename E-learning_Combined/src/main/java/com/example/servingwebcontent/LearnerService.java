package com.example.servingwebcontent;
import java.util.List;

interface LearnerService 
{
    List<Learner> getAllLearners();
    void saveLearner(Learner learner);
    Learner getLearnerById(Integer id);
    void deleteLearnerById(Integer id);
    Learner getLearnerByUsername(String username);
    boolean isValidLearner(String username, String password);
    boolean isValidPassword(String password); 
    void saveUpdatedLearner(Learner learner);
}
