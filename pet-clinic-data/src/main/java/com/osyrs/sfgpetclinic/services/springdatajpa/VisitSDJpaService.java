package com.osyrs.sfgpetclinic.services.springdatajpa;

import com.osyrs.sfgpetclinic.model.Visit;
import com.osyrs.sfgpetclinic.repositories.VisitRepository;
import com.osyrs.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {
    private final VisitRepository visitRepository;

    public VisitSDJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        return StreamSupport.stream(visitRepository.findAll()
                        .spliterator(), true)
                .collect(Collectors.toSet());
    }

    @Override
    public Visit findById(Long id) {
        var visit = visitRepository.findById(id);
        return visit.orElse(null);
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
