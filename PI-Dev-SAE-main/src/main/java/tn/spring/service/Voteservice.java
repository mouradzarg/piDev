package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.entity.AppUser;
import tn.spring.entity.Article;
import tn.spring.entity.vote;
import tn.spring.repository.ArticleRepository;
import tn.spring.repository.UserRepository;
import tn.spring.repository.voterepo;

@Service
public class Voteservice {

	@Autowired
	voterepo vr;
	  @Autowired
		UserRepository userRepository ;
	    @Autowired
	     ArticleRepository articleRepository;
	    public void add(vote v,int ida,String email) {
	    	
	    	Long id =userRepository.jibid(email);
	    	AppUser u= userRepository.findById(id).orElse(null);
	    	Article a = articleRepository.findById(ida).get();
	    	v.setArticle(a);
	    	v.setUser(u);
	    	vr.save(v);
	    }
	    
}
