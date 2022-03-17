package net.javaguides.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.RoleRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserRepository userRepository;
    
	@Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> getAlUsers() {
		return userRepository.findAll();
	}
	
	public void deleteUser(String id) {
		userRepository.deleteById(id);
	}
	
	public User getUser(String id) {
		System.out.println("id " + id);
		Optional<User> e = userRepository.findById(id);
		if(e.isPresent()) {
			return  e.get();
		} 
		return null;
	}
	
	public User updateUser(User u) {
		System.out.println(u.getEmail());
		userRepository.save(u);
		return null;
	}
	
	
	@Override
	public User save(UserRegistrationDto registrationDto) {
//		User user = new User();
		
		//หาจาก role
		Role userRole = roleRepository.findByRole("ADMIN");
//		
//		user.setEmail(registrationDto.getEmail());
//		user.setFirstname(registrationDto.getFirstName());
//		user.setLastname(registrationDto.getLastName());
//		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
//		System.out.println("getEmail " + user.getEmail());
//		System.out.println("getFirstName " + user.getFirstname());
//		System.out.println("getLastName " + user.getLastname());
//		System.out.println("getPassword " + user.getPassword());
//		System.out.println(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
//		
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				bCryptPasswordEncoder.encode(registrationDto.getPassword()), new HashSet<>(Arrays.asList(userRole)));

		
//		User user = new User(registrationDto.getFirstName(), 
//				registrationDto.getLastName(), registrationDto.getEmail(),
//				bCryptPasswordEncoder.encode(registrationDto.getPassword()), new HashSet<>(Arrays.asList(new Role("ADMIN"))));
		
        
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}
	
}
