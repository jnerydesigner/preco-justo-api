package br.com.jandernery.precojusto.infra.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jandernery.precojusto.domain.entities.DuckEntity;

@Repository
public interface DuckRepository extends JpaRepository<DuckEntity, UUID> {
    DuckEntity findByName(String name);

    @Query("SELECT d FROM DuckEntity d WHERE d.id = :id")
    DuckEntity findByIdUUid(UUID id);

    @Query("SELECT d FROM DuckEntity d WHERE d.id = :motherId")
    List<DuckEntity> findByMotherId(UUID motherId);

    @Query("SELECT d FROM DuckEntity d")
    List<DuckEntity> findAllDucks();

}
