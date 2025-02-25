package com.nttdata.task_management_app.repositories;

import com.nttdata.task_management_app.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByEmail(String email);
}
