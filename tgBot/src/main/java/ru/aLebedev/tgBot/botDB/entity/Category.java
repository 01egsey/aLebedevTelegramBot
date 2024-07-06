package ru.aLebedev.tgBot.botDB.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	@ManyToOne
	private Category parent;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return this.parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

}
