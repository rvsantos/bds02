package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    private static final String RESOURCE_NOT_FOUND = "Resource not found: ";

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event event = repository.getOne(id);
            City city = cityRepository.findById(dto.getCityId()).orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND + dto.getCityId()));
            var entity = repository.save(dto.copyDtoToEntity(event, city));
            return new EventDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND + id);
        }
    }

}
