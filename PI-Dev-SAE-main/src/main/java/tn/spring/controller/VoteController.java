package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.spring.entity.Article;
import tn.spring.entity.vote;
import tn.spring.service.Voteservice;

@RestController

@RequestMapping("api/v1/registartion/vote")

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VoteController {
	
@Autowired 
Voteservice vs ;
@PostMapping("/add/{id}/{email}")
public void addArticle(@RequestBody vote v, @PathVariable("id") int id, @PathVariable("email") String email) {
      vs.add(v, id, email);
}
}
