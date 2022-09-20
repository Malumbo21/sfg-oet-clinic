package com.osyrs.sfgpetclinic.services.springdatajpa;

import com.osyrs.sfgpetclinic.model.Owner;
import com.osyrs.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    private static final String LAST_NAME = "Sinkamba";
    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerSDJpaService service;
    Owner returnedOwner;
    @BeforeEach
    void setUp() {
        returnedOwner=Owner.builder()
                .id(1L)
                .lastName(LAST_NAME)
                .build();
    }

    @Test
    void findAll() {
        var returnOwnersSet = new HashSet<Owner>();
        returnOwnersSet.add(Owner.builder().id(1L).build());
        returnOwnersSet.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);
        var owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2,owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong()))
                .thenReturn(Optional.of(returnedOwner));
        var owner = service.findById(1L);
        assertNotNull(owner);
    }
    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        var owner = service.findById(1L);
        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any()))
                .thenReturn(returnedOwner);
        var savedOwner = service.save(returnedOwner);
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        service.delete(returnedOwner);
        verify(ownerRepository)
                .delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        var returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
        when(service.findByLastName(any()))
                .thenReturn(returnOwner);
        var smith = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, returnOwner.getLastName());
        verify(ownerRepository).findByLastName(any());
    }
}
