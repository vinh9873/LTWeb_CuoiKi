package vn.ute.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private String type;

	@Column
	private float price;

	@Column
	private String image;

	@Column
	private String description;

	@Column
	private Integer soldNumber;

	public void increaseSoldNumber() {
		if (this.soldNumber == null) {
			this.soldNumber = 0;
		}
		this.soldNumber++;
	}

	public Integer getSoldNumber() {
		if (soldNumber == null) {
			return 0;
		}
		return soldNumber;
	}
}