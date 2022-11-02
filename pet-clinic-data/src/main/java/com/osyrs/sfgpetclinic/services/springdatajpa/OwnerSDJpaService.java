package com.osyrs.sfgpetclinic.services.springdatajpa;

import com.osyrs.sfgpetclinic.model.Owner;
import com.osyrs.sfgpetclinic.repositories.OwnerRepository;
import com.osyrs.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {
        return StreamSupport.stream(ownerRepository.findAll()
                .spliterator(),true)
                .collect(Collectors.toSet());
    }

    @Override
    public Owner findById(Long id) {
        var owner = ownerRepository.findById(id);
        return owner.orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
    ownerRepository.deleteById(id);
    }

    @Override
    public Owner findByLastName(String surname) {
        return ownerRepository.findByLastName(surname);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findByLastNameLike(lastName);
    }
}
