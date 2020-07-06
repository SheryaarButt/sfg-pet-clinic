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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void findOwnersPage(){

        try{
            mockMvc.perform(get("/owners/find"))
                    .andExpect(view().name("owners/findOwners"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("owner"));
        } catch(Exception e){
            fail(e.getMessage());
        }

        verifyNoInteractions(ownerService);
    }

    @Test
    void findOwnersActionMultiple(){

        Owner testOwner1 = Owner.builder().id(1L).lastName("Briggs").build();
        Owner testOwner2 = Owner.builder().id(2L).lastName("Tigg").build();
        Set<Owner> owners = new HashSet<>();
        owners.add(testOwner1);
        owners.add(testOwner2);
        when(ownerService.findByLastNameIgnoreCaseContaining(any())).thenReturn(owners);

        try{
            mockMvc.perform(get("/owners"))
                    .andExpect(view().name("owners/ownersList"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("selections"));
        } catch(Exception e){
            fail(e.getMessage());
        }

        verify(ownerService,times(1)).findByLastNameIgnoreCaseContaining(any());
    }

    @Test
    void findOwnersActionSingle(){

        Owner testOwner1 = Owner.builder().id(1L).lastName("Briggs").build();
        Set<Owner> owners = new HashSet<>();
        owners.add(testOwner1);
        when(ownerService.findByLastNameIgnoreCaseContaining(any())).thenReturn(owners);

        try{
            mockMvc.perform(get("/owners"))
                    .andExpect(header().string("Location","/owners/1"))
                    .andExpect(status().is3xxRedirection());
        } catch(Exception e){
            fail(e.getMessage());
        }

        verify(ownerService,times(1)).findByLastNameIgnoreCaseContaining(any());
    }

    @Test
    void findOwnersActionNone(){

        Set<Owner> owners = new HashSet<>();
        when(ownerService.findByLastNameIgnoreCaseContaining(any())).thenReturn(owners);

        try{
            mockMvc.perform(get("/owners"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owners/findOwners"));
        } catch(Exception e){
            fail(e.getMessage());
        }

        verify(ownerService,times(1)).findByLastNameIgnoreCaseContaining(any());
    }

}