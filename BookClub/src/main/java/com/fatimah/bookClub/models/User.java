package com.fatimah.bookClub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "The UserName shouldn't be Empty")
    @Size(min = 3, max = 200, message = "The UserName shouldn't be less than 3 characters")
    private String username;
    @Email(message = "The Email format has error, please write a valid email syntax")
    private String email;
    @NotEmpty(message = "The Pawword shouldn't be Empty")
    private String password;
    @Transient
    private String confirmPassword;
    
 // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    @PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	
	// RelationShip: OneToMany: one user can add multiple books:
	@JsonManagedReference
	@OneToMany(mappedBy = "creatorUser")
	private List<Book> postedBooks;
	
	
	
	// RelationShip: OneToMany: one user can add multiple thoughts:
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Thoughts> userThoughts;
	
	

	//constructor + getters and setters:
	public User() {
		//
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Book> getPostedBooks() {
		return postedBooks;
	}
	public void setPostedBooks(List<Book> postedBooks) {
		this.postedBooks = postedBooks;
	}
	
	


}
