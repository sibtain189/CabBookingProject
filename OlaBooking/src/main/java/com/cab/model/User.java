package com.cab.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ColumnTransformer;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@Pattern(regexp = "^[a-zA-Z]{3,25}", message = "length must be >=3")
	@Column(unique = true)
	private String username;

	
	private String name;

	@Pattern(regexp = "^[a-zA-Z0-9]{8,20}", message = "length must be >=8")
	@Column(nullable = false)
	@ColumnTransformer(write = "HEX(AES_ENCRYPT(?, 'secret-key'))", read = "CAST(AES_DECRYPT(UNHEX(password), 'secret-key') as CHAR)")
	private String password;

	@Email
	private String email;

	@Pattern(regexp = "[0-9]{10}", message = "Mobile number must have 10 digits")
	private String mobileNo;

	@ElementCollection
	private List<UserRoles> roles = null;

}
