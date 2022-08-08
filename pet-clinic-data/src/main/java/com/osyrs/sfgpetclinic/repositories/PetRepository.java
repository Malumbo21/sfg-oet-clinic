package com.osyrs.sfgpetclinic.repositories;

import com.osyrs.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet,Long> {
}
