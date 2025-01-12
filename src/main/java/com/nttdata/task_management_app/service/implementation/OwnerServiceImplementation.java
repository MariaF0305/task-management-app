package com.nttdata.task_management_app.service.implementation;

import com.nttdata.task_management_app.domain.Owner;
import com.nttdata.task_management_app.repositories.OwnerRepository;
import com.nttdata.task_management_app.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImplementation implements OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerServiceImplementation(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }
}
