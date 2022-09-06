package tn.spring.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private int id;
    private String title;
    private String subject_article;
   
    private String content;
    private String auteur;
    @JsonIgnore
    @ManyToOne
   	private AppUser appuser;
    @JsonIgnore
    @OneToMany (mappedBy = "article", cascade=CascadeType.REMOVE)
	public List<vote> votes;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject_article() {
        return subject_article;
    }

    public void setSubject_article(String subject_article) {
        this.subject_article = subject_article;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

  


    public void setId(int id) {
        this.id = id;
    }

    public Article() {
    }

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}

	public List<vote> getVotes() {
		return votes;
	}

	public void setVotes(List<vote> votes) {
		this.votes = votes;
	}


}
