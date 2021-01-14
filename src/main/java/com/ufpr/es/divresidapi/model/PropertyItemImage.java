package com.ufpr.es.divresidapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "property_item_img")
public class PropertyItemImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "image_text")
	private String base64Image;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
    	name="property_item_id"
    )
	@JsonBackReference
	private PropertyItem propertyItem;
	
	
	
	public PropertyItemImage(Long id, String base64Image,
			PropertyItem propertyItem) {
		super();
		this.id = id;
		this.base64Image = base64Image;
		this.propertyItem = propertyItem;
	}

	public PropertyItemImage() {}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public PropertyItem getPropertyItem() {
		return propertyItem;
	}

	public void setPropertyItem(PropertyItem propertyItem) {
		this.propertyItem = propertyItem;
	}

}
