package com.uca.pncsegundoparcialcoworking.utils.mappers;

import com.uca.pncsegundoparcialcoworking.dto.request.CreateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.request.UpdateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.response.SpaceResponse;
import com.uca.pncsegundoparcialcoworking.entities.Space;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpaceMapper {

    public Space toEntityCreate(CreateSpaceRequest request) {
        return Space.builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .capacity(request.getCapacity())
                .pricePerHour(request.getPricePerHour())
                .available(request.getAvailable())
                .floor(request.getFloor())
                .amenities(request.getAmenities())
                .build();
    }

    public Space toEntityUpdate(UpdateSpaceRequest request, Long id) {
        return Space.builder()
                .id(id)
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .capacity(request.getCapacity())
                .pricePerHour(request.getPricePerHour())
                .available(request.getAvailable())
                .floor(request.getFloor())
                .amenities(request.getAmenities())
                .build();
    }

    public SpaceResponse toDto(Space space) {
        return SpaceResponse.builder()
                .id(space.getId())
                .name(space.getName())
                .description(space.getDescription())
                .type(space.getType())
                .capacity(space.getCapacity())
                .pricePerHour(space.getPricePerHour())
                .available(space.getAvailable())
                .floor(space.getFloor())
                .amenities(space.getAmenities())
                .build();
    }

    public List<SpaceResponse> toDtoList(List<Space> spaces) {
        return spaces.stream()
                .map(this::toDto)
                .toList();
    }
}