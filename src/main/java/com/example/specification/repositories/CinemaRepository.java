package com.example.specification.repositories;

import com.example.specification.domains.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema,Long>, JpaSpecificationExecutor<Cinema> {
}
