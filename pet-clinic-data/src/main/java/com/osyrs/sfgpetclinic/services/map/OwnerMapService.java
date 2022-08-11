package com.osyrs.sfgpetclinic.services.map;

import com.osyrs.sfgpetclinic.model.Owner;
import com.osyrs.sfgpetclinic.services.OwnerService;
import com.osyrs.sfgpetclinic.services.PetService;
import com.osyrs.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner owner) {
        if (owner != null) {
            if (owner.getPets() != null) {
                owner.getPets().forEach(pet -> {
                    if(pet.getPetType()==null)
                        throw new RuntimeException("Pet Type is required");

                    if (pet.getPetType().getId() != null)
                        pet.setPetType(petTypeService.save(pet.getPetType()));

                    if(pet.getId()==null){
                        var savedPet=petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(owner);
        } else {
            return null;
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String surname) {
        return null;
    }
}
