package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.repository.RoleRepository;

@Repository
public class RoleService {
	
	@Autowired
	private RoleRepository repo;
	
	public void addRole(Role e) {
		repo.save(e);
	}
	
	public List<Role> getAllRole() {
		return repo.findAll();
	}
	
	public void deleteRole(String role) {
		repo.deleteById(role);
	}
	
	public Role getRole(String id) {
		System.out.println("id " + id);
		Optional<Role> e = repo.findById(id);
		if(e.isPresent()) {
			return  e.get();
		} 
		return null;
	}
}
