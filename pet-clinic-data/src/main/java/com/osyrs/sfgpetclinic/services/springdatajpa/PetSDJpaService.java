package com.osyrs.sfgpetclinic.services.springdatajpa;

import com.osyrs.sfgpetclinic.model.Pet;
import com.osyrs.sfgpetclinic.repositories.PetRepository;
import com.osyrs.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {
    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        return StreamSupport.stream(petRepository.findAll()
                        .spliterator(), true)
                .collect(Collectors.toSet());
    }

    @Override
    public Pet findById(Long id) {
        var pet = petRepository.findById(id);
        return pet.orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
