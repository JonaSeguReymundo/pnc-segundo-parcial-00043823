package com.uca.pncsegundoparcialcoworking.service;

import com.uca.pncsegundoparcialcoworking.dto.request.CreateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.request.UpdateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.response.SpaceResponse;
import com.uca.pncsegundoparcialcoworking.utils.SpaceType;

import java.util.List;

public interface SpaceService {
    SpaceResponse createSpace(CreateSpaceRequest request);

    List<SpaceResponse> getSpaces(SpaceType type, Boolean available);

    SpaceResponse getSpaceById(Long id);

    SpaceResponse updateSpace(Long id, UpdateSpaceRequest request);

    void deleteSpace(Long id);
}