package com.uca.pncsegundoparcialcoworking.repository;

import com.uca.pncsegundoparcialcoworking.entities.Space;
import com.uca.pncsegundoparcialcoworking.utils.SpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

    boolean existsByNameIgnoreCase(String name);

    List<Space> findByTypeAndAvailable(SpaceType type, Boolean available);

    List<Space> findByType(SpaceType type);

    List<Space> findByAvailable(Boolean available);
}