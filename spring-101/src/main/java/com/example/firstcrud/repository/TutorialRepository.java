package com.example.firstcrud.repository;

import com.example.firstcrud.domain.Tutorial;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, UUID> {
  Page<Tutorial> findByPublished(boolean published, Pageable pageable);

  Page<Tutorial> findByTitleContaining(String title, Pageable pageable);

  @Transactional
  void deleteAllByCreatedAtBefore(Date date);
  // @Query("SELECT t FROM tutorials t WHERE t.createdAt >= ?1")
  // Page<Tutorial> findByDateGreaterThanEqual(Date date, Pageable pageable);
}
