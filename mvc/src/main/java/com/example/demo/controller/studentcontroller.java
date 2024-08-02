package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.studentmodel;
import com.example.demo.repositary.studentrepositary;

import jakarta.servlet.http.HttpSession;

@Controller
public class studentcontroller {
	
	@Autowired
	studentrepositary repo ;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	
	@GetMapping("/")
//	model is a keyword  in mvc
	public String home(Model m) {
		
		Iterable<studentmodel> sm =repo.findAll();
		m.addAttribute("add-products",sm);
		return "home";
	
	}
	
	@GetMapping("/getbyid/{id}")
	public String getbyid( @PathVariable(value="id") int id,Model m) {
		
		Optional<studentmodel> sm = repo.findById(id);
		
		studentmodel s = sm.get();
		
		m.addAttribute("products",m);
		
		return "edit";
		
	}
	
	@PostMapping("/insertion")
//	httpsession sends data to frontend this is jstl 
	public String insert(@ModelAttribute studentmodel sm,HttpSession hs) {
		
		repo.save(sm);
		
		hs.setAttribute("message", "sucessfully added");
		
//		the data send to load
		return "redirect:/loadform";
	}
	@PutMapping("/updated")
	public String edit(@ModelAttribute studentmodel sm,HttpSession hs) {
		
		repo.save(sm);
		
		hs.setAttribute("message","sucessfully updated");
		
		return "redirect:/";
		
	}
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") int id,HttpSession hs) {
		
		repo.deleteById(id);
		
		hs.setAttribute("message", "sucessfully deleted");
		
		return "redirect:/";
	}
	
	
	@GetMapping("/loadform")
	public String loadform() {
		return "add";
	}
	

}
