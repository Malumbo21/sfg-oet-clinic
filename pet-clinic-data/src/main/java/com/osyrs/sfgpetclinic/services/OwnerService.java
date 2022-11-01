package com.osyrs.sfgpetclinic.services;

import com.osyrs.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String surname);
    List<Owner> findAllByLastNameLike(String lastName);
}
