package br.com.jandernery.precojusto.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import br.com.jandernery.precojusto.application.dto.DuckDTO;
import br.com.jandernery.precojusto.application.dto.DuckRecord;
import br.com.jandernery.precojusto.domain.entities.DuckEntity;
import br.com.jandernery.precojusto.infra.exceptions.DuckNotFoundException;
import br.com.jandernery.precojusto.infra.repositories.DuckRepository;

import org.springframework.http.HttpStatus;

@Service
public class DuckService {
    private final DuckRepository duckRepository;

    @Autowired
    public DuckService(DuckRepository duckRepository) {
        this.duckRepository = duckRepository;
    }

    public void createDuck(DuckRecord duck) {
        if (duck.motherId() != null) {
            DuckEntity mother = getMother(duck);
            DuckEntity duckEntity = new DuckEntity();
            duckEntity.setName(duck.name());
            duckEntity.setMother(mother);
            duckRepository.save(duckEntity);
        } else {
            DuckEntity duckEntity = new DuckEntity();
            duckEntity.setName(duck.name());
            duckEntity.setMother(null);
            duckRepository.save(duckEntity);
        }

        // getMother(duck);
        // DuckEntity duckEntity = new DuckEntity();
        // duckEntity.setName(duck.name());
        // duckEntity.setIsMother(duck.isMother());
        // if (duck.isMother()) {
        // duckEntity.setMother(null);
        // } else {
        // DuckEntity mother = duckRepository.findByName(duck.mother());
        // duckEntity.setMother(mother);
        // }
        // duckRepository.save(duckEntity);

    }

    public DuckEntity getMother(DuckRecord duck) {
        DuckEntity duckEntity = duckRepository.findByIdUUid(duck.motherId());
        System.out.println(duckEntity.getName());

        if (duckEntity == null) {
            throw new DuckNotFoundException(HttpStatus.NOT_FOUND, "Duck Mother not found", this.getClass().getName());
        }

        return duckEntity;
    }

    public List<DuckEntity> findAll() {
        return duckRepository.findAllDucks();
    }

    public DuckEntity getDucksByMother(UUID motherId) {
        DuckEntity duck = duckRepository.findById(motherId)
                .orElseThrow(() -> new DuckNotFoundException(HttpStatus.NOT_FOUND, "Mother Duck not found",
                        this.getClass().getName()));

        return duck;
    }

    public DuckEntity getDuckById(UUID duckId) {
        DuckEntity duck = duckRepository.findById(duckId)
                .orElseThrow(() -> new DuckNotFoundException(HttpStatus.NOT_FOUND, "Mother Duck not found",
                        this.getClass().getName()));

        // ModelMapper modelMapper = new ModelMapper();

        // System.out.println(duck.getName());
        // DuckDTO duckDTO = modelMapper.map(duck, DuckDTO.class);

        return duck;
    }

}
