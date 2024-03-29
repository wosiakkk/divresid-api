package com.ufpr.es.divresidapi.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User implements Serializable{
	
	private static final long serialVersionUID = 398440705996215873L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 20, message = "Máximo 20 caracteres!")
	private String username;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 50, message = "Máximo 50 caracteres!")
	@Email
	private String email;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 120, message = "Máximo 120 caracteres!")
	private String password;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 50, message = "Máximo 50 caracteres!")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(
			@NotBlank String username, @NotBlank @Email String email, 
			@NotBlank String password, @NotBlank String name
		) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public User(
			Long id, @NotBlank String username,  
			@NotBlank @Email String email, @NotBlank String name
		) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.name = name;
	}
	
	public User(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
