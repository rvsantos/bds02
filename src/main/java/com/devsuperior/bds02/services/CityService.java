package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.repositories.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {
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
}
