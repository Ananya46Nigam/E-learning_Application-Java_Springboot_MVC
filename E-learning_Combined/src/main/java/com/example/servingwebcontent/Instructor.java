package com.example.servingwebcontent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "INSTRUCTOR",schema = "elearning")
public class Instructor 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer id;

    @Column(name = "username")
    public String username;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "email_id")
    public String email_id;

    @Column(name = "password")
    private String password;

    @Column(name = "contact_no")
    public String contact_no;

    @Column(name = "course_id")
    public Integer course_id;

    public Integer getId() 
    {
      return id;
    }
  
    public void setId(Integer id) {
      this.id = id;
    }
  
    public String getUsername()
    {
      return username;
    }
  
    public void setUsername(String username)
    {
      this.username = username;
    }
  
    public String getFirstname() {
      return firstname;
    }
  
    public void setFirstname(String firstname) 
    {
      this.firstname = firstname;
    }
  
    public String getLastname()
    {
      return lastname;
    }
  
    public void setLastname(String lastname) {
      this.lastname = lastname;
    }
  
    public String getEmail_id() 
    {
      return email_id;
    }
    
    public void setEmail_id(String email_id) 
    {
      this.email_id = email_id;
    }

    public String getPassword()
    {
      return password;
    }
      
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getContact_no()
    {
      return contact_no;
    }
    
    public void setContact_no(String contact_no)
    {
      this.contact_no = contact_no;
    }
    
    public Integer getCourse_id()
    {
      return course_id;
    }
    
    public void setCourse_id(Integer course_id)
    {
      this.course_id = course_id;
    }
  
}

