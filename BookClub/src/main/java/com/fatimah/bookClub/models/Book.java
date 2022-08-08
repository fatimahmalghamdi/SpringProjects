package com.fatimah.bookClub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="book")
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message = "The UserName shouldn't be Empty")
    @Size(min = 3, max = 200, message = "The UserName shouldn't be less than 3 characters")
    private String title;
	
    @NotEmpty(message = "The UserName shouldn't be Empty")
    @Size(min = 3, max = 200, message = "The UserName shouldn't be less than 3 characters")
    private String authorName;
    
    
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
	
	
	//RelationShip is ManyToOne: Many books are added by One user:
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User creatorUser;
	
	// RelationShip: OneToMany: one book has multiple thoughts:
	@JsonManagedReference
	@OneToMany(mappedBy = "book")
	private List<Thoughts> bookThoughts;

	

	//constructor + getters and setters:
	public Book() {
		//
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getCreatorUser() {
		return creatorUser;
	}
	public void setCreatorUser(User creatorUser) {
		this.creatorUser = creatorUser;
	}
	public List<Thoughts> getBookThoughts() {
		return bookThoughts;
	}
	public void setBookThoughts(List<Thoughts> bookThoughts) {
		this.bookThoughts = bookThoughts;
	}
	



}
