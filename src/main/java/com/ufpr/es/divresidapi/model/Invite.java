package com.ufpr.es.divresidapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "invites")
public class Invite implements Serializable{
	
	private static final long serialVersionUID = -9041539002950289221L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id_from")
	private User idFrom;
	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id_to")
	private User idTo;
	@NotNull
	@ManyToOne
	@JoinColumn(name="property_id")
	private Property idProperty;
	private boolean accepted;
	
	public Invite() {}

	public Invite(Long id, User idFrom, User idTo, Property idProperty, boolean accepted) {
		super();
		this.id = id;
		this.idFrom = idFrom;
		this.idTo = idTo;
		this.idProperty = idProperty;
		this.accepted = accepted;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getIdFrom() {
		return idFrom;
	}
	public void setIdFrom(User idFrom) {
		this.idFrom = idFrom;
	}
	public User getIdTo() {
		return idTo;
	}
	public void setIdTo(User idTo) {
		this.idTo = idTo;
	}
	public Property getIdProperty() {
		return idProperty;
	}
	public void setIdProperty(Property idProperty) {
		this.idProperty = idProperty;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAcceted(boolean accepted) {
		this.accepted = accepted;
	}
	
}
