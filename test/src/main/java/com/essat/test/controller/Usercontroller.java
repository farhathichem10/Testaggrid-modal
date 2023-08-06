package com.essat.test.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.essat.test.entity.User;
import com.essat.test.repository.UserRepository;

@RequestMapping("/api")
@CrossOrigin("*")
@RestController
public class Usercontroller {
	@Autowired UserRepository uss;
	
	
	@GetMapping("/getall")
	public List<User> getall(){
		
		return uss.findAll();
	}
	@GetMapping("/getbyid/{id}")
	public Optional<User> getbyid(@PathVariable("id")int id) {
		
		return uss.findById(id);
		
		
		
	}
	@PostMapping("/save")
	public void save(@RequestBody List <User> us) {
		uss.saveAll(us);
	}
	@PostMapping("/saveone")
	public void saveone(@RequestBody User us) {
		uss.save(us);
	}
	//u.datedeb=:x and u.datefin=:y and u.id=:r
	@GetMapping("/getttt")
	public User gett(@RequestParam("datedeb")String datedeb,@RequestParam("datefin")String datefin,@RequestParam("id")int id ) {
		
		return uss.ccccc(datedeb,datefin,id);
		
		
		
	}
	

}
