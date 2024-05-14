package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item {

	@Id
	@GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;

	private String name;
	private int price;
	private int stackQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStackQuantity() {
		return stackQuantity;
	}

	public void setStackQuantity(int stackQuantity) {
		this.stackQuantity = stackQuantity;
	}
}
