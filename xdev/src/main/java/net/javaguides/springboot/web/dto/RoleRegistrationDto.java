package net.javaguides.springboot.web.dto;

public class RoleRegistrationDto {
	private String role;
	
	public RoleRegistrationDto() {
	}


	public RoleRegistrationDto(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
