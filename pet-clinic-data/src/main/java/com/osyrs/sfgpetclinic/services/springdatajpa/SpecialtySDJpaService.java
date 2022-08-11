package com.osyrs.sfgpetclinic.services.springdatajpa;

import com.osyrs.sfgpetclinic.model.Specialty;
import com.osyrs.sfgpetclinic.repositories.SpecialtyRepository;
import com.osyrs.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtySDJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        return StreamSupport.stream(specialtyRepository.findAll()
                        .spliterator(), true)
                .collect(Collectors.toSet());
    }

    @Override
    public Specialty findById(Long id) {
        var specialty = specialtyRepository.findById(id);
        return specialty.orElse(null);
    }

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Specialty specialty) {
        specialtyRepository.delete(specialty);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
