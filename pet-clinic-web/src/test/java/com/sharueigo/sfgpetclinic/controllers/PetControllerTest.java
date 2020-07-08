package com.sharueigo.sfgpetclinic.controllers;

import com.sharueigo.sfgpetclinic.model.Owner;
import com.sharueigo.sfgpetclinic.model.Pet;
import com.sharueigo.sfgpetclinic.model.PetType;
import com.sharueigo.sfgpetclinic.services.OwnerService;
import com.sharueigo.sfgpetclinic.services.PetService;
import com.sharueigo.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    MockMvc mockMvc;
    @Mock
    PetTypeService petTypeService;
    @Mock
    OwnerService ownerService;
    @Mock
    PetService petService;
    @InjectMocks
    PetController petController;



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
        Owner testOwner = Owner.builder().id(1L).lastName("Fred").build();
        when(ownerService.findById(any())).thenReturn(testOwner);
        Set<PetType> types = new HashSet<>();
        types.add(PetType.builder().id(1L).name("Giraffe").build());
        types.add(PetType.builder().id(2L).name("Horse").build());
        when(petTypeService.findAll()).thenReturn(types);
    }

    @Test
    void newPetForm(){
        try{
            mockMvc.perform(get("/owners/1/pets/new"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("owner"))
                    .andExpect(model().attributeExists("types"))
                    .andExpect(model().attributeExists("pet"))
                    .andExpect(view().name("owners/pets/createOrUpdatePetForm"));
            verify(ownerService,times(1)).findById(any());
            verify(petTypeService,times(1)).findAll();
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    void editPetForm(){
        when(petService.findById(any())).thenReturn(Pet.builder().id(1L).build());
        try{
            mockMvc.perform(get("/owners/1/pets/1/edit"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("owner"))
                    .andExpect(model().attributeExists("types"))
                    .andExpect(model().attributeExists("pet"))
                    .andExpect(view().name("owners/pets/createOrUpdatePetForm"));
            verify(ownerService,times(1)).findById(any());
            verify(petTypeService,times(1)).findAll();
            verify(petService,times(1)).findById(any());
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    void newPetAction(){
        when(petService.save(any())).thenReturn(Pet.builder().id(2L).build());
        try{
            mockMvc.perform(post("/owners/1/pets/new").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(model().attributeExists("owner"))
                    .andExpect(model().attributeExists("types"))
                    .andExpect(header().string("Location","/owners/1"));
            verify(ownerService,times(1)).findById(any());
            verify(petTypeService,times(1)).findAll();
            verify(petService,times(1)).save(any());
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    void editPetAction(){
        when(petService.save(any())).thenReturn(Pet.builder().id(2L).build());
        try{
            mockMvc.perform(post("/owners/1/pets/2/edit").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(model().attributeExists("owner"))
                    .andExpect(model().attributeExists("types"))
                    .andExpect(header().string("Location","/owners/1"));
            verify(ownerService,times(1)).findById(any());
            verify(petTypeService,times(1)).findAll();
            verify(petService,times(1)).save(any());
        } catch (Exception e){
            fail(e.getMessage());
        }
    }

}