package com.nttdata.task_management_app.repositories;

import com.nttdata.task_management_app.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
