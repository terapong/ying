package net.javaguides.springboot.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.RoleService;
//import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.service.UserServiceImpl;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@Controller
public class MainController {
	
	@Autowired
	private RoleService roleService;
//	@Autowired
//	private UserService userService;
	@Autowired
	private UserServiceImpl userServiceImp;
	
	@GetMapping("/project_1")
	public String project_1(Model m) {
		List<User> emp = userServiceImp.getAlUsers();
		m.addAttribute("emp", emp);
		return "project_1";
	}
	
	@GetMapping("/project_2")
	public String project_2(Model m) {
		List<User> emp = userServiceImp.getAlUsers();
		m.addAttribute("emp", emp);
		return "project_2";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/user_all")
	public String user_all(Model m) {
		List<User> emp = userServiceImp.getAlUsers();
		m.addAttribute("emp", emp);
		return "user";
	}
	
	@PostMapping("/user_add")
	public String user_add(@ModelAttribute User e, Model m, HttpSession session) {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		userRegistrationDto.setEmail(e.getEmail());
		userRegistrationDto.setFirstName(e.getFirstname());
		userRegistrationDto.setLastName(e.getLastname());
		userRegistrationDto.setPassword(e.getPassword());
		
		userServiceImp.save(userRegistrationDto);
		List<User> emp = userServiceImp.getAlUsers();
		m.addAttribute("emp", emp);
		session.setAttribute("msg", "User Added Sucessfully..");
		return "user";
	}
	
	@GetMapping("/user_delete/{id}")
	public String user_delete(@PathVariable String id, Model m, HttpSession session) {
		System.out.println("id " + id);
		userServiceImp.deleteUser(id);

		List<User> emp = userServiceImp.getAlUsers();
		m.addAttribute("emp", emp);
		session.setAttribute("msg", "User Delete Sucessfully..");
		return "user";
	}
	
	@GetMapping("/user_edit/{id}")
	public String user_edit(@PathVariable String id, Model m, HttpSession session) {
		System.out.println("id " + id);
		User e = userServiceImp.getUser(id);
		m.addAttribute("emp", e);
		return "user_edit";
	}
	
	@PostMapping("/user_update")
	public String user_update(@ModelAttribute User e, Model m, HttpSession session) {
		System.out.print(e);
		userServiceImp.updateUser(e);
		
		List<User> emp = userServiceImp.getAlUsers();
		m.addAttribute("emp", emp);
		session.setAttribute("msg", "User Update Sucessfully..");
		return "user";
	}
	
	@GetMapping("/role")
	public String role() {
		return "role";
	}
	
	@GetMapping("/role_all")
	public String role_all(Model m) {
		List<Role> emp = roleService.getAllRole();
		m.addAttribute("emp", emp);
		return "role";
	}
	
	@PostMapping("/role_add")
	public String role_add(@ModelAttribute Role e, Model m, HttpSession session) {
		roleService.addRole(e);

		List<Role> emp = roleService.getAllRole();
		m.addAttribute("emp", emp);
		session.setAttribute("msg", "Role Added Sucessfully..");
		return "role";
	}
	
	@GetMapping("/role_delete/{id}")
	public String role_delete(@PathVariable String id, Model m, HttpSession session) {
		System.out.println("id " + id);
		roleService.deleteRole(id);

		List<Role> emp = roleService.getAllRole();
		m.addAttribute("emp", emp);
		session.setAttribute("msg", "Role Delete Sucessfully..");
		return "role";
	}
	
	@GetMapping("/role_edit/{id}")
	public String role_edit(@PathVariable String id, Model m, HttpSession session) {
		System.out.println("id " + id);
		Role e = roleService.getRole(id);
		m.addAttribute("emp", e);
		return "role_edit";
	}
	
	@PostMapping("/role_update")
	public String role_update(@ModelAttribute Role e, Model m, HttpSession session) {
		System.out.print(e);
		roleService.addRole(e);

		session.setAttribute("msg", "Role Update Sucessfully..");
		List<Role> emp = roleService.getAllRole();
		m.addAttribute("emp", emp);
		return "role";
	}
	
    @GetMapping("/info")
    public String getInfo(Model model){
        model.addAttribute("activePage", "info");
        return "info";
    }

    @GetMapping("/contact")
    public String getContact(Model model){
        model.addAttribute("activePage", "contact");
        return "contact";
    }
}
