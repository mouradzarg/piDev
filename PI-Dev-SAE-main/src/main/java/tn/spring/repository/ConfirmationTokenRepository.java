package tn.spring.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.spring.entity.ConfirmationToken;
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{
Optional<ConfirmationToken > findByToken(String Token);
@Transactional
@Modifying
@Query("UPDATE ConfirmationToken c " +
        "SET c.confirmedAt = ?2 " +
        "WHERE c.token = ?1")
int updateConfirmedAt(String token,
                      LocalDateTime confirmedAt);

}
