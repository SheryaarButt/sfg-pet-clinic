package com.sharueigo.sfgpetclinic.controllers;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Owner testOwner;

    Set<Owner> testOwners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        testOwner = Owner.builder()
                .id(1L)
                .firstName("John")
                .lastName("Farts")
                .address("Kingsbridge")
                .city("Ellicott")
                .telephone("3423423")
                .build();
        testOwners = new HashSet<>();
        testOwners.add(testOwner);
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void listOwners() {

        when(ownerService.findAll()).thenReturn(testOwners);

        try{
            mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("owners/index"))
                    .andExpect(MockMvcResultMatchers.model().attribute("owners",testOwners));
        } catch (Exception e){
            fail(e.getMessage());
        }

        verify(ownerService,times(1)).findAll();

    }
}