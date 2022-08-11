package com.osyrs.sfgpetclinic.services.map;

import com.osyrs.sfgpetclinic.model.Visit;
import com.osyrs.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    private final VisitService visitService;

    public VisitMapService(VisitService visitService) {
        this.visitService = visitService;
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit vet) {
        return super.save(vet);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
