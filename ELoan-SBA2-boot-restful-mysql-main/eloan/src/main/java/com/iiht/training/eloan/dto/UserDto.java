package com.iiht.training.eloan.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iiht.training.eloan.exception.InvalidDataException;

public class UserDto {

	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonProperty(value = "firstName")
	@NotNull(message = "firstName cannot be null")
	@Size(min = 3, max = 100, message = "firstName length should be in between 3 characters to 100 characters")
	private String firstName;

	@JsonProperty(value = "lastName")
	@NotNull(message = "lastName cannot be null")
	@Size(min = 3, max = 100, message = "lastName length should be in between 3 characters to 100 characters")
	private String lastName;

	@JsonProperty(value = "email")
	@NotNull(message = "email is requried")
	@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
	private String email;

	@Column(name = "mobile")
	@NotNull(message = "Mobile number is requried")
	@Pattern(regexp = "[1-9][0-9]{9}", message = "Mobile Number should be of 10 digits only")
	private String mobile;

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(Long id, String firstName, String lastName, String email, String mobile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	// User Details validation
	public boolean isFirstNameValid(UserDto userDto) throws InvalidDataException {
		boolean isValid = false;
		if (userDto.getFirstName() != null && userDto.getFirstName().length() > 2
				&& userDto.getFirstName().length() < 101) {
			isValid = true;
		} else {
			throw new InvalidDataException("FirstName should be not null and must be min 3 and max 100 characters.");
		}
		return isValid;
	}

	public boolean isLastNameValid(UserDto userDto) throws InvalidDataException {
		boolean isValid = false;
		if (userDto.getLastName() != null && userDto.getLastName().length() > 2
				&& userDto.getLastName().length() < 101) {
			isValid = true;
		} else {
			throw new InvalidDataException("LastName should be not null and must be min 3 and max 100 characters.");
		}
		return isValid;
	}

	public boolean isEmailValid(UserDto userDto) throws InvalidDataException {
		boolean isValid = false;
		if (userDto.getEmail() != null
				&& userDto.getEmail().matches("[a-zA-Z][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
			isValid = true;
		} else {
			throw new InvalidDataException(
					"Email should not be null and should be unique and must be of proper format");
		}
		return isValid;
	}

	public boolean isMobileValid(UserDto userDto) throws InvalidDataException {
		boolean isValid = false;
		if (userDto.getMobile() != null && userDto.getMobile().matches("[1-9][0-9]{9}")) {
			isValid = true;
		} else {
			throw new InvalidDataException("Mobile number should be unique, not null and must be of 10 digits");
		}
		return isValid;
	}

	public boolean isUserValid(UserDto userDto) throws InvalidDataException {
		boolean isValid = false;
		if (isFirstNameValid(userDto) && isLastNameValid(userDto) && isEmailValid(userDto) && isMobileValid(userDto)) {
			isValid = true;
		} else {
			throw new InvalidDataException("User Details provided are invalid");
		}
		return isValid;
	}
}
