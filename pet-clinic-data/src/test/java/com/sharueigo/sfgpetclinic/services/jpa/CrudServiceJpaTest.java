package com.sharueigo.sfgpetclinic.services.jpa;

import com.sharueigo.sfgpetclinic.model.BaseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class CrudServiceJpaTest {

    @Mock
    CrudRepository<BaseEntity, Long> crudRepository;

    CrudServiceJpa<BaseEntity> crudService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        crudService = new CrudServiceJpa<BaseEntity>(crudRepository){};
    }

    @Test
    void findAll() {
        Set<BaseEntity> entities = new HashSet<>();
        entities.add(new BaseEntity(1L));
        entities.add(new BaseEntity(2L));
        entities.add(new BaseEntity(3L));
        entities.add(new BaseEntity(4L));
        when(crudRepository.findAll()).thenReturn(entities);

        Set<BaseEntity> returnSet = crudService.findAll();

        verify(crudRepository,times(1)).findAll();

        assertEquals(entities,returnSet);
    }

    @Test
    void save() {

        BaseEntity testEntity = new BaseEntity(1L);

        when(crudRepository.save(testEntity)).thenReturn(testEntity);

        assertEquals(testEntity,crudService.save(testEntity));

    }

    @Test
    void findByIdExists() {

        BaseEntity testEntity = new BaseEntity(1L);

        when(crudRepository.findById(1L)).thenReturn(Optional.of(testEntity));

        assertEquals(testEntity,crudService.findById(1L));
        verify(crudRepository,times(1)).findById(eq(1L));
    }

    @Test
    void findByIdNotExists() {

        when(crudRepository.findById(1L)).thenReturn(Optional.empty());

        assertNull(crudService.findById(1L));
        verify(crudRepository,times(1)).findById(eq(1L));
    }

    @Test
    void delete() {

        BaseEntity testEntity = new BaseEntity(1L);

        crudService.delete(testEntity);

        verify(crudRepository,times(1)).delete(eq(testEntity));

    }

    @Test
    void deleteById() {

        crudService.deleteById(1L);
        verify(crudRepository,times(1)).deleteById(eq(1L));
    }
}