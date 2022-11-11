package com.osyrs.sfgpetclinic.services.map;

import com.osyrs.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {
    PetMapService petMapService;
    private final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        var petSet = petMapService.findAll();
        assertEquals(petSet.size(), 1);
    }
    @Test
    void finByIdExistingId(){
        var pet = petMapService.findById(petId);
        assertEquals(petId,pet.getId());
    }

    @Test
    void findByIdNotExistingId(){
        var pet = petMapService.findById(5L);
        assertNull(pet);
    }

    @Test
    void findByIdNullId(){
        var pet = petMapService.findById(null);
        assertNull(pet);
    }

    @Test
    void saveExistingId(){
        Long id = 2L;
        var pet2 = Pet.builder().id(id).build();
        var savedPet = petMapService.save(pet2);
        assertEquals(id,savedPet.getId());
    }

    @Test
    void saveDuplicateId(){
        var pet2 = Pet.builder().id(petId).build();
        var savedPet = petMapService.save(pet2);
        assertEquals(petId,savedPet.getId());
        assertEquals(1,petMapService.findAll().size());
    }
    @Test
    void saveNoId(){
        var savedPet = petMapService.save(Pet.builder().build());
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2,petMapService.findAll().size());
    }
    @Test
    void deletePet(){
        petMapService.delete(petMapService.findById(petId));
        assertEquals(0,petMapService.findAll().size());
    }

    @Test
    void deleteWithWrongId(){
        var pet = Pet.builder().id(5L).build();
        petMapService.delete(pet);
        assertEquals(1,petMapService.findAll().size());
    }

    @Test
    void deleteWithNullId(){
        var pet = Pet.builder().build();
        petMapService.delete(pet);
        assertEquals(1,petMapService.findAll().size());
    }
    @Test
    void deleteNull(){
        petMapService.delete(null);
        assertEquals(1,petMapService.findAll().size());
    }

    @Test
    void deleteByIdCorrectId(){
        petMapService.deleteById(petId);
        assertEquals(0,petMapService.findAll().size());
    }
    @Test
    void deleteByIdWrongId(){
        petMapService.deleteById(5L);
        assertEquals(1,petMapService.findAll().size());
    }
    @Test
    void deleteByIdNullId(){
        petMapService.deleteById(null);
        assertEquals(1,petMapService.findAll().size());
    }
}