package com.nttdata.task_management_app.service;

import com.nttdata.task_management_app.domain.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> findAllOwners();
    Owner saveOwner(Owner owner);
}
