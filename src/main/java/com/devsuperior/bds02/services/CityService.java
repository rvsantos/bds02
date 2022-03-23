package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.exceptions.DataViolationException;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {
    private static final String RESOURCE_CANNOT_BE_DELETED = "Resource cannot be deleted because it is associated with other resources.";

    private static final String RESOURCE_NOT_FOUND = "Resource not found: ";

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        return repository.findAll(Sort.by("name"))
                .stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        return new CityDTO(repository.save(dto.toEntity()));
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataViolationException(RESOURCE_CANNOT_BE_DELETED);
        }
    }
}
