package com.example.demo.com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Files")
public class Files {
	
	@Id
	@GeneratedValue
	
	private int cid;
	private String email;
	private String component;
	private String tag;
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	

	public Files() {
	}



	public Files(String email, String component, String tag, String name, String description) {
		this.email = email;
		this.component = component;
		this.tag = tag;
		this.name = name;
		this.description = description;
	}



	public int getCid() {
		return cid;
	}



	public void setCid(int cid) {
		this.cid = cid;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getComponent() {
		return component;
	}



	public void setComponent(String component) {
		this.component = component;
	}



	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	

}
