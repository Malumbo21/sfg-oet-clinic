package com.osyrs.sfgpetclinic.repositories;

import com.osyrs.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
