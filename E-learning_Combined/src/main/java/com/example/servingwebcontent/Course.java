package com.example.servingwebcontent;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity 
public class Course {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Integer course_id;
  public String course_img_url;
  public String course_name;
  public String course_desc;
  public String instructor_name;
  public Double course_rating;
  public Integer course_price;
  public String course_difficulty_level;
  public Double course_estimated_duration;
  public Integer learner_enrolled = 0 ;
  
   /* course id */
  public Integer getCourseId() {
    return course_id;
  }
  public void setCourseId(Integer course_id) {
    this.course_id = course_id;
  }

   /* course name */
  public String getCourseName() {
    return course_name;
  }
  public void setCourseName(String course_name) {
    this.course_name = course_name;
  }

   /* course description */
  public String getCourseDesc() {
    return course_desc;
  }
  public void setCourseDesc(String course_desc) {
    this.course_desc = course_desc;
  }

   /* course instructor */
  public String getCourseInstructor() {
    return instructor_name;
  }
  public void setCourseInstructor(String instructor_name) {
    this.instructor_name = instructor_name;
  }

   /* course img url */
  public String getCourseImgUrl() {
    return course_img_url;
  }
  public void setCourseImgUrl(String course_img_url) {
    this.course_img_url = course_img_url;
  }

  /* course rating */
  public Double getCourseRating() {
    return course_rating;
  }
  public void setCourseRating(Double course_rating) {
    this.course_rating = course_rating;
  }

   /* course price */
  public Integer getCoursePrice() {
    return course_price;
  }
  public void setCoursePrice(Integer course_price) {
    this.course_price = course_price;
  }

  /* course difficulty level */
  public String getCourseDifficultyLevel() {
    return course_difficulty_level;
  }
  public void setCourseDifficultyLevel(String course_difficulty_level) {
    this.course_difficulty_level = course_difficulty_level;
  }

  /* course duration */
  public Double getCourseDuration() {
    return course_estimated_duration;
  }
  public void setCourseDuration(Double course_estimated_duration) {
    this.course_estimated_duration = course_estimated_duration;
  }

  public int getLearner_enrolled() {
    return learner_enrolled;
  }
  public void setLearner_enrolled(int learner_enrolled) {
    this.learner_enrolled = learner_enrolled;
  }

}
