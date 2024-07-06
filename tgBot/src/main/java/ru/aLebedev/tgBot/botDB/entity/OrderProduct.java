package ru.aLebedev.tgBot.botDB.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderProduct {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private ClientOrder clientOrder;

	@ManyToOne(optional = false)
	private Product product;

	@Column(nullable = false)
	private Integer countProduct;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientOrder getClientOrder() {
		return this.clientOrder;
	}

	public void setClientOrder(ClientOrder clientOrder) {
		this.clientOrder = clientOrder;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCountProduct() {
		return this.countProduct;
	}

	public void setCountProduct(Integer countProduct) {
		this.countProduct = countProduct;
	}

}
