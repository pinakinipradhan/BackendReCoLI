package com.example.demo.com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LeaderBoard")
public class LeaderBoard {
	
	@Id
	@GeneratedValue
	private int id;
	private String uid;
	private int points;
	
	public LeaderBoard() {
		
	}

	public LeaderBoard(String uid, int points) {
		this.uid = uid;
		this.points = points;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	

}
