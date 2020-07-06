package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJpaTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceJpa ownerService;

    Owner testOwner = Owner.builder().id(1L).firstName("Johnny").lastName("Apple").build();
    String testName = testOwner.getLastName();

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastNameExists() {

        when(ownerRepository.findByLastName(testName)).thenReturn(testOwner);

        assertEquals(ownerService.findByLastName(testName),testOwner);
        verify(ownerRepository,times(1)).findByLastName(eq(testName));

    }

    @Test
    void findByLastNameNotExists() {

        when(ownerRepository.findByLastName(testName)).thenReturn(null);

        assertNull(ownerService.findByLastName(testName));
        verify(ownerRepository,times(1)).findByLastName(eq(testName));

    }

    @Test
    void findByLastNameLikeMultiple() {

        //given
        Owner testOwner1 = Owner.builder().id(1L).lastName("Briggs").build();
        Owner testOwner2 = Owner.builder().id(2L).lastName("Tiggs").build();
        Set<Owner> testOwners = new HashSet<>();
        testOwners.add(testOwner1);
        testOwners.add(testOwner2);
        when(ownerRepository.findByLastNameIgnoreCaseContaining("igg")).thenReturn(testOwners);

        //when
        Set<Owner> foundOwners = ownerService.findByLastNameIgnoreCaseContaining("igg");

        //then
        assertEquals(2,foundOwners.size());
        verify(ownerRepository,times(1)).findByLastNameIgnoreCaseContaining(any());

    }

    @Test
    void findByLastNameLikeOne() {

        //given
        Owner testOwner2 = Owner.builder().id(2L).lastName("Tiggs").build();
        Set<Owner> testOwners = new HashSet<>();
        testOwners.add(testOwner2);
        when(ownerRepository.findByLastNameIgnoreCaseContaining("Tigg")).thenReturn(testOwners);

        //when
        Set<Owner> foundOwners = ownerService.findByLastNameIgnoreCaseContaining("Tigg");

        //then
        assertEquals(1,foundOwners.size());
        verify(ownerRepository,times(1)).findByLastNameIgnoreCaseContaining(any());

    }

    @Test
    void findByLastNameLikeNone() {

        //given
        Set<Owner> testOwners = new HashSet<>();
        when(ownerRepository.findByLastNameIgnoreCaseContaining("yuur")).thenReturn(testOwners);

        //when
        Set<Owner> foundOwners = ownerService.findByLastNameIgnoreCaseContaining("yuur");

        //then
        assertTrue(foundOwners.isEmpty());
        verify(ownerRepository,times(1)).findByLastNameIgnoreCaseContaining(any());

    }
}