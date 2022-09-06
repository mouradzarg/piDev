package tn.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.spring.entity.Article;
import tn.spring.entity.vote;
@Repository
public interface voterepo extends JpaRepository<vote, Long>{

}
