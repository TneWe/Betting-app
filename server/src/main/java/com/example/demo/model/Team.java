package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "ime")
	private String name;

	public Team() {

	}

	public Team(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
