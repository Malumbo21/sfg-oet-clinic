package com.osyrs.sfgpetclinic.services.map;

import com.osyrs.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {
    OwnerMapService ownerMapService;
    final Long ownerId=1L;
    final String lastName="Sinkamba";
    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(),new PetMapService());
        ownerMapService.save(Owner.builder()
                .id(ownerId)
                .lastName(lastName)
                .build()
        );
    }

    @Test
    void findAll() {
        var owners = ownerMapService.findAll();
        assertEquals(1,owners.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0,ownerMapService.findAll().size());
    }

    @Test
    void findById() {
        var owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId,owner.getId());
    }

    @Test
    void saveNoId() {
        var owner = Owner.builder().build();
        var savedOwner = ownerMapService.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findByLastName() {
        var owner = ownerMapService.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(ownerId,owner.getId());
    }
    @Test
    void findByLastNameNotFound() {
        var owner = ownerMapService.findByLastName("Mutemwa");
        assertNull(owner);
    }
}
