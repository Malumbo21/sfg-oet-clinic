package com.osyrs.sfgpetclinic.services;

import com.osyrs.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findBySurname(String surname);
}
