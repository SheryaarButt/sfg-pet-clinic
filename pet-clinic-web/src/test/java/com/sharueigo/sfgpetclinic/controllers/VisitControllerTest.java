package com.sharueigo.sfgpetclinic.controllers;

import com.sharueigo.sfgpetclinic.model.Pet;
import com.sharueigo.sfgpetclinic.model.Visit;
import com.sharueigo.sfgpetclinic.services.PetService;
import com.sharueigo.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    PetService petService;
    @Mock
    VisitService visitService;
    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initializeNewForm() {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        try{
            mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                    .andExpect(view().name("owners/pets/createOrUpdateVisitForm"))
                    .andExpect(model().attributeExists("pet","visit"))
                    .andExpect(status().isOk());
        } catch(Exception e){
            fail(e.getMessage());
        }
        verify(petService,times(1)).findById(anyLong());
        verifyNoInteractions(visitService);
    }

    @Test
    void processNewForm() {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        try{
            mockMvc.perform(post("/owners/1/pets/1/visits/new").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location","/owners/1"));
        }catch(Exception e){
            fail(e.getMessage());
        }
        verify(visitService,times(1)).save(any());
        verify(petService,times(1)).findById(anyLong());
    }

    @Test
    void initializeEditForm() {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        when(visitService.findById(anyLong())).thenReturn(Visit.builder().id(1L).build());
        try{
            mockMvc.perform(get("/owners/1/pets/1/visits/1/edit"))
                    .andExpect(view().name("owners/pets/createOrUpdateVisitForm"))
                    .andExpect(model().attributeExists("pet","visit"))
                    .andExpect(status().isOk());
        } catch(Exception e){
            fail(e.getMessage());
        }
        verify(petService,times(1)).findById(anyLong());
        verify(visitService,times(1)).findById(anyLong());
    }

    @Test
    void processEditForm() {
    }
}