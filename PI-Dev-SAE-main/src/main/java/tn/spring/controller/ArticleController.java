package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.entity.Article;
import tn.spring.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("api/v1/registartion/article")

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {
    @Autowired
	ArticleService articleService;

 

    @GetMapping("/all")
    public ResponseEntity<List<Article>> getAllArticles () {
        List<Article> articles = articleService.findAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
    @GetMapping("/all/{email}")
    public List <Article> getAllArticles (@PathVariable("email") String email) {
        
        return articleService.findAllArticles(email);
    }

    @GetMapping("/find/{article_id}")
    public Article getArticleById (@PathVariable("article_id") int article_id) {
        Article article = articleService.findArticleById(article_id);
      return article;
    }

    @PostMapping("/add/{email}")
    public void addArticle(@RequestBody Article article , @PathVariable("email") String email) {
         articleService.addArticle(article,email); 
    }

    @PutMapping("/update/{id}")
    public void updateArticle(@RequestBody Article article,@PathVariable("id") int id ) {
         articleService.updateArticle(article,id);
    }
    

    @DeleteMapping("/delete/{article_id}")
    public void deleteArticle(@PathVariable("article_id") int article_id) {
        articleService.deleteArticle(article_id);
    }
}

