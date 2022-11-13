package com.example.jonebook.repositories;

import com.example.jonebook.entities.WorkPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkPostRepository extends JpaRepository<WorkPost, Long>, JpaSpecificationExecutor<WorkPost> {
    Optional<WorkPost> findByName(String name);

    List<WorkPost> findByNameStartingWithOrderByName(String namePrefix);
}
