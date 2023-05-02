package com.spring.practical.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Transient
	private String conPassword;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "token")
	private String token;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime tokenCreationDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createOn", insertable = true)
	private Date createOn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateOn", updatable = true)
	private Date updateOn;
	
	@Column(name = "updateBy", updatable = true)
	private int updateBy;
	
	@ManyToMany(cascade=CascadeType.DETACH, fetch=FetchType.LAZY)
	@JoinTable(name = "user_role", 
	joinColumns = { @JoinColumn(name = "user_id",  nullable = false) }, 
	inverseJoinColumns = { @JoinColumn(name = "role_id",  nullable = false) })
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
	private List<Address> address;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
	public String getConPassword() {
		return conPassword;
	}
	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}
	
	public Set<Role> getRoles() {
		return new HashSet<Role>(this.roles);
	}
	public void setRoles(Set<Role> roles) {
		this.roles = new HashSet<Role>(roles);
	}
	
	public List<Address> getAddress() {
		return new ArrayList<Address>(this.address) ;
	}
	public void setAddress(List<Address> address) {
		this.address = new ArrayList<Address>(address);
	}
	
	public Date getCreateOn() {
		return new Date(createOn.getTime());
	}
	public void setCreateOn(Date createOn) {
		this.createOn = new Date(createOn.getTime());
	}
	public Date getUpdateOn() {
		return new Date(updateOn.getTime());
	}
	public void setUpdateOn(Date updateOn) {
		this.updateOn = new Date(updateOn.getTime());
	}
	public int getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getTokenCreationDate() {
		return tokenCreationDate;
	}
	public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
		this.tokenCreationDate = tokenCreationDate;
	}
	
	public void addRole(Role role) {
        this.roles.add(role);
	}
	
	private static Map<String, String> genderOptions = new HashMap<String, String>();
	
	public static Map<String, String> getGenderOptions() {
        if(genderOptions.size() == 0) {
        	genderOptions.put("M", "Male");
        	genderOptions.put("F", "Female");
        }
        return new HashMap<String, String>(genderOptions);
	}
}