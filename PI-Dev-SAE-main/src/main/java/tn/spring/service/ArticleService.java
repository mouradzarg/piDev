package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.spring.entity.AppUser;
import tn.spring.entity.Article; 
import tn.spring.exception.ArticleNotFoundException;
import tn.spring.repository.ArticleRepository; 
import tn.spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class ArticleService {
 

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
	UserRepository userRepository ;
    @Autowired
    private ArticleRepository articleRepository;

   


    public void addArticle(Article article , String email){
    	
    	Long id =userRepository.jibid(email);
    	AppUser u= userRepository.findById(id).orElse(null);
    article.setAppuser(u);
    article.setAuteur(u.getFirstName());
         articleRepository.save(article);
    }

    


    public List<Article> findAllArticles(){
        return articleRepository.findAll();
    }
    public List<Article> findAllArticles(String email){
    	Long id =userRepository.jibid(email);
AppUser u= 	userRepository.findById(id).get();
List<Article> ar = new ArrayList<>();
for(Article e:articleRepository.findAll())
{
	if (e.getAppuser()==u)
	{
		ar.add(e);
	}
}
        return ar ;
    }
   

    public Article updateArticle(Article article,int id) {
    Article a=	articleRepository.findById(id).get();
    a.setContent(article.getContent());
    a.setSubject_article(article.getSubject_article());
    a.setTitle(article.getTitle());
        return  articleRepository.save(a);
    }

    public Article findArticleById(int article_id){
        return articleRepository.findById(article_id).get();}

    public void deleteArticle(int article_id){
        articleRepository.deleteById(article_id);
    }
}
