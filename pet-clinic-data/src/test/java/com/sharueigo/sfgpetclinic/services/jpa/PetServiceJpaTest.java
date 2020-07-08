package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.repositories.OwnerRepository;
import com.sharueigo.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PetServiceJpaTest {

    @Mock
    PetRepository petRepository;
    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    PetServiceJpa petServiceJpa;

}