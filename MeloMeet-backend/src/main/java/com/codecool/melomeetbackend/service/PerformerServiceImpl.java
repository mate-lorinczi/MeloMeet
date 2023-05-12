package com.codecool.melomeetbackend.service;

import com.codecool.melomeetbackend.dto.PerformerDTO;
import com.codecool.melomeetbackend.model.Performer;
import com.codecool.melomeetbackend.repository.PerformerRepository;
import com.codecool.melomeetbackend.utility.mappers.PerformerMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class PerformerServiceImpl implements PerformerService {

    private final PerformerMapper performerMapper;
    private final PerformerRepository performerRepository;

    @Autowired
    public PerformerServiceImpl(PerformerMapper performerMapper, PerformerRepository performerRepository) {
        this.performerMapper = performerMapper;
        this.performerRepository = performerRepository;
    }

    @Override
    public Performer addNewPerformer(PerformerDTO performerDTO) {
        String performerName = performerDTO.name();
        if(performerRepository.existsByName(performerName)) {
            throw new EntityExistsException("Performer with name " + performerName + " already " +
                    "exists");
        }

        Performer newPerformer = performerMapper.perfromerDtoToPerformer(performerDTO);
        Performer savedPerformer = performerRepository.save(newPerformer);

        return savedPerformer;
    }

    @Override
    public Performer findPerformerById(String id) {
        Performer performer =
                performerRepository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException("Performer with id " + id + " not found"));

        return performer;
    }

    @Override
    public Performer findPerformerByName(String name) {
        Performer performer =
                performerRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Performer with name " + name + " not found!"));
    }

    @Override
    public Performer deleteById(String id) {
        return null;
    }

    @Override
    public Performer findPerformerByFractionOfName(String performerNameFraction) {
        return null;
    }

    @Override
    public Set<Performer> findByEventId(String id) {
        return null;
    }
}
