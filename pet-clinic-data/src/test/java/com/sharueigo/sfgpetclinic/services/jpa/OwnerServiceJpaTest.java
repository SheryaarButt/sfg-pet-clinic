package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
}