package com.uca.pncsegundoparcialcoworking.service.impl;

import com.uca.pncsegundoparcialcoworking.dto.request.CreateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.request.UpdateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.response.SpaceResponse;
import com.uca.pncsegundoparcialcoworking.entities.Space;
import com.uca.pncsegundoparcialcoworking.exceptions.BusinessRuleException;
import com.uca.pncsegundoparcialcoworking.exceptions.ResourceNotFoundException;
import com.uca.pncsegundoparcialcoworking.repository.SpaceRepository;
import com.uca.pncsegundoparcialcoworking.service.SpaceService;
import com.uca.pncsegundoparcialcoworking.utils.SpaceType;
import com.uca.pncsegundoparcialcoworking.utils.mappers.SpaceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;
    private final SpaceMapper spaceMapper;

    @Override
    @Transactional
    public SpaceResponse createSpace(CreateSpaceRequest request) {
        Space space = spaceMapper.toEntityCreate(request);
        return spaceMapper.toDto(spaceRepository.save(space));
    }

    @Override
    public List<SpaceResponse> getSpaces(SpaceType type, Boolean available) {
        List<Space> spaces;
        if (type != null && available != null) {
            spaces = spaceRepository.findByTypeAndAvailable(type, available);
        } else if (type != null) {
            spaces = spaceRepository.findByType(type);
        } else if (available != null) {
            spaces = spaceRepository.findByAvailable(available);
        } else {
            spaces = spaceRepository.findAll();
        }
        return spaceMapper.toDtoList(spaces);
    }

    @Override
    public SpaceResponse getSpaceById(Long id) {
        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Espacio de coworking no encontrado con el ID: " + id));
        return spaceMapper.toDto(space);
    }

    @Override
    @Transactional
    public SpaceResponse updateSpace(Long id, UpdateSpaceRequest request) {
        Space existingSpace = spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. Espacio no encontrado con el ID: " + id));

        // Regla de Negocio: Validar nombre único si se cambió el nombre original
        if (!existingSpace.getName().equalsIgnoreCase(request.getName()) &&
                spaceRepository.existsByNameIgnoreCase(request.getName())) {
            throw new BusinessRuleException("El nuevo nombre del espacio ya está en uso por otro recurso.");
        }

        Space updatedSpace = spaceMapper.toEntityUpdate(request, id);
        return spaceMapper.toDto(spaceRepository.save(updatedSpace));
    }

    @Override
    @Transactional
    public void deleteSpace(Long id) {
        Space space = spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. Espacio no encontrado con el ID: " + id));

        // Regla de negocio: Protección de eliminación si available = false
        if (!space.getAvailable()) {
            throw new BusinessRuleException("Regla de negocio violada: No se puede eliminar un espacio que no esté disponible (está en uso o bloqueado).");
        }

        spaceRepository.deleteById(id);
    }
}